package proj.eval.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import proj.eval.app.model.stat.GeneralRanking;

public interface GeneralRankingRepository
  extends JpaRepository<GeneralRanking, Integer> {}
