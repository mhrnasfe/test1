package com.example.test.service;

import com.example.test.Enum.Category;
import com.example.test.dto.ProductDto;
import com.example.test.entity.Product;
import com.example.test.exeption.AllReadyExist;

import java.util.List;

public interface ProductService {
    public Product addProducts(ProductDto product) throws AllReadyExist;

    public List<Product> all();

//    public List<Product> getProductId();


    public List<ProductDto> getCategoryWiseProducts(Category cat);

    public void deleteProduct(Long id);

//    public Product updateProductIncatalog(Product product);

    Product updateProduct(ProductDto product, Long id);

    Product getProductById(Long id);


    //    public Products getProductsFromCatalogById(Integer id);
}
