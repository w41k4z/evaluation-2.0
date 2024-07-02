package proj.eval.app.service;

import java.util.Collections;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import proj.eval.app.exception.RoleException;
import proj.eval.app.exception.UserException;
import proj.eval.app.model.postgres.auth.Role;
import proj.eval.app.model.postgres.auth.User;
import proj.eval.app.repository.postgres.RoleRepository;
import proj.eval.app.repository.postgres.UserRepository;
import proj.eval.app.spec.EntitySpecification;

@Service
public class UserService {

  private PasswordEncoder passwordEncoder;
  private UserRepository repository;
  private RoleRepository roleRepository;

  public UserService(
    PasswordEncoder passwordEncoder,
    UserRepository repository,
    RoleRepository roleRepository
  ) {
    this.passwordEncoder = passwordEncoder;
    this.repository = repository;
    this.roleRepository = roleRepository;
  }

  public User findByUsername(String username) {
    Optional<User> user = repository.findByUsername(username);
    if (user.isEmpty()) {
      throw new UserException("User `" + username + "` not found");
    }
    return user.get();
  }

  public User createUser(
    String name,
    String username,
    String password,
    String role
  ) throws RoleException {
    Optional<Role> userRole = roleRepository.findByName(role);
    if (userRole.isEmpty()) {
      throw new RoleException("Role `" + role + "` not found");
    }
    User user = new User();
    user.setName(name);
    user.setUsername(username);
    user.setPassword(this.passwordEncoder.encode(password));
    user.setRoles(Collections.singleton(userRole.get()));
    user.setState(0);
    return repository.save(user);
  }

  public Page<User> getUsers(Integer page, Integer size) {
    EntitySpecification<User> userSpecification = new EntitySpecification<>();
    Specification<User> spec = userSpecification.equals("state", 0);
    return repository.findAll(spec, PageRequest.of(page, size));
  }
}
