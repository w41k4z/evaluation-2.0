package proj.eval.app.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssignPenaltyRequest {

  @NotBlank(message = "The team id is required")
  private String teamId;

  @NotBlank(message = "The penalty time is required")
  private String penaltyTime;
}
