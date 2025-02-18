package proj.eval.app.model.stat;

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
import proj.eval.app.model.Runner;
import proj.eval.app.model.Stage;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "general_ranking")
@Immutable
public class GeneralRanking {

  @Id
  private Integer id;

  private Integer rank;

  @OneToOne
  @JoinColumn(name = "runner_id")
  private Runner runner;

  @OneToOne
  @JoinColumn(name = "stage_id")
  private Stage stage;

  private String chrono;

  @Column(name = "final_chrono")
  private String finalChrono;

  private String penalty;

  private Integer score;
}
