package proj.eval.app.service;

import java.sql.Time;
import java.util.List;
import org.springframework.stereotype.Service;
import proj.eval.app.model.Stage;
import proj.eval.app.model.TeamPenalty;
import proj.eval.app.model.auth.User;
import proj.eval.app.repository.TeamPenaltyRepository;

@Service
public class TeamPenaltyService {

  private TeamPenaltyRepository repository;

  public TeamPenaltyService(TeamPenaltyRepository repository) {
    this.repository = repository;
  }

  public List<TeamPenalty> list() {
    return this.repository.findAll();
  }

  public TeamPenalty assignPenalty(
    Long stageId,
    String teamId,
    Time penaltyTime
  ) {
    Stage stage = new Stage();
    stage.setId(stageId);
    User team = new User();
    team.setId(teamId);
    TeamPenalty newTeamPenalty = new TeamPenalty();
    newTeamPenalty.setStage(stage);
    newTeamPenalty.setTeam(team);
    newTeamPenalty.setPenalty(penaltyTime);
    return this.repository.save(newTeamPenalty);
  }

  public void delete(Long id) {
    this.repository.deleteById(id);
  }
}
