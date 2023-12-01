package com.example.test.controller;

import com.example.test.dto.ProductDto;
import com.example.test.entity.Product;
import com.example.test.exeption.AllReadyExist;
import com.example.test.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<Product> create(@RequestBody ProductDto product) throws AllReadyExist {
        return ResponseEntity.ok(productService.addProducts(product));
    }

    @GetMapping("/get")
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(productService.all());
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id){
        Product product =  productService.getProductById(id);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody ProductDto product, @PathVariable Long id) {
        Product updateProduct = productService.updateProduct(product, id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(updateProduct);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        productService.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
    }
}
