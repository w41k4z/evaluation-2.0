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
import proj.eval.app.model.postgres.Stage;
import proj.eval.app.model.postgres.auth.User;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "team_ranking_details")
public class TeamRankingDetails {

  @Id
  private Integer rank;

  @OneToOne
  @JoinColumn(name = "team_id")
  private User team;

  @OneToOne
  @JoinColumn(name = "stage_id")
  private Stage stage;

  @Column(name = "total_score")
  private Integer score;
}
