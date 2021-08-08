package andypizza.pizza.service;

import andypizza.pizza.constant.ErrorMessage;
import andypizza.pizza.exeption.NotFoundIdException;
import andypizza.pizza.model.Role;
import andypizza.pizza.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoleService {
    private RoleRepository roleRepository;

    public Role findById(Long id) {
        return roleRepository.findById(id).orElseThrow(
                () -> new NotFoundIdException(String.format(ErrorMessage.PRODUCT_WAS_NOT_FOUND_BY_ID, id)));
    }

    public Role findByRole(String role) {
        return roleRepository.findByRole(role).orElseThrow(
                () -> new NotFoundIdException(String.format(ErrorMessage.PRODUCT_WAS_NOT_FOUND_BY_ID, role)));
    }

}
