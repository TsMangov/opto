package bigproject.demo.model.service;

import java.util.List;

public class UserRegistrationServiceModel {

  private String username;
  private String email;
  private String fullname;
  private String password;
  private Long jobTitle;
  private Long zveno;
  private List<String> role;


  public String getUsername() {
    return username;
  }

  public UserRegistrationServiceModel setUsername(String username) {
    this.username = username;
    return this;
  }

  public String getEmail() {
    return email;
  }

  public UserRegistrationServiceModel setEmail(String email) {
    this.email = email;
    return this;
  }

  public String getFullname() {
    return fullname;
  }

  public UserRegistrationServiceModel setFullname(String fullname) {
    this.fullname = fullname;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public UserRegistrationServiceModel setPassword(String password) {
    this.password = password;
    return this;
  }

  public Long getJobTitle() {
    return jobTitle;
  }

  public UserRegistrationServiceModel setJobTitle(Long jobTitle) {
    this.jobTitle = jobTitle;
    return this;
  }

  public Long getZveno() {
    return zveno;
  }

  public UserRegistrationServiceModel setZveno(Long zveno) {
    this.zveno = zveno;
    return this;
  }

  public List<String> getRole() {
    return role;
  }

  public UserRegistrationServiceModel setRole(List<String> role) {
    this.role = role;
    return this;
  }
}
