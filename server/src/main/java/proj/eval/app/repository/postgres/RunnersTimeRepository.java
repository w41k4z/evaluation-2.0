package proj.eval.app.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import proj.eval.app.model.postgres.RunnersTime;

public interface RunnersTimeRepository
  extends JpaRepository<RunnersTime, Long> {}
