package proj.eval.app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import proj.eval.app.model.auth.User;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "runners")
public class Runner {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @OneToOne
  @JoinColumn(name = "team_id")
  private User team;

  private String number;

  private Integer gender;

  @Column(name = "birth_date")
  private Date birthDate;

  private Integer state;
}
