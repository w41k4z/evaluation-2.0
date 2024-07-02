package proj.eval.app.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import proj.eval.app.model.postgres.stat.TeamAllCategoryRanking;

public interface TeamAllCategoryRankingRepository
  extends JpaRepository<TeamAllCategoryRanking, Long> {}
