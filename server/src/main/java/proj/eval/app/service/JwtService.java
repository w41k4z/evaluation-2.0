package proj.eval.app.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

  @Value("${jwt.private.key}")
  private String SECRET_KEY;

  @Value("${jwt.expiration.time}")
  private Long EXPIRATION_TIME;

  public boolean isValidToken(String token) {
    try {
      Jwts
        .parserBuilder()
        .setSigningKey(this.getSignInKey())
        .build()
        .parseClaimsJws(token);
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  private Claims extractAllClaims(String token) {
    return Jwts
      .parserBuilder()
      .setSigningKey(getSignInKey())
      .build()
      .parseClaimsJws(token)
      .getBody();
  }

  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  public String getSubject(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  private Key getSignInKey() {
    return Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY));
  }

  public String generateToken(UserDetails userDetails)
    throws JsonProcessingException {
    Map<String, Object> claims = new HashMap<>();
    Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
    List<String> userAuthorities = new ArrayList<>();
    for (GrantedAuthority authority : authorities) {
      userAuthorities.add(authority.getAuthority());
    }
    claims.put("authorities", userAuthorities);
    return Jwts
      .builder()
      .setClaims(claims)
      .setSubject(userDetails.getUsername())
      .setIssuedAt(new Date(System.currentTimeMillis()))
      .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
      .signWith(this.getSignInKey(), SignatureAlgorithm.HS256)
      .compact();
  }

  public UserDetails getUserDetailsFromToken(String token) {
    Claims claims = this.extractAllClaims(token);
    String username = claims.getSubject();

    @SuppressWarnings("unchecked")
    List<String> authorities = (List<String>) claims.get("authorities");
    Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
    for (String authority : authorities) {
      grantedAuthorities.add(new SimpleGrantedAuthority(authority));
    }
    // No need to check password since it is not used for authentication
    String emptyPassword = "";
    return new User(username, emptyPassword, grantedAuthorities);
  }
}
