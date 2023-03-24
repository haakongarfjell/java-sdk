package no.vipps.services;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import no.vipps.exceptions.VippsTechnicalException;
import no.vipps.model.accesstoken.AccessToken;

public class AccessTokenCacheService {

  private static final AccessTokenLifetimeService LIFETIME_SERVICE =
      new AccessTokenLifetimeService();

  private static final Duration BACKOFF_DURATION = Duration.ofMinutes(2);

  private static final ConcurrentMap<String, AccessTokenCacheEntry> CACHE =
      new ConcurrentHashMap<>();

  private static final String KEY_PREFIX = "access-token-";

  public static void put(String key, AccessToken token) {
    OffsetDateTime tokenValidTo = LIFETIME_SERVICE.getValidTo(token.getToken());
    OffsetDateTime tokenValidToWithBackoff = tokenValidTo.minus(BACKOFF_DURATION);

    if (tokenValidToWithBackoff != null && tokenValidToWithBackoff.isAfter(OffsetDateTime.now())) {
      CACHE.put(
          getPrefixedHashedKey(key), new AccessTokenCacheEntry(token, tokenValidToWithBackoff));
    }
  }

  public static AccessToken get(String key) {
    AccessTokenCacheEntry entry = CACHE.get(getPrefixedHashedKey(key));

    if (entry != null && entry.getValidTo().isAfter(OffsetDateTime.now())) {
      return entry.getToken();
    } else if (entry != null) {
      CACHE.remove(key);
    }

    return null;
  }

  private static String getPrefixedHashedKey(String key) {
    return KEY_PREFIX + getHashedKey(key);
  }

  private static String getHashedKey(String key) {
    try {
      MessageDigest digest = MessageDigest.getInstance("SHA-256");
      byte[] hash = digest.digest(key.getBytes(StandardCharsets.UTF_8));

      StringBuilder hexString = new StringBuilder();
      for (byte b : hash) {
        String hex = Integer.toHexString(0xff & b);
        if (hex.length() == 1) {
          hexString.append('0');
        }
        hexString.append(hex);
      }

      return hexString.toString();
    } catch (NoSuchAlgorithmException e) {
      throw new VippsTechnicalException("SHA-256 not supported", e);
    }
  }

  private static class AccessTokenCacheEntry {

    private final AccessToken token;
    private final OffsetDateTime validTo;

    public AccessTokenCacheEntry(AccessToken token, OffsetDateTime validTo) {
      this.token = token;
      this.validTo = validTo;
    }

    public AccessToken getToken() {
      return token;
    }

    public OffsetDateTime getValidTo() {
      return validTo;
    }
  }
}
