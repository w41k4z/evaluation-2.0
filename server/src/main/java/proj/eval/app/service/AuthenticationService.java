package proj.eval.app.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

  private AuthenticationManager authManager;
  private JwtService jwtService;
  private PasswordEncoder passwordEncoder;

  public AuthenticationService(
    AuthenticationManager authManager,
    JwtService jwtService,
    PasswordEncoder passwordEncoder
  ) {
    this.authManager = authManager;
    this.jwtService = jwtService;
    this.passwordEncoder = passwordEncoder;
  }

  public String authenticateAndGenerateToken(String username, String password)
    throws JsonProcessingException {
    Authentication authentication = authManager.authenticate(
      new UsernamePasswordAuthenticationToken(username, password)
    );

    // return the token
    return jwtService.generateToken(
      (UserDetails) authentication.getPrincipal()
    );
  }

  public String hashPassword(String password) {
    return this.passwordEncoder.encode(password);
  }
}
