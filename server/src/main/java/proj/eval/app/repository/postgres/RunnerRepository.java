package proj.eval.app.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import proj.eval.app.model.postgres.Runner;

public interface RunnerRepository
  extends JpaRepository<Runner, Long>, JpaSpecificationExecutor<Runner> {}
