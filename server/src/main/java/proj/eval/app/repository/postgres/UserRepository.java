package proj.eval.app.repository.postgres;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import proj.eval.app.model.postgres.auth.User;

public interface UserRepository
  extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {
  Optional<User> findByUsername(String username);
}
