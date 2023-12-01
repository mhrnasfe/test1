package com.example.test.service;

import com.example.test.entity.Cart;
import com.example.test.exeption.ProductNotFoundException;

import javax.transaction.Transactional;
import java.util.List;

public interface CartService {
    Cart AddProduct(Cart cart, Long ProductId, Long customerId);

    List<Cart> ViewAllCart();

    String deleteProductfromCart(Long id)throws ProductNotFoundException;

    @Transactional
    void deleteAllCart();
}
