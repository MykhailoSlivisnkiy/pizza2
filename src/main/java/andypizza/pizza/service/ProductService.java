package andypizza.pizza.service;

import andypizza.pizza.constant.ErrorMessage;
import andypizza.pizza.exeption.NotFoundIdException;
import andypizza.pizza.model.Product;
import andypizza.pizza.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {
    private ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(
                () -> new NotFoundIdException(String.format(ErrorMessage.PRODUCT_WAS_NOT_FOUND_BY_ID, id)));
    }
}
