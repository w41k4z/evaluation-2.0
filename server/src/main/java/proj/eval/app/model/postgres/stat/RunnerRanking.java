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
import proj.eval.app.model.postgres.Runner;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "runner_ranking")
@Immutable
public class RunnerRanking {

  @Id
  private Long id;

  private Integer rank;

  @OneToOne
  @JoinColumn(name = "runner_id")
  private Runner runner;

  @Column(name = "total_score")
  private Integer score;

  @Column(name = "total_chrono")
  private String chrono;

  @Column(name = "total_final_chrono")
  private String finalChrono;

  @Column(name = "total_penalty")
  private String penalty;
}
