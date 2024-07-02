package proj.eval.app.repository.postgres;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import proj.eval.app.model.postgres.stat.TeamCategoryRanking;

public interface TeamCategoryRankingRepository
  extends JpaRepository<TeamCategoryRanking, Long> {
  List<TeamCategoryRanking> findAllByCategory_Name(String categoryName);
}
