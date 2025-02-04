package proj.eval.app.model;

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
import proj.eval.app.model.auth.User;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "team_runners")
@Immutable
public class TeamRunner {

  @Id
  private Long id;

  @OneToOne
  @JoinColumn(name = "stage_id")
  private Stage stage;

  @OneToOne
  @JoinColumn(name = "team_id")
  private User team;

  @OneToOne
  @JoinColumn(name = "runner_id")
  private Runner runner;

  private String chrono;

  @Column(name = "final_chrono")
  private String finalChrono;

  private String penalty;
}
