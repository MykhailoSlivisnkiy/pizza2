package andypizza.pizza.controller;

import andypizza.pizza.model.Product;
import andypizza.pizza.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
    private ProductService productService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Product> findAllAccounts(){
        return productService.findAll();
    }

    @GetMapping("/product/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product findAllAccountsByUser(@PathVariable Long id){
        return productService.findById(id);
    }
}
