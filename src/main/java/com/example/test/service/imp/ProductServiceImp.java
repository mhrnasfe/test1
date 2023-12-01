package com.example.test.service.imp;

import com.example.test.Enum.Category;
import com.example.test.dto.ProductDto;
import com.example.test.entity.Product;
import com.example.test.exeption.AllReadyExist;
import com.example.test.exeption.ProductNotFound;
import com.example.test.repository.ProductRepository;
import com.example.test.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductServiceImp implements ProductService {

    private final ProductRepository productRepository;

    @Override
    @Transactional
    public Product addProducts(ProductDto product) throws AllReadyExist {
        Optional<Product> product1 = productRepository
                .findByName(product.getProductName());
        if (product1.isPresent()) {
            throw new AllReadyExist("this product is present");
        } else {
            Product savedProduct = new Product();
            savedProduct.setName(product.getProductName());
            savedProduct.setPrice(product.getPrice());
            return productRepository.save(savedProduct);
        }
    }

    @Override
    public List<Product> all() {
        ArrayList<Product> product = new ArrayList<>();
        return productRepository.findAll();

    }

    @Override
    public Product getProductById(Long id) {
        Optional<Product> byId = productRepository.findById(id) ;
        if (byId.isPresent()){
            return byId.get();
        }else{
            throw new ProductNotFound("productId not found");
        }

    }


    @Override
    public Product updateProduct(ProductDto product, Long id) {
        Product product1 = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFound("productNotFound"));
        product1.setName(product.getProductName());
        product1.setPrice(product.getPrice());
        return productRepository.save(product1);
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFound("productId not found"));
        productRepository.delete(product);
    }


    @Override
    public List<ProductDto> getCategoryWiseProducts(Category cat) {
        return null;
    }


}


//    @Override
//    public Products getProductsFromCatalogById(Integer id) {
//        return null;
//    }

