package bigproject.demo.web;

import bigproject.demo.model.binding.ProductBindingModel;
import bigproject.demo.model.service.ProductServiceModel;
import bigproject.demo.service.CartItemService;
import bigproject.demo.service.CategoryService;
import bigproject.demo.service.ProductService;
import bigproject.demo.service.TopicService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;

@Controller
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;
    private final CartItemService cartItemService;
    private final ModelMapper modelMapper;
    private final CategoryService categoryService;
    private final TopicService topicService;

    public ProductController(ProductService productService, CartItemService cartItemService, ModelMapper modelMapper, CategoryService categoryService, TopicService topicService) {
        this.productService = productService;
        this.cartItemService = cartItemService;
        this.modelMapper = modelMapper;
        this.categoryService = categoryService;
        this.topicService = topicService;
    }


    @GetMapping("/all/{id}")
    public String getProducts(@PathVariable Long id, Model model){
        model.addAttribute("categoryId", id);
        model.addAttribute("categories", this.categoryService.getAll( ));
        model.addAttribute("allProducts", productService.getAllProducts());
        //todo fix the workjobid
        model.addAttribute("WorkJobId", Long.valueOf("0"));
        return "product";
    }


    @GetMapping("/materials/{topicId}/{categoryId}")
    public String getProductsForWorkJob(@PathVariable("topicId") Long topicId, @PathVariable("categoryId") Long categoryId, Model model){
        model.addAttribute("categoryId", categoryId );
        model.addAttribute("WorkJobId", topicId);
        model.addAttribute("WorkJobText", this.topicService.getTopicTextByTopicId(topicId) );
        model.addAttribute("categories", this.categoryService.getAll( ));
        model.addAttribute("allProducts", productService.getAllProducts());

        return "product";
    }


//todo add wanted category id in delete filter
    @DeleteMapping("/deleteProduct/{id}/{topicId}/{wantedId}")
    public String deleteProduct(@PathVariable("id") Long id,
                                @PathVariable("topicId") Long topicId,
                                @PathVariable("wantedId") Long wantedId, Model model){
        //todo cant delete when in cart
        this.productService.deleteById(id);
        return "redirect:/product/materials/"+topicId+"/"+wantedId;
    }


    @GetMapping("/add")
    public String addProduct(Model model){
        if (!model.containsAttribute("productBindingModel")){
            model.addAttribute("productBindingModel", new ProductBindingModel());
        }
        model.addAttribute("allCategories", this.categoryService.getAll());
        return "add-product";
    }

    @PostMapping("/add")
    public String addProductConfirm(
            @Valid ProductBindingModel productBindingModel,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            Principal principal) throws IOException {

//        System.out.println();
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("productBindingModel", productBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.productBindingModel", bindingResult);
            return "redirect:add";
        }

//        System.out.println();
        ProductServiceModel input = modelMapper.map(productBindingModel, ProductServiceModel.class);
        this.productService.addNewProduct( input );
//        System.out.println();

        return "redirect:all/-69";
    }



}
