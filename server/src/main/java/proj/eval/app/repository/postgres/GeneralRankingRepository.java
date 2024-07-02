package proj.eval.app.repository.postgres;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import proj.eval.app.model.postgres.stat.GeneralRanking;

public interface GeneralRankingRepository
  extends JpaRepository<GeneralRanking, Integer> {
  public List<GeneralRanking> findAllByStage_Id(Long stageId);
}
