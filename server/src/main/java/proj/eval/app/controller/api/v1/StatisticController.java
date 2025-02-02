package proj.eval.app.controller.api.v1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import proj.eval.app.service.RankingService;
import proj.eval.app.util.ApiResponse;

@RestController
@RequestMapping("/api/v1/statistics")
public class StatisticController {

  private RankingService rankingService;

  public StatisticController(RankingService rankingService) {
    this.rankingService = rankingService;
  }

  @GetMapping("/runner/general")
  public ResponseEntity<ApiResponse> generalRanking() {
    ApiResponse response = new ApiResponse();
    response.setPayload(this.rankingService.runnerGlobalRanking());
    return ResponseEntity.ok(response);
  }

  @GetMapping("/runner/general/stage")
  public ResponseEntity<ApiResponse> generalRankingPerStage() {
    ApiResponse response = new ApiResponse();
    response.setPayload(this.rankingService.runnerRankingPerStage());
    return ResponseEntity.ok(response);
  }

  @GetMapping("/team/general/{filter}")
  public ResponseEntity<ApiResponse> teamRanking(@PathVariable String filter) {
    ApiResponse response = new ApiResponse();
    response.setPayload(this.rankingService.teamRanking(filter));
    return ResponseEntity.ok(response);
  }
}
