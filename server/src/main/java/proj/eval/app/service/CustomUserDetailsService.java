package proj.eval.app.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import proj.eval.app.exception.UserException;
import proj.eval.app.model.postgres.auth.Role;
import proj.eval.app.model.postgres.auth.User;
import proj.eval.app.repository.postgres.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username)
    throws UsernameNotFoundException {
    Optional<User> user = userRepository.findByUsername(username);
    if (user.isEmpty()) {
      throw new UnsupportedOperationException(
        new UserException("User `" + username + "` not found")
      );
    }
    return new org.springframework.security.core.userdetails.User(
      username,
      user.get().getPassword(),
      true,
      true,
      true,
      true,
      this.getAuthorities(user.get().getRoles())
    );
  }

  private Collection<? extends GrantedAuthority> getAuthorities(
    Collection<Role> roles
  ) {
    List<GrantedAuthority> privileges = new ArrayList<>();
    for (Role role : roles) {
      privileges.add(new SimpleGrantedAuthority(role.getName()));
    }
    return privileges;
  }
}
