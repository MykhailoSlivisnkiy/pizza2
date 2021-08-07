package andypizza.pizza.service;

import andypizza.pizza.constant.ErrorMessage;
import andypizza.pizza.exeption.NotFoundIdException;
import andypizza.pizza.model.User;
import andypizza.pizza.repository.UserRepository;
import andypizza.pizza.security.entity.UserToken;
import andypizza.pizza.security.filters.UsernameAndPasswordAuthenticationRequest;
import andypizza.pizza.security.jwt.JwtTokenProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;
    private JwtTokenProvider tokenProvider;

    public UserToken login(UsernameAndPasswordAuthenticationRequest request) {
        Authentication auth = new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword());

        SecurityContextHolder.getContext().setAuthentication(auth);
        User user = userRepository.findByPhoneNumber(request.getUsername())
                .orElseThrow(() -> new NotFoundIdException(String.format(ErrorMessage.USER_WAS_NOT_FOUND_BY_USERNAME, request.getUsername())));

        if(!user.getPassword().equals(request.getPassword())) {
            throw new BadCredentialsException("Password is incorrect");
        }

        return new UserToken(tokenProvider.generateAccessToken(auth));
    }
}
