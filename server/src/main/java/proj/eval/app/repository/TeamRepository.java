package proj.eval.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import proj.eval.app.model.Team;

public interface TeamRepository extends JpaRepository<Team, String> {
}
