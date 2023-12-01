package com.example.test.service.imp;


import antlr.collections.List;
import com.example.test.entity.Cart;
import com.example.test.entity.Customer;
import com.example.test.entity.Product;
import com.example.test.entity.User;
import com.example.test.exeption.CustomerNotFoundException;
import com.example.test.exeption.ProductNotFoundException;
import com.example.test.repository.*;
import com.example.test.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Optional;


@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {

    private final ProductRepository productRepository;

    private final UserRepository userRepository;

    private CurrentUserSessionRepository currentUserSessionRepository;


    private CartRepository cartRepository;

    private CustomerRepository customerRepository;

    private AddressRepository addressRepository;


    //Method to add the Product and customer in cart

    @Override
    public Cart AddProduct(Cart cart, Long ProductId, Long customerId) {
        // TODO Auto-generated method stub\

        Optional<Product> opt = productRepository.findById(ProductId);
        Optional<Customer> customer = customerRepository.findById(customerId);
//		System.out.println(customer.get());


//		Optional< Customer> databaseCustomer = custDao.findById(customerId);

        if (customer.isPresent()) {
            throw new CustomerNotFoundException("customer not found");
        }
//		Customer getCustomer = databaseCustomer.get();
        Optional<User> user = userRepository.findByMobile(customer.get().getPhoneNumber());
        String logedinOrNot = String.valueOf(currentUserSessionRepository.findById(user.get().getId()));
        if (logedinOrNot == null) {
            throw new CustomerNotFoundException("Customer not logged in");
        }


        if (opt.isPresent()) {

            Product product = opt.get();
            Customer customer1 = customer.get();


            cart.setProduct(product);
            cart.setCustomer(customer1);
//			for(Customer cust:customer) {
//				custDao.save(cust);
//			}
            return cartRepository.save(cart);
        } else {
            throw new ProductNotFoundException("Product not available");
        }
    }

    //Method to view the cart

    @Override
    public java.util.List<Cart> ViewAllCart() {
        // TODO Auto-generated method stub
        return cartRepository.findAll();
    }


    //Method to update the Product from cart
//	@Override
//	public Cart UpdateCartProduct(Cart cart) throws ProductNotFoundException {
//		// TODO Auto-generated method stub
//
//		Optional<Cart> opt = cartDao.findById(cart.getCartItemId());
//
//		if (opt.isPresent()) {
//			opt.get();
//			Cart crt = cartDao.save(cart);
//			return crt;
//		} else
//			throw new ProductNotFoundException("Product not found with given id");
//
//	}
//

    //Method to delete the product from cart

    @Override
    public String deleteProductfromCart(Long id) throws ProductNotFoundException {
        Optional<Cart> opt = cartRepository.findById(id);

        if (opt.isPresent()) {
            Cart cart = opt.get();
//			System.out.println(prod);
            cartRepository.delete(cart);
            return "CartProduct is deleted from Cart";

        } else
            throw new ProductNotFoundException("Product not found with given id");


    }

    @Override
    @Transactional
    public void deleteAllCart() {
        // TODO Auto-generated method stub
        cartRepository.deleteAll();
//		return "Cart is empty";
    }


}