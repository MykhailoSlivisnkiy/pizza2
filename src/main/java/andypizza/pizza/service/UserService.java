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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;
    private JwtTokenProvider tokenProvider;


    public UserToken login(UsernameAndPasswordAuthenticationRequest request) {
        User user = userRepository.findByPhoneNumber(request.getUsername()).orElseThrow();
        //Collection<? extends GrantedAuthority> authorities
        Set<GrantedAuthority> authorities = new HashSet<>();
        System.out.println("HAHAHAHAH" + user.getRoles().getRole());
        authorities.add(new SimpleGrantedAuthority(user.getRoles().getRole()));
        Authentication auth = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword(), authorities);
        SecurityContextHolder.getContext().setAuthentication(auth);
        return new UserToken(tokenProvider.generateAccessToken(auth));
    }
}
