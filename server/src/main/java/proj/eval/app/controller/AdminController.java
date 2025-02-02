package proj.eval.app.controller;

import jakarta.validation.Valid;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import proj.eval.app.exception.RoleException;
import proj.eval.app.model.auth.User;
import proj.eval.app.request.CreateUserRequest;
import proj.eval.app.service.AdminService;
import proj.eval.app.service.CategoryService;
import proj.eval.app.service.RankingService;
import proj.eval.app.service.StageService;
import proj.eval.app.service.TeamPenaltyService;
import proj.eval.app.service.TeamService;
import proj.eval.app.service.UserService;
import proj.eval.app.util.ApiResponse;

@RestController
@RequestMapping("/admin")
public class AdminController {

  private AdminService adminService;
  private UserService userService;
  private CategoryService categoryService;
  private RankingService rankingService;
  private TeamService teamService;
  private StageService stageService;
  private TeamPenaltyService teamPenaltyService;

  public AdminController(
    AdminService adminService,
    UserService userService,
    CategoryService categoryService,
    TeamService teamService,
    StageService stageService,
    RankingService rankingService,
    TeamPenaltyService teamPenaltyService
  ) {
    this.adminService = adminService;
    this.userService = userService;
    this.categoryService = categoryService;
    this.rankingService = rankingService;
    this.teamService = teamService;
    this.stageService = stageService;
    this.teamPenaltyService = teamPenaltyService;
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

  @GetMapping("/penalties")
  public ResponseEntity<ApiResponse> getPenaltiesList() {
    ApiResponse response = new ApiResponse();
    Map<String, Object> payload = new HashMap<>();
    payload.put("teams", this.teamService.list());
    payload.put("stages", this.stageService.list());
    payload.put("teamPenalties", this.teamPenaltyService.list());
    response.setPayload(payload);
    return ResponseEntity.ok(response);
  }

  @DeleteMapping("/penalties/{id}")
  public ResponseEntity<ApiResponse> removePenalty(@PathVariable String id) {
    ApiResponse response = new ApiResponse();
    this.teamPenaltyService.delete(Long.parseLong(id));
    response.setMessage("Penalty removed successfully");
    return ResponseEntity.ok(response);
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

  @GetMapping("/certificate/{filter}")
  public ResponseEntity<ApiResponse> certificate(@PathVariable String filter)
    throws SQLException {
    ApiResponse response = new ApiResponse();
    User team = this.rankingService.certificate(filter);
    response.setPayload(team);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
