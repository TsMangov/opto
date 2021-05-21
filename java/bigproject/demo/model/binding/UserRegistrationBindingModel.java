package bigproject.demo.model.binding;



import bigproject.demo.model.validators.FieldMatch;

import javax.validation.constraints.*;
import java.util.List;

@FieldMatch(
    first = "password",
    second = "confirmPassword"
)
public class UserRegistrationBindingModel {

  @NotBlank
  @Size(min = 3, max = 20,  message = "Username length must be between 3 and 20 characters")
  private String username;
  @NotBlank
  @Email
  private String email;
  @NotBlank
  @Size(min = 3)
  private String fullname;
  @NotBlank
  @Size(min = 5, max = 20)
  private String password;
  @NotBlank
  @Size(min = 5, max = 20)
  private String confirmPassword;
  @NotNull
  private Long jobTitle;
  @NotNull
  private Long zveno;
  @NotNull
  private List<String> role;

  public String getUsername() {
    return username;
  }

  public UserRegistrationBindingModel setUsername(String username) {
    this.username = username;
    return this;
  }

  public String getEmail() {
    return email;
  }

  public UserRegistrationBindingModel setEmail(String email) {
    this.email = email;
    return this;
  }

  public String getFullname() {
    return fullname;
  }

  public UserRegistrationBindingModel setFullname(String fullname) {
    this.fullname = fullname;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public UserRegistrationBindingModel setPassword(String password) {
    this.password = password;
    return this;
  }

  public String getConfirmPassword() {
    return confirmPassword;
  }

  public UserRegistrationBindingModel setConfirmPassword(String confirmPassword) {
    this.confirmPassword = confirmPassword;
    return this;
  }

  public Long getJobTitle() {
    return jobTitle;
  }

  public UserRegistrationBindingModel setJobTitle(Long jobTitle) {
    this.jobTitle = jobTitle;
    return this;
  }

  public Long getZveno() {
    return zveno;
  }

  public UserRegistrationBindingModel setZveno(Long zveno) {
    this.zveno = zveno;
    return this;
  }

  public List<String> getRole() {
    return role;
  }

  public UserRegistrationBindingModel setRole(List<String> role) {
    this.role = role;
    return this;
  }
}
