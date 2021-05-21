package bigproject.demo.web;

import bigproject.demo.model.entities.CartItemEntity;
import bigproject.demo.model.viewModels.OrderViewModel;
import bigproject.demo.service.CartItemService;
import bigproject.demo.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;
    private final CartItemService cartItemService;

    public OrderController(OrderService orderService, CartItemService cartItemService) {
        this.orderService = orderService;
        this.cartItemService = cartItemService;
    }

    @GetMapping("/all")
    private String allOrders(Model model){
        List<OrderViewModel> list = orderService.getAllOrders();
        model.addAttribute("allOrders", list);
        return "orders";
    }


    @GetMapping("/viewAllItems/{id}")
    private String viewAllItemsInOrder(@PathVariable Long id,Model model){
        //todo with view model
        List<CartItemEntity> allItemsInOrder = this.cartItemService.getAllCartItemsByOrderId(id);
        model.addAttribute("allItemsInOrder", allItemsInOrder);
        model.addAttribute("order", this.orderService.findById(id));
        return "itemsInOrders";
    }

    @PostMapping("/finish/{id}")
    public String finishOrder(@PathVariable Long id, Model model){

        this.orderService.finishOrderById(id);
        System.out.println();
        return "redirect:/order/all";
    }

}
