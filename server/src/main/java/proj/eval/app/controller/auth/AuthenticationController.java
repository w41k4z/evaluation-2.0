package proj.eval.app.controller.auth;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import proj.eval.app.request.AuthRequest;
import proj.eval.app.service.JwtService;
import proj.eval.app.util.ApiResponse;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

  private AuthenticationManager authenticationManager;
  private PasswordEncoder passwordEncoder;
  private JwtService jwtService;

  public AuthenticationController(
    AuthenticationManager authenticationManager,
    PasswordEncoder passwordEncoder,
    JwtService jwtService
  ) {
    this.authenticationManager = authenticationManager;
    this.passwordEncoder = passwordEncoder;
    this.jwtService = jwtService;
  }

  @PostMapping("/sign-in")
  private ResponseEntity<ApiResponse> signIn(
    @RequestBody @Valid AuthRequest authRequest
  ) throws JsonProcessingException {
    Authentication authentication = authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(
        authRequest.getUsername(),
        authRequest.getPassword()
      )
    );

    String token = jwtService.generateToken(
      (UserDetails) authentication.getPrincipal()
    );
    Map<String, Object> payload = new HashMap<>();
    payload.put("token", token);
    ApiResponse response = new ApiResponse();
    response.setPayload(payload);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @PostMapping("/hash-password")
  private ResponseEntity<String> hashPassword(@RequestParam String password) {
    return new ResponseEntity<>(
      this.passwordEncoder.encode(password),
      HttpStatus.OK
    );
  }
}
