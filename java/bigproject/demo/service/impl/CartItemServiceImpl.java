package bigproject.demo.service.impl;

import bigproject.demo.model.binding.OrderBindingModel;
import bigproject.demo.model.entities.CartItemEntity;
import bigproject.demo.model.entities.OrderEntity;
import bigproject.demo.repository.CartItemRepository;
import bigproject.demo.service.CartItemService;
import bigproject.demo.service.OrderService;
import bigproject.demo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartItemServiceImpl implements CartItemService {
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final CartItemRepository cartItemRepository;
    private final OrderService orderService;


    public CartItemServiceImpl(ModelMapper modelMapper, UserService userService, CartItemRepository cartItemRepository, OrderService orderService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.cartItemRepository = cartItemRepository;
        this.orderService = orderService;
    }


    @Override
    public List<CartItemEntity> getUsersCartItemsWithNoOrders(String username) {
        List<CartItemEntity> lsit = this.cartItemRepository.findAllByUserEntity_Username(username);
        List<CartItemEntity> result = new ArrayList<>();
        for (CartItemEntity entity : lsit) {
            if(entity.getOrderEntity() == null){
                result.add(entity);
            }
        }
        return result;
    }

    @Override
    public int getCartItemsCountByUserWithNoOrder(String username) {
        List<CartItemEntity> lsit = this.cartItemRepository.findAllByUserEntity_Username(username);
        int result = 0;
        for (CartItemEntity entity : lsit) {
            if(entity.getOrderEntity() == null){
                result = result + entity.getCount();
            }
        }
        return result;
    }

    @Override
    public void decreaseCountOfItem(Long id) {
        //todo handle throw
        CartItemEntity entity = this.cartItemRepository.findById(id).orElseThrow();
        if(entity.getCount() == 1){
            cartItemRepository.deleteById(id);
        }else{
            entity.setCount(entity.getCount()-1);
            cartItemRepository.save(entity);
        }
    }

    @Override
    public void deleteById(Long id) {
        cartItemRepository.deleteById(id);
    }

    @Override
    public void addCartItemsToOrder(String username, OrderBindingModel orderBindingModel) {
        BigDecimal totalPriceOfItems = this.getTotalPriceOfItems(username);
        OrderEntity order = this.orderService.createOrder(username, orderBindingModel, totalPriceOfItems);
        List<CartItemEntity> list = this.cartItemRepository.findAllByUserEntity_Username(username);
        for (CartItemEntity entity : list) {
            if( entity.getOrderEntity() == null ){
                CartItemEntity newItem = new CartItemEntity();
                newItem.setOrderEntity(order);
                newItem.setCount(entity.getCount());
                newItem.setUserEntity(entity.getUserEntity());
                newItem.setProduct(entity.getProduct());
                newItem.setTopicEntity(entity.getTopicEntity());
                this.cartItemRepository.deleteById(entity.getId());
                this.cartItemRepository.save(newItem);
            }
        }
    }

    @Override
    public BigDecimal getTotalPriceOfItems(String username) {
        List<CartItemEntity> list = this.cartItemRepository.findAllByUserEntity_Username(username);
        BigDecimal result = BigDecimal.valueOf(0.0);
        for (CartItemEntity entity : list) {
            BigDecimal sum = entity.getProduct().getPrice().multiply(new BigDecimal(entity.getCount()));
                    result = result.add(sum);
        }
        return result;
    }

    @Override
    public List<CartItemEntity> getAllCartItemsByOrderId(Long id) {
        return this.cartItemRepository.findAllByOrderEntity_Id(id);
    }

    @Override
    public List<Long> listOfOrdersForTopic(Long id) {
        List<CartItemEntity> list = this.cartItemRepository.findAllByTopicEntity_Id(id);
        List<Long> result = new ArrayList<>();
        for (CartItemEntity entity : list) {
            result.add(entity.getOrderEntity().getId());
        }

        return result;
    }


    @Override
    public void increaseCountOfItem(Long id) {
        //todo handle throw
        CartItemEntity entity = this.cartItemRepository.findById(id).orElseThrow();
        entity.setCount(entity.getCount()+1);
        cartItemRepository.save(entity);
    }
}
