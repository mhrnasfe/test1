package com.example.test.controller;

import com.example.test.entity.Order;
import com.example.test.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/order")
@AllArgsConstructor
public class OrderController {


    private final OrderService orderservice;

//	@PostMapping("/{customerId}/{addressId}")
//	public ResponseEntity<MyOrder>addorder(@RequestBody MyOrder order,@PathVariable Integer customerId,@PathVariable Integer addressId){
//		MyOrder uporder= orderservice.addOrder(order, customerId, addressId);
//		return new ResponseEntity<MyOrder>(uporder,HttpStatus.ACCEPTED);
//	}

    @PostMapping("/addFromCart/{customerId}")
    public ResponseEntity<Order> addOrderFromCart(@PathVariable Integer customerId){
        Order order=orderservice.addOrderFromCart(Long.valueOf(customerId));
        return new ResponseEntity<Order>(order,HttpStatus.ACCEPTED);
    }

    @GetMapping("/findallOrder")
    public ResponseEntity<List<Order>>viewAllOrder(){
        List<Order>allOrder= orderservice.viewOrder();
        return new ResponseEntity<List<Order>>(allOrder,HttpStatus.ACCEPTED);
    }

    @GetMapping("/findByCustomerId/{customerId}")
    public ResponseEntity<Order>viewOrderByCustomerID(@PathVariable Long customerId){
        Order findOrder=orderservice.viewOrderByCustomerId(customerId);
        return new ResponseEntity<Order>(findOrder,HttpStatus.ACCEPTED);
    }

    @GetMapping("/findOrderByUserName/{FirstName}/{LastName}/{phoneNumber}")
    public ResponseEntity<List<Order>>viewByUserName(@PathVariable("FirstName") String FirstName,@PathVariable("LastName") String LastName,@PathVariable("phoneNumber") String phoneNumber ){
        List<Order>getOrderByName= orderservice.findOrderByUserName(FirstName, LastName,phoneNumber);
        return new ResponseEntity<List<Order>>(getOrderByName,HttpStatus.ACCEPTED);
    }

    @DeleteMapping("removeOrder/{customerId}")
    public ResponseEntity<String> removeOrderByCustomerID(@PathVariable Long customerId){
        String findOrder=orderservice.removeOrder(customerId);

        return new ResponseEntity<String>(findOrder,HttpStatus.ACCEPTED);
    }
}


