package proj.eval.app.service;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import proj.eval.app.exception.StageException;
import proj.eval.app.model.Stage;
import proj.eval.app.model.TeamRunner;
import proj.eval.app.repository.StageRepository;
import proj.eval.app.spec.EntitySpecification;

@Service
public class StageService {

  private StageRepository repository;
  private TeamRunnerService teamRunnerService;
  private StageRunnerService stageRunnerService;
  private RunnersTimeService runnersTimeService;

  public StageService(
      StageRepository repository,
      TeamRunnerService teamRunnerService,
      StageRunnerService stageRunnerService,
      RunnersTimeService runnersTimeService) {
    this.repository = repository;
    this.teamRunnerService = teamRunnerService;
    this.stageRunnerService = stageRunnerService;
    this.runnersTimeService = runnersTimeService;
  }

  public Stage get(Long id) {
    Optional<Stage> stage = repository.findById(id);
    if (stage.isEmpty()) {
      throw new StageException("Stage with id `" + id + "` not found");
    }
    return stage.get();
  }

  public List<Stage> list() {
    EntitySpecification<Stage> entitySpecification = new EntitySpecification<>();
    Specification<Stage> spec = entitySpecification.equals("state", 0);
    return repository.findAll(spec);
  }

  public Stage create(
      Date startDate,
      Time startTime,
      Integer rank,
      String name,
      Double pathLength,
      Integer runnersPerTeam) {
    Stage stage = new Stage();
    stage.setStartDate(startDate);
    stage.setStartTime(startTime);
    stage.setName(name);
    stage.setPathLength(pathLength);
    stage.setRunnersPerTeam(runnersPerTeam);
    stage.setState(0);
    return repository.save(stage);
  }

  public Stage update(
      Long id,
      Date startDate,
      Time startTime,
      Integer rank,
      String name,
      Double pathLength,
      Integer runnersPerTeam) {
    Optional<Stage> stage = repository.findById(id);
    if (stage.isEmpty()) {
      throw new StageException("Stage with id `" + id + "` not found");
    }
    stage.get().setStartDate(startDate);
    stage.get().setStartTime(startTime);
    stage.get().setRank(rank);
    stage.get().setName(name);
    stage.get().setPathLength(pathLength);
    stage.get().setRunnersPerTeam(runnersPerTeam);
    return repository.save(stage.get());
  }

  public void assignRunners(
      String teamUsername,
      Long stageId,
      List<Long> runnerIds) {
    Stage stage = this.get(stageId);
    Integer limit = stage.getRunnersPerTeam();
    List<TeamRunner> existingRunners = teamRunnerService.runners(
        teamUsername,
        stage.getId());
    // No need to check if the runner belongs to the team as there will only be
    // runners from the current logged team shown in the UI
    // But there should always be a check here from the backend
    if (existingRunners.size() + runnerIds.size() > limit) {
      String message = "Cannot assign more than " + limit + " runners.";
      if (existingRunners.size() > 0) {
        message += " Your Team already has " +
            existingRunners.size() +
            " runners assigned.";
      }
      throw new StageException(message);
    }
    for (Long runnerId : runnerIds) {
      stageRunnerService.assignRunner(stageId, runnerId);
    }
  }

  public void assignRunnersTime(Long stageRunnersId, Timestamp arrivalTime) {
    this.runnersTimeService.assignRunnersTime(stageRunnersId, arrivalTime);
  }
}
