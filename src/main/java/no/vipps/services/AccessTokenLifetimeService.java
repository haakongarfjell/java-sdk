package no.vipps.services;

import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import no.vipps.exceptions.VippsTechnicalException;

class AccessTokenLifetimeService {
  private final JwtParser parser;

  public AccessTokenLifetimeService() {
    parser = Jwts.parserBuilder().build();
  }

  public OffsetDateTime getValidTo(String token) {
    try {
      return parser.parseClaimsJws(token).getBody().getExpiration().toInstant()
          .atOffset(ZoneOffset.UTC);
    } catch (IllegalArgumentException ex) {
      throw new VippsTechnicalException("Could not parse AccessToken expiration time");
    }
  }
}
