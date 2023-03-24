package no.vipps.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;
import javax.crypto.spec.SecretKeySpec;
import no.vipps.model.accesstoken.AccessToken;
import no.vipps.services.AccessTokenCacheService;
import org.junit.jupiter.api.Test;


public class AccessTokenCacheServiceTests {
  @Test
  public void canRetrieveSavedValid() {
    String key = UUID.randomUUID().toString();
    AccessToken accessToken =
        getToken(getInstantSubtract(1, ChronoUnit.HOURS), getInstantAdd(1, ChronoUnit.HOURS));
    AccessTokenCacheService.put(key, accessToken);
    AccessToken res = AccessTokenCacheService.get(key);
    assertEquals(accessToken, res);
  }

  @Test
  public void canNotRetrieveSavedExpired() {
    String key = UUID.randomUUID().toString();
    AccessToken accessToken =
        getToken(getInstantSubtract(2, ChronoUnit.HOURS).atOffset(ZoneOffset.UTC).toInstant(),
            getInstantAdd(1, ChronoUnit.HOURS).atOffset(ZoneOffset.UTC).toInstant());
    AccessTokenCacheService.put(key, accessToken);
    AccessToken res = AccessTokenCacheService.get(key);
    assertNull(res);
  }

  @Test
  public void canNotRetrieveSavedNotValidForLongEnough() {
    String key = UUID.randomUUID().toString();
    AccessToken accessToken =
        getToken(getInstantSubtract(2, ChronoUnit.HOURS), getInstantAdd(1, ChronoUnit.MINUTES));
    AccessTokenCacheService.put(key, accessToken);
    AccessToken res = AccessTokenCacheService.get(key);
    assertNull(res);
  }

  private Instant getInstantAdd(int amountToAdd, ChronoUnit unit) {
    return Instant.now().plus(amountToAdd, unit).atOffset(ZoneOffset.UTC).toInstant();
  }
  private Instant getInstantSubtract(int amountToSubtract, ChronoUnit unit) {
    return Instant.now().minus(amountToSubtract, unit).atOffset(ZoneOffset.UTC).toInstant();
  }

  private AccessToken getToken(Instant notBefore, Instant expiresAt) {
    JwtBuilder jwtBuilder = Jwts.builder().setIssuer("TestIssuer").setAudience("TestAudience")
        .setNotBefore(Date.from(notBefore)).setExpiration(Date.from(expiresAt))
        .setIssuedAt(Date.from(notBefore)).signWith(new SecretKeySpec(new byte[36],
            SignatureAlgorithm.HS512.getJcaName())); // 18 =  AES256_CTS_HMAC_SHA1_96

    String jwtString = jwtBuilder.compact();
    return AccessToken.builder().tokenType("Bearer").expiresIn("3600").extExpiresIn("3600")
        .expiresOn(expiresAt.toString()).notBefore("0").resource("").token(jwtString).build();
  }
}
