package proj.eval.app.spec;

import org.springframework.data.jpa.domain.Specification;
import proj.eval.app.model.auth.User;

public class UserSpecification {

  public static Specification<User> hasState(Integer state) {
    return (root, query, criteriaBuilder) ->
      criteriaBuilder.equal(root.get("state"), state);
  }
}
