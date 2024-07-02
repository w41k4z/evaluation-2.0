package proj.eval.app.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import proj.eval.app.model.postgres.stat.TeamRanking;

public interface TeamRankingRepository
  extends JpaRepository<TeamRanking, Integer> {}
