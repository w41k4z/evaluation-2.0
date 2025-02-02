package proj.eval.app.model;

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
@Table(name = "teams")
@Immutable
public class Team {

  @Id
  private String id;

  private String name;
}
