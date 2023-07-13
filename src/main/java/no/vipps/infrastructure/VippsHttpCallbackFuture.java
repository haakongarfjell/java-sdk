package no.vipps.infrastructure;

import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import no.vipps.exceptions.VippsTechnicalException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

public class VippsHttpCallbackFuture extends CompletableFuture<String> implements Callback {

  public VippsHttpCallbackFuture(Call call) {
    super();

    this.whenComplete(
        (response, e) -> {
          if (e != null
              && (e instanceof InterruptedException || e instanceof CancellationException)) {
            call.cancel();
          }
        });
  }

  @Override
  public void onFailure(@NotNull Call call, @NotNull IOException e) {
    this.completeExceptionally(new VippsTechnicalException(e.getMessage(), e));
  }

  @Override
  public void onResponse(@NotNull Call call, @NotNull Response response) {
    try {
      if (!response.isSuccessful()) {
        throw new IOException("Unexpected response " + response + ": " + response.body().string());
      }
      this.complete(response.body() != null ? response.body().string() : "");
    } catch (IOException ioException) {
      this.completeExceptionally(
          new VippsTechnicalException(ioException.getMessage(), ioException));
    }
  }
}
