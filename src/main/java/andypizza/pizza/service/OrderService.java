package andypizza.pizza.service;

import andypizza.pizza.constant.ErrorMessage;
import andypizza.pizza.dto.OrderDto;
import andypizza.pizza.exeption.NotFoundIdException;
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

    public Order findById(Long id) {
        return orderRepository.findById(id).orElseThrow(
                () -> new NotFoundIdException(String.format(ErrorMessage.PRODUCT_WAS_NOT_FOUND_BY_ID, id)));
    }

    public void create(Order account) {
        orderRepository.save(account);
    }

    public void update(OrderDto order) {
        Order orderToUpdate = findById(order.getId());
        orderToUpdate.setStatus(order.getStatus());

        orderRepository.save(orderToUpdate);
    }

}
