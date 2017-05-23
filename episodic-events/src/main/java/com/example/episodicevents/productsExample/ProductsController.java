package com.example.episodicevents.productsExample;

import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/productsExample")
public class ProductsController {
    @GetMapping
    public List<Object> getProducts() {
        return Arrays.asList(
                new Product("abc123", "Hamilton CD", 1299),
                new DigitalProduct("abc123", "Hamilton", 1299, "http://example.com/playground/hamilton.mp4"),
                new StreamingProduct("abc123", "Hamilton", 1299, "rtmp://example.com/playground/mp4:hamilton.mp4")
        );
    }

    @PostMapping
    public Object createProduct(@RequestBody Product product) {
        System.out.println(product.getClass().getName());
        return product;
    }
}