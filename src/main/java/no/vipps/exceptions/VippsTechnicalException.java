package no.vipps.exceptions;

public class VippsTechnicalException extends RuntimeException {
  public VippsTechnicalException(String message) {
    super(message);
  }

  public VippsTechnicalException(String message, Exception innerException) {
    super(message, innerException);
  }
}
