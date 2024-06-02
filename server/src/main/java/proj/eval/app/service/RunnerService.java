package proj.eval.app.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import proj.eval.app.enumeration.Authority;
import proj.eval.app.exception.RunnerException;
import proj.eval.app.model.Runner;
import proj.eval.app.model.auth.User;
import proj.eval.app.repository.RunnerRepository;
import proj.eval.app.spec.EntitySpecification;
import proj.eval.app.spec.RunnerSpecification;

@Service
public class RunnerService {

  private RunnerRepository repository;
  private UserService userService;

  public RunnerService(RunnerRepository repository, UserService userService) {
    this.repository = repository;
    this.userService = userService;
  }

  public List<Runner> list(UserDetails userDetails) {
    Boolean isAdmin = false;
    for (GrantedAuthority role : userDetails.getAuthorities()) {
      if (role.getAuthority().equals(Authority.ADMIN.name())) {
        isAdmin = true;
        break;
      }
    }
    EntitySpecification<Runner> entitySpec = new EntitySpecification<>();
    Specification<Runner> spec = entitySpec.equals("state", 0);
    // If admin, all runners are returned, otherwise only the runners of the current logged in team
    if (!isAdmin) {
      RunnerSpecification runnerSpec = new RunnerSpecification();
      spec =
        spec.and(
          runnerSpec.hasTeam(
            this.userService.findByUsername(userDetails.getUsername()).getId()
          )
        );
    }
    return repository.findAll(spec);
  }

  public Runner create(
    String name,
    String teamId,
    Integer number,
    Integer gender,
    Date birthDate
  ) {
    Runner runner = new Runner();
    runner.setName(name);
    User team = new User();
    team.setId(teamId);
    runner.setTeam(team);
    runner.setNumber(number);
    runner.setGender(gender);
    runner.setBirthDate(birthDate);
    runner.setState(0);
    return repository.save(runner);
  }

  public Runner update(
    Long id,
    String name,
    String teamId,
    Integer number,
    Integer gender,
    Date birthDate
  ) {
    Optional<Runner> runner = repository.findById(id);
    if (runner.isEmpty()) {
      throw new RunnerException("Runner with id: `" + id + "` not found");
    }
    runner.get().setName(name);
    User team = new User();
    team.setId(teamId);
    runner.get().setTeam(team);
    runner.get().setNumber(number);
    runner.get().setGender(gender);
    runner.get().setBirthDate(birthDate);
    return repository.save(runner.get());
  }
}
