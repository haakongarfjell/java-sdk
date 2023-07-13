package no.vipps.infrastructure;

import java.util.concurrent.CompletableFuture;
import no.vipps.exceptions.VippsTechnicalException;
import okhttp3.Request;

public interface VippsClient {
  String send(Request request) throws VippsTechnicalException;

  CompletableFuture<String> sendAsync(Request request) throws VippsTechnicalException;
}
