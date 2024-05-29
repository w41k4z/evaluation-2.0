package proj.eval.app.service;

import java.util.Collections;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import proj.eval.app.exception.RoleException;
import proj.eval.app.model.auth.Role;
import proj.eval.app.model.auth.User;
import proj.eval.app.repository.RoleRepository;
import proj.eval.app.repository.UserRepository;
import proj.eval.app.spec.UserSpecification;

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

  public User createUser(String username, String password, String role)
    throws RoleException {
    Optional<Role> userRole = roleRepository.findByName(role);
    if (userRole.isEmpty()) {
      throw new RoleException("Role `" + role + "` not found");
    }
    User user = new User();
    user.setUsername(username);
    user.setPassword(this.passwordEncoder.encode(password));
    user.setRoles(Collections.singleton(userRole.get()));
    user.setState(0);
    return repository.save(user);
  }

  public Page<User> getUsers(Integer page, Integer size) {
    Specification<User> spec = UserSpecification.hasState(0);
    return repository.findAll(spec, PageRequest.of(page, size));
  }
}
