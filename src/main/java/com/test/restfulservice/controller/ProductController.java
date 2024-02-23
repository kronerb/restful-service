package com.test.restfulservice.controller;

import com.test.restfulservice.entity.Product;
import com.test.restfulservice.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController // specifies that this class is a controller for RESTful API requests
@RequestMapping("/api/v1") // specifies the base URL for all requests handled by exactly this controller
public class ProductController {

    private final ProductService productService;

    // Constructor
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // create a new Prodcut
    @PostMapping("/product") // indicates that this method handles post requests and returns the response as JSON
    public ResponseEntity<Product> createNewProduct(@RequestBody Product product){
        return productService.createNewProduct(product);
    }

    @GetMapping("/products") // indicates that this method handels get requests and returns the response as JSON
    public ResponseEntity<List<Product>> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Optional<Product>> getProductById(@PathVariable Long id){
        return productService.getProductById(id);
    }

    @PutMapping("/products/{productId}") //
    public ResponseEntity<Product> updateProduct(@PathVariable(value = "productId") Long id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    @DeleteMapping(value = "/products/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long productId){
        return productService.deleteProduct(productId);
    }

}
