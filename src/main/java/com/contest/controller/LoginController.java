package com.contest.controller;

import com.contest.model.security.User;
import com.contest.service.RegistrationService;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
@AllArgsConstructor
@CrossOrigin("http://localhost:3000")
@RestController
@EqualsAndHashCode
public class LoginController {
  private RegistrationService registrationService;

  @PostMapping(value = "/signup" ,consumes = MediaType.APPLICATION_JSON_VALUE)
  public String register(@RequestBody User request) {
    return registrationService.signUp(request);
  }

  @PostMapping(value = "/login" ,consumes = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody String login(@RequestBody User users) {
    if(!Objects.equals(users.getPassword(), registrationService.getPassword(users.getUsername()))){
      throw new RuntimeException("user not found");
    }
    else {
      registrationService.loadUserByUsername(users.getUsername());
      return "connexion faite";
    }
  }
}