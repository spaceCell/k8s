package com.iprody.controller;

import com.iprody.persist.Product;
import com.iprody.persist.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product/v1")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public List<Product> findAll() {
        return productRepository
                .findAll();
    }

    @GetMapping("/{id}")
    public Product findById(@PathVariable Long id) {
        return productRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("No product with id " + id + " in database"));
    }

    @PutMapping
    public Product create(@RequestBody Product product) {
        if (product.getId() != null) {
            throw new IllegalStateException("Id have to be null");
        }
        return productRepository.save(product);
    }

    @PostMapping
    public Product update(Product product) {
        if (product.getId() != null) {
            throw new IllegalArgumentException("Id can't be null");
        }
        return productRepository.save(product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productRepository.deleteById(id);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto handleNotFoundException(NotFoundException ex) {
        return new ErrorDto(ex.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ErrorDto(ex.getMessage());
    }
}
