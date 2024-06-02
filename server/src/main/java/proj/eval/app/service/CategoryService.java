package proj.eval.app.service;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import proj.eval.app.exception.CategoryException;
import proj.eval.app.model.Category;
import proj.eval.app.repository.CategoryRepository;
import proj.eval.app.spec.EntitySpecification;

@Service
public class CategoryService {

  private CategoryRepository repository;

  public CategoryService(CategoryRepository repository) {
    this.repository = repository;
  }

  public List<Category> list() {
    EntitySpecification<Category> entitySpec = new EntitySpecification<>();
    Specification<Category> spec = entitySpec.equals("state", 0);
    return repository.findAll(spec);
  }

  public Category create(String name) {
    Category category = new Category();
    category.setName(name);
    category.setState(0);
    return repository.save(category);
  }

  public Category update(Long id, String name) {
    Optional<Category> category = repository.findById(id);
    if (category.isEmpty()) {
      throw new CategoryException("Category with id: `" + id + "` not found");
    }
    category.get().setName(name);
    return repository.save(category.get());
  }
}
