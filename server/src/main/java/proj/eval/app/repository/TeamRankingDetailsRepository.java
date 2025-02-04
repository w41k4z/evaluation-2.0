package proj.eval.app.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import proj.eval.app.model.stat.TeamRankingDetails;

public interface TeamRankingDetailsRepository
    extends JpaRepository<TeamRankingDetails, Integer> {
  public List<TeamRankingDetails> findAllByTeam_Id(String teamId);
}
