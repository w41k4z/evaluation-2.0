package proj.eval.app.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import proj.eval.app.model.TeamRunner;

public interface TeamRunnerRepository extends JpaRepository<TeamRunner, Long> {
  public List<TeamRunner> findAllByTeamIdAndStageId(
    String teamId,
    Long stageId
  );
}
