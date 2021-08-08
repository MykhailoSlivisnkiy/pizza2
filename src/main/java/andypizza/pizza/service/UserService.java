package andypizza.pizza.service;

import andypizza.pizza.constant.ErrorMessage;
import andypizza.pizza.exeption.NotFoundIdException;
import andypizza.pizza.model.User;
import andypizza.pizza.repository.UserRepository;
import andypizza.pizza.security.entity.AuthorizationUser;
import andypizza.pizza.security.entity.UserToken;
import andypizza.pizza.security.jwt.JwtProvider;
import lombok.AllArgsConstructor;
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
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    public UserToken login(AuthorizationUser authUser) {
        User user = userRepository.findByPhoneNumber(authUser.getUsername()).orElseThrow();
        //Collection<? extends GrantedAuthority> authorities
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(user.getRoles().getRole()));
        Authentication auth = new UsernamePasswordAuthenticationToken(authUser.getUsername(), authUser.getPassword(), authorities);
        SecurityContextHolder.getContext().setAuthentication(auth);
        Boolean isAdmin = user.getRoles().getRole().equals("ROLE_ADMIN");

        return new UserToken(jwtProvider.generateAccessToken(auth), user.getId(), isAdmin);

    }
}
