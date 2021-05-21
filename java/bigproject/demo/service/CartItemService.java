package bigproject.demo.service;

import bigproject.demo.model.binding.OrderBindingModel;
import bigproject.demo.model.entities.CartItemEntity;

import java.math.BigDecimal;
import java.util.List;

public interface CartItemService {
    List<CartItemEntity> getUsersCartItemsWithNoOrders(String username);

    int getCartItemsCountByUserWithNoOrder(String username);

    void increaseCountOfItem(Long id);

    void decreaseCountOfItem(Long id);

    void deleteById(Long id);

    void addCartItemsToOrder(String username, OrderBindingModel orderBindingModel);

    BigDecimal getTotalPriceOfItems(String username);

    List<CartItemEntity> getAllCartItemsByOrderId(Long id);

    List<Long> listOfOrdersForTopic(Long id);
}
