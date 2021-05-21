package bigproject.demo.service;

import bigproject.demo.model.binding.OrderBindingModel;
import bigproject.demo.model.entities.OrderEntity;
import bigproject.demo.model.viewModels.OrderViewModel;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService {

    OrderEntity findById(long id);

    OrderEntity createOrder(String username, OrderBindingModel orderBindingModel, BigDecimal totalPriceOfItems);

    List<OrderViewModel> getAllOrders();

    void finishOrderById(Long id);
}
