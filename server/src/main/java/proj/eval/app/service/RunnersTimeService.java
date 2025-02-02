package proj.eval.app.service;

import java.sql.Timestamp;
import org.springframework.stereotype.Service;
import proj.eval.app.model.RunnersTime;
import proj.eval.app.repository.RunnersTimeRepository;

@Service
public class RunnersTimeService {

  private RunnersTimeRepository repository;

  public RunnersTimeService(RunnersTimeRepository repository) {
    this.repository = repository;
  }

  public void assignRunnersTime(Long stageRunnersId, Timestamp arrivalTime) {
    RunnersTime runnersTime = new RunnersTime();
    runnersTime.setStageRunnersId(stageRunnersId);
    runnersTime.setArrivalTime(arrivalTime);
    repository.save(runnersTime);
  }
}
