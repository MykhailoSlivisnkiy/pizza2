package andypizza.pizza.security.service;

import andypizza.pizza.constant.ErrorMessage;
import andypizza.pizza.model.User;
import andypizza.pizza.repository.UserRepository;
import andypizza.pizza.security.entity.ApplicationUser;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ApplicationUserService implements UserDetailsService {
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByPhoneNumber(username).orElseThrow(
                () -> new UsernameNotFoundException(String.format(ErrorMessage.USER_WAS_NOT_FOUND_BY_USERNAME, username)));

        return ApplicationUser.create(user);
    }
}
