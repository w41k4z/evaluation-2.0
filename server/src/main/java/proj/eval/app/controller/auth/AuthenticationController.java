package proj.eval.app.controller.auth;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import proj.eval.app.request.AuthRequest;
import proj.eval.app.service.AuthenticationService;
import proj.eval.app.util.ApiResponse;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

  private AuthenticationService authService;

  public AuthenticationController(AuthenticationService authService) {
    this.authService = authService;
  }

  @PostMapping("/sign-in")
  private ResponseEntity<ApiResponse> signIn(
    @RequestBody @Valid AuthRequest authRequest
  ) throws JsonProcessingException {
    String token =
      this.authService.authenticateAndGenerateToken(
          authRequest.getUsername(),
          authRequest.getPassword()
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
      this.authService.hashPassword(password),
      HttpStatus.OK
    );
  }
}
