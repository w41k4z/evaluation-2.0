package proj.eval.app.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import proj.eval.app.model.postgres.Stage;

public interface StageRepository
  extends JpaRepository<Stage, Long>, JpaSpecificationExecutor<Stage> {}
