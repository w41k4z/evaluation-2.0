package proj.eval.app.spec;

import org.springframework.data.jpa.domain.Specification;
import proj.eval.app.model.postgres.stat.GeneralRanking;

public class GeneralRankingSpecification {

  public Specification<GeneralRanking> hasTeam(String teamId) {
    return (root, query, criteriaBuilder) ->
      criteriaBuilder.equal(root.get("runner").get("team").get("id"), teamId);
  }
}
