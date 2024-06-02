package proj.eval.app.spec;

import org.springframework.data.jpa.domain.Specification;

public class EntitySpecification<E> {

  public Specification<E> equals(String columnName, Object value) {
    return (root, query, criteriaBuilder) ->
      criteriaBuilder.equal(root.get(columnName), value.toString());
  }
}
