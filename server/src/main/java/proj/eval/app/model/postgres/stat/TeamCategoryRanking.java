package proj.eval.app.model.postgres.stat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Immutable;
import proj.eval.app.model.postgres.Category;
import proj.eval.app.model.postgres.auth.User;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "team_category_ranking")
@Immutable
public class TeamCategoryRanking {

  @Id
  private Long id;

  private Integer rank;

  @OneToOne
  @JoinColumn(name = "team_id")
  private User team;

  @OneToOne
  @JoinColumn(name = "category_id")
  private Category category;

  @Column(name = "total_score")
  private Integer totalScore;
}
