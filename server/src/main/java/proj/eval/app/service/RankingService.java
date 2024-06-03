package proj.eval.app.service;

import java.util.List;
import org.springframework.stereotype.Service;
import proj.eval.app.model.stat.GeneralRanking;
import proj.eval.app.model.stat.RunnerRanking;
import proj.eval.app.model.stat.TeamCategoryRanking;
import proj.eval.app.model.stat.TeamRanking;
import proj.eval.app.repository.GeneralRankingRepository;
import proj.eval.app.repository.RunnerRankingRepository;
import proj.eval.app.repository.TeamCategoryRankingRepository;
import proj.eval.app.repository.TeamRankingRepository;

@Service
public class RankingService {

  private GeneralRankingRepository generalRankingRepository;
  private TeamRankingRepository teamRankingRepository;
  private RunnerRankingRepository runnerRankingRepository;
  private TeamCategoryRankingRepository teamCategoryRankingRepository;

  public RankingService(
    GeneralRankingRepository generalRankingRepository,
    TeamRankingRepository teamRankingRepository,
    RunnerRankingRepository runnerRankingRepository,
    TeamCategoryRankingRepository teamCategoryRankingRepository
  ) {
    this.generalRankingRepository = generalRankingRepository;
    this.teamRankingRepository = teamRankingRepository;
    this.runnerRankingRepository = runnerRankingRepository;
    this.teamCategoryRankingRepository = teamCategoryRankingRepository;
  }

  public List<TeamRanking> teamGlobalRanking() {
    return this.teamRankingRepository.findAll();
  }

  public List<TeamCategoryRanking> teamCategoryRanking(String categoryName) {
    return this.teamCategoryRankingRepository.findAllByCategory_Name(
        categoryName
      );
  }

  public List<RunnerRanking> runnerGlobalRanking() {
    return this.runnerRankingRepository.findAll();
  }

  public List<GeneralRanking> runnerRankingPerStage() {
    return this.generalRankingRepository.findAll();
  }
}
