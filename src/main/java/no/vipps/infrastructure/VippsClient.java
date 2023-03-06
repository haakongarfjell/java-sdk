package no.vipps.infrastructure;


import no.vipps.exceptions.VippsTechnicalException;
import okhttp3.Request;

public interface VippsClient {
  String send(
      Request request
  ) throws VippsTechnicalException;
}
