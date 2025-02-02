package proj.eval.app.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import proj.eval.app.model.TeamRunner;

public interface TeamRunnerRepository extends JpaRepository<TeamRunner, Long> {
  public List<TeamRunner> findAllByTeam_IdAndStage_Id(
    String teamId,
    Long stageId
  );
}
