package proj.eval.app.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import proj.eval.app.model.postgres.Team;

public interface TeamRepository extends JpaRepository<Team, String> {}
