package andypizza.pizza.service;

import andypizza.pizza.dto.UserDto;
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
    private static final String USER_ROLE = "ROLE_USER";

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final JwtProvider jwtProvider;

    public void create(UserDto userDto) {
        User user = new User();
        user.setCity(userDto.getCity());
        user.setPassword(userDto.getPassword());
        user.setHouse(userDto.getHouse());
        user.setName(userDto.getName());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setStreet(userDto.getStreet());
        user.setRoles(roleService.findByRole(USER_ROLE));

        System.out.println("INSERTED USER" + user);
         userRepository.save(user);
    }

    public UserToken login(AuthorizationUser authUser) {
        User user = userRepository.findByPhoneNumber(authUser.getUsername()).orElseThrow();
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(user.getRoles().getRole()));
        Authentication auth = new UsernamePasswordAuthenticationToken(authUser.getUsername(), authUser.getPassword(), authorities);
        SecurityContextHolder.getContext().setAuthentication(auth);
        Boolean isAdmin = user.getRoles().getRole().equals("ROLE_ADMIN");

        return new UserToken(jwtProvider.generateAccessToken(auth), user.getId(), isAdmin);

    }
}
