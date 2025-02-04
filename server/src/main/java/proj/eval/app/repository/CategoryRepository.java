package proj.eval.app.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import proj.eval.app.model.Category;

public interface CategoryRepository
    extends JpaRepository<Category, Long>, JpaSpecificationExecutor<Category> {
  Optional<Category> findByName(String name);
}
