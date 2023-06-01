package no.vipps.infrastructure;

import lombok.Getter;
import no.vipps.helpers.VippsRequestSerializer;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

public abstract class BaseServiceClient {
  private static final MediaType JSON_MEDIA_TYPE = MediaType.get("application/json; charset=utf-8");
  @Getter
  private final VippsClient vippsHttpClient;

  public BaseServiceClient(VippsClient vippsHttpClient) {
    this.vippsHttpClient = vippsHttpClient;
  }

  abstract Headers getHeaders();

  abstract CompletableFuture<Headers> getHeadersAsync();

  private static <T> RequestBody createRequestBody(T request) {
    String serializedRequest = VippsRequestSerializer.serializeVippsRequest(request);
    return RequestBody.create(serializedRequest, JSON_MEDIA_TYPE);
  }

  public <TRequest, TResponse> TResponse executeRequest(
      String path, String httpMethod, TRequest data, Class<TResponse> responseClass) {
    return executeRequestBaseAndParse(path, httpMethod, createRequestBody(data), responseClass);
  }

  public <TRequest, TResponse> CompletableFuture<TResponse> executeRequestAsync(
      String path, String httpMethod, TRequest data, Class<TResponse> responseClass) {
    return executeRequestBaseAndParseAsync(path, httpMethod, createRequestBody(data), responseClass);
  }

  public <TResponse> TResponse executeRequest(
      String path, String httpMethod, Class<TResponse> responseClass) {
    return executeRequestBaseAndParse(path, httpMethod, null, responseClass);
  }

  public <TResponse> CompletableFuture<TResponse> executeRequestAsync(
      String path, String httpMethod, Class<TResponse> responseClass) {
    return executeRequestBaseAndParseAsync(path, httpMethod, null, responseClass);
  }

  private <TResponse> TResponse executeRequestBaseAndParse(
      String path, String httpMethod, RequestBody requestBody, Class<TResponse> responseClass) {
    RequestBody body = requestBody == null && Objects.equals(httpMethod, "POST")
        ? RequestBody.create("", null)
        : requestBody;

    Request request =
        new Request.Builder()
            .url(path)
            .method(httpMethod, body)
            .headers(getHeaders())
            .build();

    String responseBody = vippsHttpClient.send(request);

    if (responseClass == null) {
      return null;
    }

    return VippsRequestSerializer.deserializeVippsResponse(responseBody, responseClass);
  }

  private <TResponse> CompletableFuture<TResponse> executeRequestBaseAndParseAsync(
      String path, String httpMethod, RequestBody requestBody, Class<TResponse> responseClass) {
    RequestBody body = requestBody == null && Objects.equals(httpMethod, "POST")
        ? RequestBody.create("", null)
        : requestBody;

    return getHeadersAsync()
        .thenCompose((headers) -> {
          Request request =
              new Request.Builder()
                  .url(path)
                  .method(httpMethod, body)
                  .headers(headers)
                  .build();

          return vippsHttpClient.sendAsync(request);
        })
        .thenApply(responseBody -> {
          if (responseClass == null) {
            return null;
          }

          return VippsRequestSerializer.deserializeVippsResponse(responseBody, responseClass);
        });
  }
}
