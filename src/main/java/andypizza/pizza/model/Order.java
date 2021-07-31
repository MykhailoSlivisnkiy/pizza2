package andypizza.pizza.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "Order")
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @Column(name = "housnumber")
    private String houseNumber;

    @Column(name = "product", nullable = false)
    private String product;

    @Column(name = "price", nullable = false)
    private String price;
}
