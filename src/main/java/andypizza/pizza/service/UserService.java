package andypizza.pizza.service;

import andypizza.pizza.constant.ErrorMessage;
import andypizza.pizza.dto.UserDto;
import andypizza.pizza.exeption.NotFoundIdException;
import andypizza.pizza.exeption.UserPasswordException;
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

import javax.management.monitor.MonitorSettingException;
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

        userRepository.save(user);
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundIdException(String.format(ErrorMessage.PRODUCT_WAS_NOT_FOUND_BY_ID, id)));
    }

    public UserToken login(AuthorizationUser authUser) {
        User user = userRepository.findByPhoneNumber(authUser.getUsername()).orElseThrow(() -> new NotFoundIdException(authUser.getUsername()));
        System.out.println("Password from db"+user.getPassword());
        System.out.println("Password UI"+authUser.getPassword());

        if(!user.getPassword().equals(authUser.getPassword())) {
            throw new UserPasswordException(ErrorMessage.USER_HAS_WRONG_PASSWORD);
        }

        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(user.getRoles().getRole()));
        Authentication auth = new UsernamePasswordAuthenticationToken(authUser.getUsername(), authUser.getPassword(), authorities);
        SecurityContextHolder.getContext().setAuthentication(auth);
        Boolean isAdmin = user.getRoles().getRole().equals("ROLE_ADMIN");
        System.out.println("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF");
        return new UserToken(jwtProvider.generateAccessToken(auth), user.getId(), isAdmin);

    }
}
