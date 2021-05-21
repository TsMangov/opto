package bigproject.demo.service.impl;


import bigproject.demo.model.entities.JobTitleEntity;
import bigproject.demo.model.entities.UserEntity;
import bigproject.demo.model.entities.UserRoleEntity;
import bigproject.demo.model.entities.ZvenaEntity;
import bigproject.demo.model.entities.enums.UserRole;
import bigproject.demo.model.service.UserRegistrationServiceModel;
import bigproject.demo.repository.UserRepository;
import bigproject.demo.repository.UserRoleRepository;
import bigproject.demo.service.JobTitleService;
import bigproject.demo.service.UserService;
import bigproject.demo.service.ZvenaService;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
  private final JobTitleService jobTitleService;
  private final ZvenaService zvenaService;
  private final UserRoleRepository userRoleRepository;
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final ModelMapper modelMapper;
  private final GigaAppDBUserService gigaAppDBUserService;
  private final PasswordEncoder encoder;

  public UserServiceImpl(JobTitleService jobTitleService, ZvenaService zvenaService, UserRoleRepository userRoleRepository,
                         UserRepository userRepository,
                         PasswordEncoder passwordEncoder,
                         ModelMapper modelMapper,
                         GigaAppDBUserService gigaAppDBUserService,
                         PasswordEncoder encoder) {
    this.jobTitleService = jobTitleService;
    this.zvenaService = zvenaService;
    this.userRoleRepository = userRoleRepository;
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.modelMapper = modelMapper;
    this.gigaAppDBUserService = gigaAppDBUserService;
    this.encoder = encoder;
  }


  @Override
  public void seedUsers() {

    if (userRepository.count() == 0) {

      for (UserRole role : UserRole.values()) {
        UserRoleEntity roleEntity = new UserRoleEntity().setRole(role);
        userRoleRepository.save(roleEntity);
      }


//      UserRoleEntity adminRole = new UserRoleEntity().setRole(UserRole.ADMIN);
//      UserRoleEntity userRole = new UserRoleEntity().setRole(UserRole.USER);
//
//      userRoleRepository.saveAll(List.of(adminRole, userRole));

      UserEntity admin = new UserEntity().setUsername("admin").setFullname("Admin Adminov").setPassword(passwordEncoder.encode("topsecret")).setEmail("abv11@abv.bg");
      UserEntity user = new UserEntity().setUsername("user").setFullname("Bai Ivan").setPassword(passwordEncoder.encode("topsecret")).setEmail("abv2222@abv.bg");
      UserEntity sklad = new UserEntity().setUsername("sklad").setFullname("skalda Ivan").setPassword(passwordEncoder.encode("secret")).setEmail("abv2222@abv.bg");
      UserEntity proizvodstvo = new UserEntity().setUsername("proiz").setFullname("proizvodstvo").setPassword(passwordEncoder.encode("secret")).setEmail("abv2222@abv.bg");
      UserEntity otk = new UserEntity().setUsername("otkotk").setFullname("otk Ivan").setPassword(passwordEncoder.encode("secret")).setEmail("abv2222@abv.bg");
      //todo fix throw null
      admin.setRoles(List.of(userRoleRepository.findByRole(UserRole.ADMIN).orElseThrow(), userRoleRepository.findByRole(UserRole.USER).orElseThrow()));
      user.setRoles(List.of( userRoleRepository.findByRole(UserRole.USER).orElseThrow()  ));
      sklad.setRoles(List.of( userRoleRepository.findByRole(UserRole.SKLAD).orElseThrow()  ));
      proizvodstvo.setRoles(List.of( userRoleRepository.findByRole(UserRole.PROIZVODSTVO).orElseThrow()  ));
      otk.setRoles(List.of( userRoleRepository.findByRole(UserRole.OTK).orElseThrow()  ));

      userRepository.saveAll(List.of(admin, user, sklad, proizvodstvo, otk ));
    }
  }

  @Override
  public void registerAndLoginUser(UserRegistrationServiceModel serviceModel) {
    UserEntity newUser = new UserEntity();
    newUser = modelMapper.map(serviceModel, UserEntity.class);
    newUser.setPassword(passwordEncoder.encode(serviceModel.getPassword()));
//    newUser.setRoles(new ArrayList<>());
    for (String roleName : serviceModel.getRole()) {
      UserRole role = UserRole.valueOf(roleName);

      UserRoleEntity userRoleEntity = userRoleRepository.findByRole(role).orElseThrow(
              () -> new IllegalStateException("USER role not found. Please seed the roles."));

      newUser.addRole(userRoleEntity);
    }

//    UserRoleEntity userRole =  userRoleRepository.
//            findByRole(UserRole.USER).orElseThrow(
//                () -> new IllegalStateException("USER role not found. Please seed the roles."));
//    newUser.addRole(userRole);

    JobTitleEntity job = this.jobTitleService.findJobByTitle(serviceModel.getJobTitle());
    newUser.setJobTitle(job);
    ZvenaEntity zveno = this.zvenaService.findZvenoById(serviceModel.getZveno());
    newUser.setZveno(zveno);

    newUser = this.userRepository.saveAndFlush(newUser);

    UserDetails principal = gigaAppDBUserService.loadUserByUsername(newUser.getUsername());

    Authentication authentication = new UsernamePasswordAuthenticationToken(
            principal,
            newUser.getPassword(),
            principal.getAuthorities()
    );

    SecurityContextHolder.getContext().setAuthentication(authentication);
  }

  @Override
  public boolean userNameExists(String username) {
    return userRepository.findByUsername(username).isPresent();
  }

    @Override
    public UserEntity getUserByUsername(String username) {
      return this.userRepository.findByUsername(username).orElseThrow();
    }
}
