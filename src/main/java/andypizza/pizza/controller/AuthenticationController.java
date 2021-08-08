package andypizza.pizza.controller;

import andypizza.pizza.security.entity.AuthorizationUser;
import andypizza.pizza.security.entity.UserToken;
import andypizza.pizza.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping()
@AllArgsConstructor
public class AuthenticationController {
    private UserService userService;

    @PostMapping("/login")
    public UserToken login(@RequestBody AuthorizationUser user) {
        return userService.login(user);
    }
}
