package no.vipps.infrastructure;

import lombok.Getter;
import no.vipps.helpers.VippsRequestSerializer;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

public abstract class BaseServiceClient {
  private static final MediaType JSON_MEDIA_TYPE = MediaType.get("application/json; charset=utf-8");
  @Getter private final VippsClient vippsHttpClient;

  public BaseServiceClient(VippsClient vippsHttpClient) {
    this.vippsHttpClient = vippsHttpClient;
  }

  private static <T> RequestBody createRequestBody(T request) {
    String serializedRequest = VippsRequestSerializer.serializeVippsRequest(request);
    return RequestBody.create(serializedRequest, JSON_MEDIA_TYPE);
  }

  public <TRequest, TResponse> TResponse executeRequest(
      String path, String httpMethod, TRequest data, Class<TResponse> responseClass) {
    return executeRequestBaseAndParse(path, httpMethod, createRequestBody(data), responseClass);
  }

  public <TResponse> TResponse executeRequest(
      String path, String httpMethod, Class<TResponse> responseClass) {
    return executeRequestBaseAndParse(path, httpMethod, null, responseClass);
  }

  public <TResponse> TResponse executeRequest(String path, String httpMethod) {
    return executeRequestBaseAndParse(path, httpMethod, null, null);
  }

  abstract Headers getHeaders();

  private <TResponse> TResponse executeRequestBaseAndParse(
      String path, String httpMethod, RequestBody requestBody, Class<TResponse> responseClass) {
    String responseBody = executeRequestBase(path, httpMethod, requestBody);
    if (responseClass == null) {
      return null;
    }
    return VippsRequestSerializer.<TResponse>deserializeVippsResponse(responseBody, responseClass);
  }

  private String executeRequestBase(String path, String httpMethod, RequestBody requestBody) {
    if (requestBody == null && httpMethod == "POST") {
      requestBody = RequestBody.create("", null);
    }
    Request request =
        new Request.Builder()
            .url(path)
            .method(httpMethod, requestBody)
            .headers(getHeaders())
            .build();
    return vippsHttpClient.send(request);
  }
}
