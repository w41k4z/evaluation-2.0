package proj.eval.app.model.postgres;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.sql.Date;
import java.sql.Time;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "stages")
public class Stage {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "start_date")
  private Date startDate;

  @Column(name = "start_time")
  private Time startTime;

  private Integer rank;

  private String name;

  @Column(name = "path_length")
  private Double pathLength;

  @Column(name = "runners_per_team")
  private Integer runnersPerTeam;

  private Integer state;
}
