package proj.eval.app.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateStageRequest {

  @NotNull(message = "Starting date is required")
  private String startDate;

  @NotNull(message = "Starting time is required")
  private String startTime;

  @NotNull(message = "Rank is required")
  private Integer rank;

  @NotBlank(message = "Name is required")
  private String name;

  @NotNull(message = "Path length is required")
  private Double pathLength;

  @NotNull(message = "Runners per team is required")
  private Integer runnersPerTeam;
}
