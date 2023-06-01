package no.vipps.infrastructure;

import no.vipps.exceptions.VippsTechnicalException;
import okhttp3.Request;

import java.util.concurrent.CompletableFuture;

public interface VippsClient {
  String send(Request request) throws VippsTechnicalException;
  CompletableFuture<String> sendAsync(Request request) throws VippsTechnicalException;
}
