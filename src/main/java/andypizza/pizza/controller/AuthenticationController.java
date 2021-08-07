package andypizza.pizza.controller;

import andypizza.pizza.security.entity.UserToken;
import andypizza.pizza.security.filters.UsernameAndPasswordAuthenticationRequest;
import andypizza.pizza.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@AllArgsConstructor
public class AuthenticationController {
    private UserService userService;

    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    public UserToken login(@RequestBody UsernameAndPasswordAuthenticationRequest request) {
        return userService.login(request);
    }
}
