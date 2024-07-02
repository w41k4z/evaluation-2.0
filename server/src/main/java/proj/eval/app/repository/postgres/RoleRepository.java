package proj.eval.app.repository.postgres;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import proj.eval.app.model.postgres.auth.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(String name);
}
