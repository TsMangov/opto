package bigproject.demo.web;

import bigproject.demo.model.binding.CategoryBindingModel;
import bigproject.demo.model.binding.CommentBindingModel;
import bigproject.demo.model.service.CategoryServiceModel;
import bigproject.demo.model.service.CommentServiceModel;
import bigproject.demo.model.service.TopicServiceModel;
import bigproject.demo.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    public CategoryController(CategoryService categoryService, ModelMapper modelMapper) {
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }



    @GetMapping("/all")
    public String home(Model model) {
        model.addAttribute("allCategories", this.categoryService.getAll());
        return "home";
    }


    @GetMapping("/add")
    public String addCategory(Model model){
        if (!model.containsAttribute("categoryBindingModel")){
            model.addAttribute("categoryBindingModel", new CategoryBindingModel());
        }

        return "add-category";
    }
/*
    @PostMapping("/add")
    public String addCategoryConfirm( @ModelAttribute("categoryBindingModel") CategoryBindingModel categoryBindingModel) throws IOException {
        this.categoryService.saveCategory(modelMapper.map(categoryBindingModel, CategoryServiceModel.class));
            return "redirect:all";
    }

 */
@PostMapping("/add")
public String addCategoryConfirm(  @Valid CategoryBindingModel categoryBindingModel,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes ) throws IOException {

    System.out.println();
    if (bindingResult.hasErrors()) {
        redirectAttributes.addFlashAttribute("categoryBindingModel", categoryBindingModel);
        redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.categoryBindingModel", bindingResult);

        return "redirect:add";
    }

    this.categoryService.saveCategory(modelMapper.map(categoryBindingModel, CategoryServiceModel.class));

    return "redirect:all";
}


}
