package andypizza.pizza.security.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserToken {
    private String token;
    private Long userId;
    private Boolean isAdmin;
}
