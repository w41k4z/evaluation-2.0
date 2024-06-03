package proj.eval.app.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssignRunnersTimeRequest {

  @NotBlank(message = "Stage Runners Id is required")
  private String stageRunnersId;

  @NotBlank(message = "Arrival Time is required")
  private String arrivalTime;
}
