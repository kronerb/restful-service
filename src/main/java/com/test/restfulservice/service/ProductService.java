package com.test.restfulservice.service;

import com.test.restfulservice.entity.Product;
import com.test.restfulservice.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    // Constructor
    public ProductService(
            ProductRepository productRepository
    ){
        this.productRepository = productRepository;
    }

    // Return value is of type ResponseEntity which is the common answer of a http request
    // use of @RequestBody to have the request body read and deserialized
    public ResponseEntity<Product> createNewProduct(@RequestBody Product product){
        Product newProduct = productRepository.save(product);
        return ResponseEntity.ok(newProduct);
    }

    public ResponseEntity<List<Product>> getAllProducts(){
        return ResponseEntity.ok(productRepository.findAll());
    }

    // there is no need of @RequestBody because the id is in the url
    public ResponseEntity<Optional<Product>> getProductById(Long id){
        // use of Optional because product may or may not be present
        Optional<Product> product = productRepository.findById(id);

        if(product.isPresent()){
            return ResponseEntity.ok(product);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Product> updateProduct(Long id, Product updatedProduct){
        if (id == null){
            throw new IllegalArgumentException("ID cannot be null");
        }
        Product existingProduct
                = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
        existingProduct.setName(updatedProduct.getName());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setQuantity(updatedProduct.getQuantity());

        Product savedEntity = productRepository.save(existingProduct);
        return ResponseEntity.ok(savedEntity);
    }

    public ResponseEntity<String> deleteProduct(Long id){
        productRepository.deleteById(id);
        return ResponseEntity.ok("Product deleted successfully");
    }
}
