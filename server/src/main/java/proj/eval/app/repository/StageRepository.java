package proj.eval.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import proj.eval.app.model.Stage;

public interface StageRepository
    extends JpaRepository<Stage, Long>, JpaSpecificationExecutor<Stage> {
}
