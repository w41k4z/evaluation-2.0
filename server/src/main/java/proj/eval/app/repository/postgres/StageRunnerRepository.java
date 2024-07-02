package proj.eval.app.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import proj.eval.app.model.postgres.StageRunner;

public interface StageRunnerRepository
  extends
    JpaRepository<StageRunner, Long>, JpaSpecificationExecutor<StageRunner> {}
