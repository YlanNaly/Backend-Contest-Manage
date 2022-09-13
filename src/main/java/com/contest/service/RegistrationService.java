package com.contest.service;

import com.contest.model.security.User;
import com.contest.repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService implements UserDetailsService {

  private final UsersRepository usersRepo;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private final static String User_error = "user with name %s not found";

  public String getPassword(String name) {
    return usersRepo.findUsersByUsernameIs(name).get().getPassword();
  }
  @Override
  public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
    return usersRepo.findByUsername(name)
            .orElseThrow(() -> new UsernameNotFoundException(String.format(User_error, name)));
  }
  public String signUp(User appUser) {
    boolean exist = usersRepo.findByUsername(appUser.getUsername())
            .isPresent();
    if (exist) {
      throw new IllegalStateException("name already taken please add number");
    }
    String encryptpassword = bCryptPasswordEncoder.encode(appUser.getPassword());
    appUser.setPassword(encryptpassword);
    usersRepo.save(appUser);
    return "encryptpassword";
  }
}