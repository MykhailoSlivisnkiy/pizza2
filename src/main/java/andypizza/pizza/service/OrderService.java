package andypizza.pizza.service;

import andypizza.pizza.model.Order;
import andypizza.pizza.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {
    private OrderRepository orderRepository;

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public void create(Order account) {
        orderRepository.save(account);
    }
}
