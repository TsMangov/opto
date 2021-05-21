package bigproject.demo.service;


import bigproject.demo.model.entities.UserEntity;
import bigproject.demo.model.service.UserRegistrationServiceModel;

public interface UserService {

  void seedUsers();

  void registerAndLoginUser(UserRegistrationServiceModel serviceModel);

  boolean userNameExists(String username);

  UserEntity getUserByUsername(String username);
}
