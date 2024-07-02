package proj.eval.app.spec;

import org.springframework.data.jpa.domain.Specification;
import proj.eval.app.model.postgres.Runner;

public class RunnerSpecification {

  public Specification<Runner> hasTeam(String teamId) {
    return (root, query, criteriaBuilder) ->
      criteriaBuilder.equal(root.get("team").get("id"), teamId);
  }
}
