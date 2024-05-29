package proj.eval.app.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import proj.eval.app.exception.RoleException;
import proj.eval.app.request.CreateUserRequest;
import proj.eval.app.service.UserService;
import proj.eval.app.util.ApiResponse;

@RestController
@RequestMapping("/admin")
public class AdminController {

  private UserService userService;

  public AdminController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/users")
  public ResponseEntity<ApiResponse> getUsers(
    @RequestParam(value = "page", defaultValue = "0") int page,
    @RequestParam(value = "size", defaultValue = "5") int size
  ) {
    ApiResponse response = new ApiResponse();
    response.setPayload(this.userService.getUsers(page, size));
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @PostMapping("/create/user")
  public ResponseEntity<ApiResponse> createUser(
    @RequestBody @Valid CreateUserRequest request
  ) throws RoleException {
    ApiResponse response = new ApiResponse();
    response.setPayload(
      this.userService.createUser(
          request.getUsername(),
          request.getPassword(),
          request.getRole()
        )
    );
    response.setMessage("User created successfully");
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }
}
