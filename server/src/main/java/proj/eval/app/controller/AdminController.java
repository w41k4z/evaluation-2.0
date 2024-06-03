package proj.eval.app.controller;

import jakarta.validation.Valid;
import java.sql.SQLException;
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
import proj.eval.app.service.AdminService;
import proj.eval.app.service.CategoryService;
import proj.eval.app.service.UserService;
import proj.eval.app.util.ApiResponse;

@RestController
@RequestMapping("/admin")
public class AdminController {

  private AdminService adminService;
  private UserService userService;
  private CategoryService categoryService;

  public AdminController(
    AdminService adminService,
    UserService userService,
    CategoryService categoryService
  ) {
    this.adminService = adminService;
    this.userService = userService;
    this.categoryService = categoryService;
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

  @PostMapping("/create/team")
  public ResponseEntity<ApiResponse> createTeam(
    @RequestBody @Valid CreateUserRequest request
  ) throws RoleException {
    ApiResponse response = new ApiResponse();
    response.setPayload(
      this.userService.createUser(
          request.getName(),
          request.getUsername(),
          request.getPassword(),
          request.getRole()
        )
    );
    response.setMessage("Team created successfully");
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  @GetMapping("/reinitialize")
  public ResponseEntity<ApiResponse> reinitializeDatabase()
    throws SQLException {
    ApiResponse response = new ApiResponse();
    this.adminService.reinitializeDatabase();
    response.setMessage("Database reinitialized successfully");
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping("/assign-categories")
  public ResponseEntity<ApiResponse> assignCategories() throws SQLException {
    ApiResponse response = new ApiResponse();
    this.categoryService.assignCategories();
    response.setMessage("Categories assigned successfully to each runner");
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
