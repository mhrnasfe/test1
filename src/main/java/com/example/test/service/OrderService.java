package com.example.test.service;

import com.example.test.entity.Order;

import java.util.List;

public interface OrderService {
//    Order createOrder(Order order);
//
//    Order addOrderFromCart(Integer customerId);
//
//    List<Order> viewOrder();
//
//    Order viewOrderByCustomerId(Integer customerId);

    Order viewOrderByCustomerId(Long customerId);

    List<Order> findOrderByUserName(String firstName, String lastName, String phoneNumber);

    Order updateOrder(Long customerId, Order order);

    String removeOrder(Long customerId);


    Order addOrderFromCart(Long customerId);

    List<Order> viewOrder();

}
