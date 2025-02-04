package proj.eval.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import proj.eval.app.model.stat.TeamAllCategoryRanking;

public interface TeamAllCategoryRankingRepository
    extends JpaRepository<TeamAllCategoryRanking, Long> {
}
