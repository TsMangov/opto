package bigproject.demo.service.impl;

import bigproject.demo.model.binding.OrderBindingModel;
import bigproject.demo.model.entities.OrderEntity;
import bigproject.demo.model.viewModels.OrderViewModel;
import bigproject.demo.repository.OrderRepository;
import bigproject.demo.service.OrderService;
import bigproject.demo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final ModelMapper modelMapper;
    private final OrderRepository orderRepository;
    private final UserService userService;

    public OrderServiceImpl(ModelMapper modelMapper, OrderRepository orderRepository, UserService userService) {
        this.modelMapper = modelMapper;
        this.orderRepository = orderRepository;
        this.userService = userService;
    }


    @Override
    public OrderEntity findById(long id) {
        //todo handle throw
        return this.orderRepository.findById(id).orElseThrow();
    }

    @Override
    public OrderEntity createOrder(String username, OrderBindingModel orderBindingModel, BigDecimal totalPriceOfItems) {
        OrderEntity order = modelMapper.map(orderBindingModel, OrderEntity.class);
        order.setUserEntity( this.userService.getUserByUsername(username) );
        order.setTotalPrice(totalPriceOfItems);
        return this.orderRepository.save(order);
    }

    @Override
    public List<OrderViewModel> getAllOrders() {
        List<OrderEntity> list = this.orderRepository.findAll();
        List<OrderViewModel> result = new ArrayList<>();
        for (OrderEntity orderEntity : list) {
            OrderViewModel view = modelMapper.map(orderEntity, OrderViewModel.class);
            view.setUsername(orderEntity.getUserEntity().getUsername());
            view.setFullName(orderEntity.getUserEntity().getFullname());
            result.add(view);
        }
        return result;
    }

    @Override
    public void finishOrderById(Long id) {
        //todo handle throw
        OrderEntity order = this.orderRepository.findById(id).orElseThrow();
        order.setFinished(true);
        this.orderRepository.save(order);
    }
}
