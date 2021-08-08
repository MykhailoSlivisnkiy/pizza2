package andypizza.pizza.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto {
    private String name;
    private String phoneNumber;
    private String password;
    private String city;
    private String street;
    private String house;
    private String roleId;
}
