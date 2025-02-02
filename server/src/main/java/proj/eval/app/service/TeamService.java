package proj.eval.app.service;

import java.util.List;
import org.springframework.stereotype.Service;
import proj.eval.app.model.Team;
import proj.eval.app.repository.TeamRepository;

@Service
public class TeamService {

  private TeamRepository repository;

  public TeamService(TeamRepository repository) {
    this.repository = repository;
  }

  public List<Team> list() {
    return this.repository.findAll();
  }
}
