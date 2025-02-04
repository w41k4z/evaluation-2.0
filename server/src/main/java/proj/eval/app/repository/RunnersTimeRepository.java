package proj.eval.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import proj.eval.app.model.RunnersTime;

public interface RunnersTimeRepository
    extends JpaRepository<RunnersTime, Long> {
}
