package bigproject.demo.web;


import bigproject.demo.model.binding.UserRegistrationBindingModel;
import bigproject.demo.model.service.UserRegistrationServiceModel;
import bigproject.demo.service.JobTitleService;
import bigproject.demo.service.UserService;
import bigproject.demo.service.ZvenaService;
import org.modelmapper.ModelMapper;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {
  private final JobTitleService jobTitleService;
  private final ZvenaService zvenaService;
  private final ModelMapper modelMapper;
  private final UserService userService;

  public UserController(JobTitleService jobTitleService, ZvenaService zvenaService, ModelMapper modelMapper,
                        UserService userService) {
    this.jobTitleService = jobTitleService;
    this.zvenaService = zvenaService;
    this.modelMapper = modelMapper;
    this.userService = userService;
  }

  @ModelAttribute("registrationBindingModel")
  public UserRegistrationBindingModel createBindingModel() {
    return new UserRegistrationBindingModel();
  }

  @GetMapping("/login")
  public String login(Model model) {
    model.addAttribute("count", 100);
    return "login";
  }

  @GetMapping("/register")
  public String register(Model model) {
    if(!model.containsAttribute("userExistsError")){
      model.addAttribute("userExistsError", false);
    }
    model.addAttribute("allJobTitles", this.jobTitleService.getAllJobTitles() );
    model.addAttribute("allZvena", this.zvenaService.getAllZvena());
    return "register";
  }

  @PostMapping("/register")
  public String registerAndLoginUser(
      @Valid UserRegistrationBindingModel registrationBindingModel,
      BindingResult bindingResult,
      RedirectAttributes redirectAttributes) {

    //todo make test for unique username and unique emeil

    if (bindingResult.hasErrors()) {
      redirectAttributes.addFlashAttribute("registrationBindingModel", registrationBindingModel);
      redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registrationBindingModel", bindingResult);
      return "redirect:/users/register";
    }

    if (userService.userNameExists(registrationBindingModel.getUsername())) {
      redirectAttributes.addFlashAttribute("registrationBindingModel", registrationBindingModel);
      redirectAttributes.addFlashAttribute("userExistsError", true);
//todo fix check for user
      return "redirect:/users/register";
    }

    UserRegistrationServiceModel userServiceModel = modelMapper
        .map(registrationBindingModel, UserRegistrationServiceModel.class);

    userService.registerAndLoginUser(userServiceModel);

    return "redirect:/category/all";
  }

  @PostMapping("/login-error")
  public String failedLogin(@ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
                                    String username,
                            RedirectAttributes attributes) {

    attributes.addFlashAttribute("bad_credentials", true);
    attributes.addFlashAttribute("username", username);

    return "redirect:/users/login";
  }

}
