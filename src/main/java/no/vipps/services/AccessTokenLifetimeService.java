package no.vipps.services;

import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import javax.crypto.spec.SecretKeySpec;
import no.vipps.exceptions.VippsTechnicalException;

class AccessTokenLifetimeService {
  private final JwtParser parser;

  public AccessTokenLifetimeService() {
    parser =
        Jwts.parserBuilder()
            .setSigningKey(new SecretKeySpec(new byte[36], SignatureAlgorithm.HS512.getJcaName()))
            .build();
  }

  public OffsetDateTime getValidTo(String token) {
    try {
      return parser
          .parseClaimsJws(token)
          .getBody()
          .getExpiration()
          .toInstant()
          .atOffset(ZoneOffset.UTC);
    } catch (IllegalArgumentException ex) {
      throw new VippsTechnicalException(ex.getMessage());
    }
  }
}
