package proj.eval.app.service;

import java.util.List;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import proj.eval.app.model.postgres.Runner;
import proj.eval.app.model.postgres.StageRunner;
import proj.eval.app.repository.postgres.StageRunnerRepository;
import proj.eval.app.spec.EntitySpecification;

@Service
public class StageRunnerService {

  private StageRunnerRepository repository;

  public StageRunnerService(StageRunnerRepository repository) {
    this.repository = repository;
  }

  public List<StageRunner> listByStageId(Long stageId) {
    EntitySpecification<StageRunner> entitySpec = new EntitySpecification<>();
    Specification<StageRunner> spec = entitySpec.equals("stageId", stageId);
    return repository.findAll(spec);
  }

  public void assignRunner(Long stageId, Long runnerId) {
    StageRunner stageRunner = new StageRunner();
    stageRunner.setStageId(stageId);
    Runner runner = new Runner();
    runner.setId(runnerId);
    stageRunner.setRunner(runner);
    repository.save(stageRunner);
  }
}
