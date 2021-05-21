package bigproject.demo.web;

import bigproject.demo.model.binding.OrderBindingModel;
import bigproject.demo.model.entities.CartItemEntity;
import bigproject.demo.service.CartItemService;
import bigproject.demo.service.OrderService;
import bigproject.demo.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {
    private final ProductService productService;
    private final CartItemService cartItemService;
    private final OrderService orderService;

    public CartController(ProductService productService, CartItemService cartItemService, OrderService orderService) {
        this.productService = productService;
        this.cartItemService = cartItemService;
        this.orderService = orderService;
    }

    @GetMapping("/all")
    private String allCartItems(Model model, Principal principal){
        String username = principal.getName();
        //todo with view model
        List<CartItemEntity> list = cartItemService.getUsersCartItemsWithNoOrders(username);
        model.addAttribute("allCartItems", list);
        if(!model.containsAttribute("orderBindingModel")){
            model.addAttribute("orderBindingModel", new OrderBindingModel());
        }
        if(!model.containsAttribute("noItemsFound")) {
            model.addAttribute("noItemsFound", false);
        }
        return "shopingcart";
    }

    @RequestMapping("/getCount")
    public ResponseEntity<String> countOfItems(Principal principal) {
            int count = this.cartItemService.getCartItemsCountByUserWithNoOrder(principal.getName());
            return new ResponseEntity<>(""+count, HttpStatus.OK);
    }

    @PostMapping("/increaseCount/{id}")
    public String increaseCount(@PathVariable Long id, Model model){
        this.cartItemService.increaseCountOfItem(id);
        return "redirect:/cart/all";
    }

    @PostMapping("/decreaseCount/{id}")
    public String decreaseCount(@PathVariable Long id, Model model){
        this.cartItemService.decreaseCountOfItem(id);
        return "redirect:/cart/all";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        System.out.println();
        this.cartItemService.deleteById(id);
        System.out.println();
        return "redirect:/cart/all";
    }

//    addToCart

//    @PostMapping("/addTo/{id}/{wantedId}")
//    public String addToCart(@PathVariable Long id, @PathVariable Long wantedId, Model model, Principal principal){
//        String username = principal.getName();
//
//        this.productService.addProductToUsersCart(id, username);
//        return "redirect:/product/all/"+wantedId;
//    }

    @PostMapping("/addTo/{id}/{wanted}")
    public String addToCart(@PathVariable("id") Long id,
                            @PathVariable("wanted") Long wantedId,
                            Model model, Principal principal){
        String username = principal.getName();
        System.out.println();
        this.productService.addProductToUsersCart(id, username);
        return "redirect:/product/all/"+wantedId;
    }

//todo tova e noviq kod
    @PostMapping("/addToByTopic/{id}/{wanted}/{topic}")
    public String addToCartByTopic(@PathVariable("id") Long id,
                                   @PathVariable("wanted") Long wantedId,
                                   @PathVariable("topic") Long topicId,
                                   Model model, Principal principal){
        String username = principal.getName();
        System.out.println();
        this.productService.addProductToUsersCartAndWantedTopic(id, username, topicId);
        return "redirect:/product/materials/"+topicId+"/"+wantedId;

    }

    @PostMapping("/all")
    public String orderCartItems(
            @Valid OrderBindingModel orderBindingModel,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            Principal principal) {

        int count = this.cartItemService.getCartItemsCountByUserWithNoOrder(principal.getName());
        if( count == 0){
            redirectAttributes.addFlashAttribute("orderBindingModel", orderBindingModel);
            redirectAttributes.addFlashAttribute("noItemsFound", true);
            return "redirect:all";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("orderBindingModel", orderBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.orderBindingModel", bindingResult);
            return "redirect:all";
        }
        this.cartItemService.addCartItemsToOrder(principal.getName(), orderBindingModel);

        return "redirect:all";
    }


}
