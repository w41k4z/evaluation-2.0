package proj.eval.app.service;

import java.util.List;
import org.springframework.stereotype.Service;
import proj.eval.app.model.stat.TeamRanking;
import proj.eval.app.repository.TeamRankingRepository;

@Service
public class TeamRankingService {

  private TeamRankingRepository repository;

  public TeamRankingService(TeamRankingRepository repository) {
    this.repository = repository;
  }

  public List<TeamRanking> ranking() {
    return repository.findAll();
  }
}
