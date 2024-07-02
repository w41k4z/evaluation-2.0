package proj.eval.app.service;

import java.util.List;
import org.springframework.stereotype.Service;
import proj.eval.app.model.postgres.auth.User;
import proj.eval.app.model.postgres.stat.GeneralRanking;
import proj.eval.app.model.postgres.stat.RunnerRanking;
import proj.eval.app.model.postgres.stat.TeamAllCategoryRanking;
import proj.eval.app.model.postgres.stat.TeamCategoryRanking;
import proj.eval.app.model.postgres.stat.TeamRanking;
import proj.eval.app.model.postgres.stat.TeamRankingDetails;
import proj.eval.app.repository.postgres.GeneralRankingRepository;
import proj.eval.app.repository.postgres.RunnerRankingRepository;
import proj.eval.app.repository.postgres.TeamAllCategoryRankingRepository;
import proj.eval.app.repository.postgres.TeamCategoryRankingRepository;
import proj.eval.app.repository.postgres.TeamRankingDetailsRepository;
import proj.eval.app.repository.postgres.TeamRankingRepository;

@Service
public class RankingService {

  private GeneralRankingRepository generalRankingRepository;
  private TeamRankingRepository teamRankingRepository;
  private RunnerRankingRepository runnerRankingRepository;
  private TeamCategoryRankingRepository teamCategoryRankingRepository;
  private TeamAllCategoryRankingRepository teamAllCategoryRankingRepository;
  private TeamRankingDetailsRepository teamRankingDetailsRepository;

  public RankingService(
    GeneralRankingRepository generalRankingRepository,
    TeamRankingRepository teamRankingRepository,
    RunnerRankingRepository runnerRankingRepository,
    TeamCategoryRankingRepository teamCategoryRankingRepository,
    TeamAllCategoryRankingRepository teamAllCategoryRankingRepository,
    TeamRankingDetailsRepository teamRankingDetailsRepository
  ) {
    this.generalRankingRepository = generalRankingRepository;
    this.teamRankingRepository = teamRankingRepository;
    this.runnerRankingRepository = runnerRankingRepository;
    this.teamCategoryRankingRepository = teamCategoryRankingRepository;
    this.teamAllCategoryRankingRepository = teamAllCategoryRankingRepository;
    this.teamRankingDetailsRepository = teamRankingDetailsRepository;
  }

  public User certificate(String filter) {
    switch (filter) {
      case "Aside":
        return this.teamAllCategoryRanking().get(0).getTeam();
      case "All":
        return this.teamGlobalRanking().get(0).getTeam();
      default:
        return teamCategoryRanking(filter).get(0).getTeam();
    }
  }

  public List<? extends Object> teamRanking(String filter) {
    switch (filter) {
      case "Aside":
        return this.teamAllCategoryRanking();
      case "All":
        return this.teamGlobalRanking();
      default:
        return teamCategoryRanking(filter);
    }
  }

  private List<TeamRanking> teamGlobalRanking() {
    return this.teamRankingRepository.findAll();
  }

  public List<TeamCategoryRanking> teamCategoryRanking(String categoryName) {
    return this.teamCategoryRankingRepository.findAllByCategory_Name(
        categoryName
      );
  }

  private List<TeamAllCategoryRanking> teamAllCategoryRanking() {
    return this.teamAllCategoryRankingRepository.findAll();
  }

  public List<RunnerRanking> runnerGlobalRanking() {
    return this.runnerRankingRepository.findAll();
  }

  public List<GeneralRanking> runnerRankingPerStage() {
    return this.generalRankingRepository.findAll();
  }

  public List<GeneralRanking> runnerRankingByStage(Long stageId) {
    return this.generalRankingRepository.findAllByStage_Id(stageId);
  }

  public List<TeamRankingDetails> rankingDetailsByTeam(String team) {
    return this.teamRankingDetailsRepository.findAllByTeam_Id(team);
  }
}
