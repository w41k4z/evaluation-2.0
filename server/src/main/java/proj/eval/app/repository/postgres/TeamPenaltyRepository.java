package proj.eval.app.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import proj.eval.app.model.postgres.TeamPenalty;

public interface TeamPenaltyRepository
  extends JpaRepository<TeamPenalty, Long> {}
