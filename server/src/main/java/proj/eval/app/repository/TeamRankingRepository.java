package proj.eval.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import proj.eval.app.model.stat.TeamRanking;

public interface TeamRankingRepository
  extends JpaRepository<TeamRanking, Integer> {}
