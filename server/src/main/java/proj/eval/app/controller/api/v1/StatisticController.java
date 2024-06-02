package proj.eval.app.controller.api.v1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import proj.eval.app.service.GeneralRankingService;
import proj.eval.app.service.TeamRankingService;
import proj.eval.app.util.ApiResponse;

@RestController
@RequestMapping("/api/v1/statistics")
public class StatisticController {

  private GeneralRankingService generalRankingService;
  private TeamRankingService teamRankingService;

  public StatisticController(
    GeneralRankingService generalRankingService,
    TeamRankingService teamRankingService
  ) {
    this.generalRankingService = generalRankingService;
    this.teamRankingService = teamRankingService;
  }

  @GetMapping("/general")
  public ResponseEntity<ApiResponse> generalRanking() {
    ApiResponse response = new ApiResponse();
    response.setPayload(generalRankingService.ranking());
    return ResponseEntity.ok(response);
  }

  @GetMapping("/general/team")
  public ResponseEntity<ApiResponse> teamRanking() {
    ApiResponse response = new ApiResponse();
    response.setPayload(teamRankingService.ranking());
    return ResponseEntity.ok(response);
  }
}
