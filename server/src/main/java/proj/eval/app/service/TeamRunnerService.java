package proj.eval.app.service;

import java.util.List;
import org.springframework.stereotype.Service;
import proj.eval.app.model.postgres.TeamRunner;
import proj.eval.app.model.postgres.auth.User;
import proj.eval.app.repository.postgres.TeamRunnerRepository;

@Service
public class TeamRunnerService {

  private TeamRunnerRepository repository;
  private UserService userService;

  public TeamRunnerService(
    TeamRunnerRepository repository,
    UserService userService
  ) {
    this.repository = repository;
    this.userService = userService;
  }

  public List<TeamRunner> runners(String teamUsername, Long stageId) {
    User team = this.userService.findByUsername(teamUsername);
    return repository.findAllByTeam_IdAndStage_Id(team.getId(), stageId);
  }
}
