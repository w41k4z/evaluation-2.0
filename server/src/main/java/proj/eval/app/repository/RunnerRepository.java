package proj.eval.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import proj.eval.app.model.Runner;

public interface RunnerRepository
    extends JpaRepository<Runner, Long>, JpaSpecificationExecutor<Runner> {
}
