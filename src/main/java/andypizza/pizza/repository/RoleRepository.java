package andypizza.pizza.repository;

import andypizza.pizza.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>  {
    Optional<Role> findById(Long id);
    Optional<Role> findByRole(String role);
}
