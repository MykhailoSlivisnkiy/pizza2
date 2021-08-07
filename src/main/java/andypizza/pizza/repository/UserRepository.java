package andypizza.pizza.repository;

import andypizza.pizza.model.Product;
import andypizza.pizza.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {
    Optional<User> findByPhoneNumber(String phoneNumber);
}
