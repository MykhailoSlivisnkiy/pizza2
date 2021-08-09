package andypizza.pizza.controller;

import andypizza.pizza.dto.OrderDto;
import andypizza.pizza.model.Order;
import andypizza.pizza.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
@CrossOrigin
public class OrderController {
    private OrderService orderService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Order> findAllOrders(){
        return orderService.findAllTodayOrders();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestBody Order order) {
        orderService.create(order);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public void updateAccount(@RequestBody OrderDto order) {
        orderService.update(order);
    }
}
