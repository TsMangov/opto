package bigproject.demo.model.entities;

import bigproject.demo.model.entities.enums.GenderEnum;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")
public class UserEntity extends BaseEntity {

  @Column(nullable = false, unique = true)
  private String username;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  private String fullname;

  @Column(nullable = false)
  private String email;

  @ManyToOne
  private JobTitleEntity jobTitle;

  @ManyToOne
  private ZvenaEntity zveno;

  @Enumerated(EnumType.STRING)
  private GenderEnum gender;

  @ManyToMany(fetch = FetchType.EAGER)
  private List<UserRoleEntity> roles = new ArrayList<>();

  public UserEntity() {
  }

  public String getUsername() {
    return username;
  }

  public UserEntity setUsername(String name) {
    this.username = name;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public UserEntity setPassword(String password) {
    this.password = password;
    return this;
  }

  public String getFullname() {
    return fullname;
  }

  public UserEntity setFullname(String fullname) {
    this.fullname = fullname;
    return this;
  }

  public String getEmail() {
    return email;
  }

  public UserEntity setEmail(String email) {
    this.email = email;
    return this;
  }

  public List<UserRoleEntity> getRoles() {
    return roles;
  }

  public UserEntity setRoles(List<UserRoleEntity> roles) {
    this.roles = roles;
    return this;
  }

  public UserEntity addRole(UserRoleEntity roleEntity) {
    this.roles.add(roleEntity);
    return this;
  }

  public JobTitleEntity getJobTitle() {
    return jobTitle;
  }

  public UserEntity setJobTitle(JobTitleEntity jobTitle) {
    this.jobTitle = jobTitle;
    return this;
  }

  public ZvenaEntity getZveno() {
    return zveno;
  }

  public UserEntity setZveno(ZvenaEntity zveno) {
    this.zveno = zveno;
    return this;
  }

  public GenderEnum getGender() {
    return gender;
  }

  public UserEntity setGender(GenderEnum gender) {
    this.gender = gender;
    return this;
  }
}
