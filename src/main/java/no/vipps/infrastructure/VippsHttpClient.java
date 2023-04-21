package no.vipps.infrastructure;

import java.io.IOException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import no.vipps.exceptions.VippsTechnicalException;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class VippsHttpClient implements VippsClient {

  private static final Duration DEFAULT_TIMEOUT = Duration.of(100, ChronoUnit.SECONDS);
  private OkHttpClient httpClient = null;

  public VippsHttpClient() {
    this.httpClient = createDefaultOkHttpClient();
  }

  public VippsHttpClient(OkHttpClient httpClient) {
    this.httpClient = httpClient;
  }

  private static Headers getHeaders() {

    HashMap<String, String> headers = new HashMap<>();
    headers.put("Ocp-Apim-Subscription-Key", VippsConfiguration.getInstance().getSubscriptionKey());
    headers.put(
        "Merchant-Serial-Number", VippsConfiguration.getInstance().getMerchantSerialNumber());
    headers.put("Vipps-System-Name", VippsConfiguration.getInstance().getSystemName());
    headers.put("Vipps-System-Version", VippsConfiguration.getInstance().getSystemVersion());
    headers.put("Vipps-System-Plugin-Name", VippsConfiguration.getInstance().getPluginName());
    headers.put("Vipps-System-Plugin-Version", VippsConfiguration.getInstance().getPluginVersion());

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

  private OkHttpClient createDefaultOkHttpClient() {
    var client =
        new OkHttpClient.Builder()
            .retryOnConnectionFailure(true)
            .connectTimeout(DEFAULT_TIMEOUT)
            .addInterceptor(
                new Interceptor() {
                  @Override
                  public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request();
                    Response response = chain.proceed(request);
                    int tryCount = 0;
                    while (response.code() >= 500 && tryCount < 3) {
                      tryCount++;
                      response.close();
                      response = chain.proceed(request);
                    }
                    return response;
                  }
                })
            .build();

    return client;
  }
}
