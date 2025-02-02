package proj.eval.app.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateRunnerRequest {

  @NotBlank(message = "Name is required")
  private String name;

  @NotBlank(message = "Team id is required")
  private String teamId;

  @NotNull(message = "Number is required")
  private Integer number;

  @NotNull(message = "Gender is required")
  private Integer gender;

  @NotNull(message = "Birth date is required")
  private LocalDate birthDate;
}
