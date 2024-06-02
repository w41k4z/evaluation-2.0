package proj.eval.app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Immutable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "team_runners")
@Immutable
public class TeamRunner {

  @Id
  private Long id;

  @Column(name = "stage_id")
  private Long stageId;

  @Column(name = "team_id")
  private String teamId;

  @Column(name = "runner_id")
  private Long runnerId;
}
