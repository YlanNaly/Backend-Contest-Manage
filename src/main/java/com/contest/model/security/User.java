package com.contest.model.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;
import java.util.Collections;

@NoArgsConstructor
@Entity
@Table(name = "\"users\"")
@Data
@Builder
@AllArgsConstructor
public class User implements UserDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @NotEmpty(message = "should be not empty")
  @NotBlank(message = "should be not blank")
  @Column(nullable = false)
  private String password;
  @NotEmpty(message = "should be not empty")
  @Column(nullable = false)
  @NotBlank(message = "should be not blank")
  private String username;
  private boolean locked;
  @Column(nullable = false)
  private Roles roles;
  private boolean enabled;
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_"+roles.toString());
    return Collections.singletonList(authority);
  }
  public enum Roles{
    ADMIN,
    VISITOR
  }
  @Override
  public String getPassword() {
    return password;
  }

  public void setPassword(String encryptpassword) {
    this.password = encryptpassword;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return !locked;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return enabled;
  }
}
