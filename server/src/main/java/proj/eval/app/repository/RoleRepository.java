package proj.eval.app.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import proj.eval.app.model.auth.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(String name);
}
