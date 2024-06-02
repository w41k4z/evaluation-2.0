package proj.eval.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import proj.eval.app.model.Category;

public interface CategoryRepository
  extends JpaRepository<Category, Long>, JpaSpecificationExecutor<Category> {}
