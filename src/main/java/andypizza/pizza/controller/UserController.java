package andypizza.pizza.controller;

import andypizza.pizza.dto.UserDto;
import andypizza.pizza.model.User;
import andypizza.pizza.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
@CrossOrigin
public class UserController {
    private UserService userService;

    @GetMapping("/{id}")
    public User findUserById(@PathVariable Long id) {
       return userService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody UserDto user) {
        userService.create(user);
    }
}
