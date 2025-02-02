package proj.eval.app.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import proj.eval.app.exception.CategoryException;
import proj.eval.app.model.Category;
import proj.eval.app.model.Runner;
import proj.eval.app.repository.CategoryRepository;
import proj.eval.app.spec.EntitySpecification;
import proj.eval.app.util.ManualConnection;

@Service
public class CategoryService {

  private ManualConnection manualConnection;
  private CategoryRepository repository;
  private RunnerService runnerService;

  public CategoryService(
    ManualConnection manualConnection,
    CategoryRepository repository,
    RunnerService runnerService
  ) {
    this.manualConnection = manualConnection;
    this.repository = repository;
    this.runnerService = runnerService;
  }

  public Category findByName(String name) {
    Optional<Category> category = this.repository.findByName(name);
    if (category.isEmpty()) {
      throw new CategoryException(
        "Category with name: `" + name + "` not found"
      );
    }
    return category.get();
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

  public void assignCategories() throws SQLException {
    List<Runner> runners = this.runnerService.allRunners();
    List<Category> categories = this.list();
    StringBuilder sql = new StringBuilder(
      "INSERT INTO runner_categories(runner_id, category_id) VALUES("
    );
    for (Runner runner : runners) {
      for (Category category : categories) {
        int age =
          LocalDate.now().getYear() -
          runner.getBirthDate().toLocalDate().getYear();
        switch (category.getName()) {
          case "Homme":
            if (runner.getGender() == 1) {
              sql.append(runner.getId());
              sql.append(",");
              sql.append(category.getId());
              sql.append("), (");
            }
            break;
          case "Femme":
            if (runner.getGender() == 0) {
              sql.append(runner.getId());
              sql.append(",");
              sql.append(category.getId());
              sql.append("), (");
            }
            break;
          case "Junior":
            if (age < 18) {
              sql.append(runner.getId());
              sql.append(",");
              sql.append(category.getId());
              sql.append("), (");
            }
            break;
          case "Senior":
            if (age >= 18) {
              sql.append(runner.getId());
              sql.append(",");
              sql.append(category.getId());
              sql.append("), (");
            }
            break;
          default:
            throw new CategoryException(
              "Category not yet implemented: " + category.getName()
            );
        }
      }
    }
    Connection connection = manualConnection.getConnection();
    String query = sql.substring(0, sql.length() - 3);
    try {
      connection.createStatement().execute(query);
    } catch (SQLException e) {
      // Nothing to do if there is a duplicate entry
    }
    connection.close();
  }
}
