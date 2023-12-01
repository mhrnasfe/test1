package com.example.test.service.imp;

import com.example.test.entity.*;
import com.example.test.exeption.CustomerNotFoundException;
import com.example.test.repository.*;
import com.example.test.service.CartService;
import com.example.test.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@AllArgsConstructor
public class OrderServiceImp implements OrderService {

    private final OrderRepository orderRepository;

    private final CustomerRepository customerRepository;

    private final UserRepository userRepository;

    private final CurrentUserSessionRepository currentUserSessionRepository;

    private final ProductRepository productRepository;

    private final AddressRepository addressRepository;

    private final CartRepository cartRepository;

    private final CartServiceImpl cartServiceImp;

    private final CustomerServiceImp customerServiceImpl;

    private final CartService cartService;


    //parameter customerId,addressId
//	@Override
//	public MyOrder addOrder(MyOrder order, Integer customerId, Integer addressId) {
//
//		order.setLocaldtetime(LocalDateTime.now());
//		order.setOrderstatus("accepted");
//		Optional< Customer> databaseCustomer = customerDao.findById(customerId);
//
//		if(databaseCustomer.isEmpty()) {
//			throw new CustomerNotFoundException("customer not found");
//		}
//		Customer customer = databaseCustomer.get();
//		User user=  userDao.findByMobile(databaseCustomer.get().getMobileNumber());
//		String logedinOrNot = currUserSessDao.findByUserId(user.getUserId());
//		if(logedinOrNot==null) {
//			throw new CustomerNotFoundException("Customer not logged in");
//		}
//
//		List<Products> products = order.getProductlist();
//		List<Products> products2=new ArrayList<Products>();
//		for(Products p:products) {
//
//			List<Products> pr = productDao.findByProductName(p.getProductName());
//			if(pr.size()<=0) {
//
//				throw new CustomerNotFoundException("Product not found");
//			}
////			System.out.println(pr);
//			for(Products prd:pr) {
//				if(prd.getPrice().equals(p.getPrice())) {
//					products2.add(prd);
//				}
//
//			}
//
//
//		}
//
//		 order.setProductlist(products2);
//
//		List<Address> customerAddr = customer.getAddresslist();
//		int count = 0;
//		for(Address addr: customerAddr) {
//			if(addr.getAddressId()==addressId) {
//				order.setAddress(addr);
//			}else count++;
//		}
//		if(count==customerAddr.size()) throw new AddressNotFound("Address not found with the customer Id"+customerId);
//		order.setCustomer(customer);
//		return orderdao.save(order);
//	}

    @Override
    public List<Order> viewOrder() {
        List<Order> allOrder = orderRepository.findAll();
        return allOrder;
    }

    @Override
    public Order viewOrderByCustomerId(Long customerId) {
        List<Order> allOrder = orderRepository.findAll();

        for (Order order : allOrder) {
            if (Objects.equals(order.getCustomer().getId(), customerId)) {
                return order;
            }
        }
        throw new CustomerNotFoundException("Order not found");
    }

    @Override
    public List<Order> findOrderByUserName(String FirstName, String LastName, String phoneNumber) {
        List<Order> allOrder = orderRepository.findAll();
        List<Order> findAllByName = new ArrayList<Order>();
        for (Order order : allOrder) {
            if (order.getCustomer().getFirstName().equals(FirstName) &&
                    order.getCustomer().getLastName().equals(LastName) &&
                    order.getCustomer().getPhoneNumber().equals(phoneNumber)) {
                findAllByName.add(order);
            }

        }
//		System.out.println(findAllByName);
        if (findAllByName.size() == 0) {
            throw new CustomerNotFoundException("Order not found");
        } else {
            return findAllByName;
        }
    }

    @Override
    public Order updateOrder(Long customerId, Order order) {
        List<Order> allOrder = orderRepository.findAll();
        AtomicInteger count = new AtomicInteger();
        Order findOrder = new Order();
        for (Order find : allOrder) {
            if (Objects.equals(find.getCustomer().getId(), customerId)) {
                findOrder = find;
                count.getAndIncrement();
            }
        }
        if (count.get() == 0) {
            throw new CustomerNotFoundException("Order not found");
        }
        List<Product> products2 = order.getProducts();
        List<Product> products = findOrder.getProducts();
        return findOrder;
    }

    @Override
    public String removeOrder(Long customerId) {

        List<Order> allOrder = orderRepository.findAll();
        for (Order order : allOrder) {
            if (Objects.equals(order.getCustomer().getId(), customerId)) {
//			 orderdao.delete(order);

                User user = userRepository.findByPhoneNumber(order.getCustomer().getPhoneNumber());
                String logedinOrNot = String.valueOf(currentUserSessionRepository.findById(user.getId()));
                if (logedinOrNot == null) {
                    throw new CustomerNotFoundException("Customer not logged in");
                }
                order.setCustomer(null);
                order.setProducts(null);
                orderRepository.save(order);
                System.out.println(order);
                orderRepository.delete(order);

                return "order canceled";
            }
        }

        throw new CustomerNotFoundException("Order not found");
    }

    @Override
    public Order addOrderFromCart(Long customerId) {
        // TODO Auto-generated method stub

        Order order = new Order();
        Cart cart = new Cart();
        List<Cart> allCartDetails = cartServiceImp.ViewAllCart();
        List<Product> getProducts = new ArrayList<>();

        for (Cart newCart : allCartDetails) {
            if (Objects.equals(newCart.getCustomer().getId(), customerId)) {
                getProducts.add(newCart.getProduct());
                allCartDetails.remove(newCart.getCustomer());
            }
        }
        System.out.println(getProducts);
        order.setTime(LocalDateTime.now());
        order.setOrderStatus("ORDER PLACED");

        Optional<Customer> opt = customerRepository.findById(customerId);

        if (opt.isPresent()) {
            throw new CustomerNotFoundException("Customer not found with this Id" + customerId);
        }
        order.setCustomer(opt.get());
        order.setProducts(getProducts);

        //		cartService.deleteAllCart();
        return orderRepository.save(order);
    }

}
