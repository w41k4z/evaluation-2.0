package proj.eval.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import proj.eval.app.model.StageRunner;

public interface StageRunnerRepository
  extends
    JpaRepository<StageRunner, Long>, JpaSpecificationExecutor<StageRunner> {}
