package proj.eval.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import proj.eval.app.model.TeamPenalty;

public interface TeamPenaltyRepository
    extends JpaRepository<TeamPenalty, Long> {
}
