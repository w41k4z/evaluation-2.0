package proj.eval.app.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import proj.eval.app.model.stat.TeamCategoryRanking;

public interface TeamCategoryRankingRepository
    extends JpaRepository<TeamCategoryRanking, Long> {
  List<TeamCategoryRanking> findAllByCategory_Name(String categoryName);
}
