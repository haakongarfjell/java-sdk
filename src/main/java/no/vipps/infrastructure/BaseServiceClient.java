package no.vipps.infrastructure;

import lombok.Getter;
import no.vipps.helpers.VippsRequestSerializer;
import no.vipps.model.VippsRequest;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseServiceClient {
  private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
  @Getter
  private final VippsClient vippsHttpClient;
  private final Logger logger;

  public BaseServiceClient(VippsClient vippsHttpClient) {
    this.vippsHttpClient = vippsHttpClient;
    this.logger = LoggerFactory.getLogger(this.getClass());
  }

  private static RequestBody createRequestBody(VippsRequest vippsRequest) {
    String serializedRequest = VippsRequestSerializer.serializeVippsRequest(vippsRequest);
    return RequestBody.create(serializedRequest, JSON);
  }

  public <TRequest extends VippsRequest, TResponse> TResponse executeRequest(String path,
                                                                             String httpMethod,
                                                                             TRequest data,
                                                                             Class<TResponse> responseClass) {
    return executeRequestBaseAndParse(path, httpMethod, createRequestBody(data), responseClass);
  }

  public <TResponse> TResponse executeRequest(String path, String httpMethod,
                                              Class<TResponse> responseClass) {
    return executeRequestBaseAndParse(path, httpMethod, null, responseClass);
  }

  public <TResponse> TResponse executeRequest(String path, String httpMethod) {
    return executeRequestBaseAndParse(path, httpMethod, null, null);
  }

  abstract Headers getHeaders();

  private <TResponse> TResponse executeRequestBaseAndParse(String path, String httpMethod,
                                                           RequestBody requestBody,
                                                           Class<TResponse> responseClass) {
    String responseBody = executeRequestBase(path, httpMethod, requestBody);
    return VippsRequestSerializer.<TResponse>deserializeVippsResponse(responseBody, responseClass);
  }

  private String executeRequestBase(String path, String httpMethod, RequestBody requestBody) {
    // TODO: Retry policy
    Request request =
        new Request.Builder().url(path).method(httpMethod, requestBody).headers(getHeaders())
            .build();
    return vippsHttpClient.send(request);
  }


}
