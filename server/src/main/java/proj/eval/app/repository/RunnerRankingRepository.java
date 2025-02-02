package proj.eval.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import proj.eval.app.model.stat.RunnerRanking;

public interface RunnerRankingRepository
  extends JpaRepository<RunnerRanking, Long> {}
