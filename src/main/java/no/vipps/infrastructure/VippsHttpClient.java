package no.vipps.infrastructure;

import java.io.IOException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;
import no.vipps.exceptions.VippsTechnicalException;
import okhttp3.Call;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class VippsHttpClient implements VippsClient {

  private static final Duration DEFAULT_TIMEOUT = Duration.of(100, ChronoUnit.SECONDS);
  private OkHttpClient httpClient;

  private final VippsConfigurationOptions vippsConfigurationOptions;

  public VippsHttpClient(VippsConfigurationOptions vippsConfigurationOptions) {
    this.httpClient = createDefaultOkHttpClient();
    this.vippsConfigurationOptions = vippsConfigurationOptions;
  }

  private Headers getHeaders() {

    HashMap<String, String> headers = new HashMap<>();
    headers.put("Merchant-Serial-Number", vippsConfigurationOptions.getMerchantSerialNumber());
    headers.put("Vipps-System-Name", vippsConfigurationOptions.getSystemName());
    headers.put("Vipps-System-Version", vippsConfigurationOptions.getSystemVersion());
    headers.put("Vipps-System-Plugin-Name", vippsConfigurationOptions.getPluginName());
    headers.put("Vipps-System-Plugin-Version", vippsConfigurationOptions.getPluginVersion());

    return Headers.of(headers);
  }

  OkHttpClient getHttpClient() {
    this.httpClient = createDefaultOkHttpClient();
    return this.httpClient;
  }

  @Override
  public String send(Request request) throws VippsTechnicalException {
    Request modifiedRequest =
        request
            .newBuilder()
            .headers(request.headers().newBuilder().addAll(getHeaders()).build())
            .build();
    try (Response response = httpClient.newCall(modifiedRequest).execute()) {
      if (!response.isSuccessful()) {
        throw new IOException("Unexpected response " + response + ": " + response.body().string());
      }
      return response.body() != null ? response.body().string() : "";
    } catch (IOException ioException) {
      throw new VippsTechnicalException(ioException.getMessage(), ioException);
    }
  }

  @Override
  public CompletableFuture<String> sendAsync(Request request) throws VippsTechnicalException {
    Request modifiedRequest =
        request
            .newBuilder()
            .headers(request.headers().newBuilder().addAll(getHeaders()).build())
            .build();

    Call call = httpClient.newCall(modifiedRequest);
    VippsHttpCallbackFuture future = new VippsHttpCallbackFuture(call);
    call.enqueue(future);
    return future;
  }

  private OkHttpClient createDefaultOkHttpClient() {
    return new OkHttpClient.Builder()
        .retryOnConnectionFailure(true)
        .connectTimeout(DEFAULT_TIMEOUT)
        .addInterceptor(
            chain -> {
              Request request = chain.request();
              Response response = chain.proceed(request);
              int tryCount = 0;
              while (response.code() >= 500 && tryCount < 3) {
                tryCount++;
                response.close();
                response = chain.proceed(request);
              }
              return response;
            })
        .build();
  }
}
