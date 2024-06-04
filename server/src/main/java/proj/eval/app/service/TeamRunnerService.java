package proj.eval.app.service;

import java.util.List;
import org.springframework.stereotype.Service;
import proj.eval.app.model.TeamRunner;
import proj.eval.app.repository.TeamRunnerRepository;

@Service
public class TeamRunnerService {

  private TeamRunnerRepository repository;

  public TeamRunnerService(TeamRunnerRepository repository) {
    this.repository = repository;
  }

  public List<TeamRunner> runners(String teamId, Long stageId) {
    return repository.findAllByTeam_IdAndStage_Id(teamId, stageId);
  }
}
