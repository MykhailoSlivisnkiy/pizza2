package andypizza.pizza.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    @Column(name = "comment")
    private String comment;



    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @Column(name = "housenumber")
    private String housenumber;

    @Column(name = "product", nullable = false)
    private String product;

    @Column(name = "price", nullable = false)
    private String price;

    @Column(name = "status")
    private String status;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @PrePersist
    void prefillStatus() {
        this.status = "todo";
        this.createdDate = LocalDateTime.now();
    }
}
