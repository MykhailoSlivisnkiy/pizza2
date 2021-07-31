package andypizza.pizza.model;

import lombok.*;

import javax.persistence.*;

@Entity(name = "Product")
@Table(name = "menu")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "typeOfProduct", nullable = false)
    private String type;

    @Column(name = "title", unique = true, nullable = false)
    private String title;

    @Column(name = "img", unique = true, nullable = false)
    private String img;

    @Column(name = "price", unique = true, nullable = false)
    private Integer price;

    @Column(name = "ingredient", nullable = false)
    private String ingredient;

    @Column(name = "weight", nullable = false)
    private Integer weight;
}
