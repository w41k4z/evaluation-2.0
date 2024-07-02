package proj.eval.app.controller.api.v1;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import proj.eval.app.service.TeamRunnerService;
import proj.eval.app.util.ApiResponse;

@RestController
@RequestMapping("/api/v1/team-runners")
public class TeamRunnerController {

  private TeamRunnerService service;

  public TeamRunnerController(TeamRunnerService service) {
    this.service = service;
  }

  @GetMapping("/{stageId}")
  public ResponseEntity<ApiResponse> teamRunners(
    @AuthenticationPrincipal UserDetails userDetails,
    @PathVariable String stageId
  ) {
    ApiResponse response = new ApiResponse();
    response.setPayload(
      this.service.runners(userDetails.getUsername(), Long.valueOf(stageId))
    );
    return ResponseEntity.ok(response);
  }
}
