//package com.example.test.security;
//
//import com.example.test.entity.User;
//import com.example.test.exeption.LockedException;
//import com.example.test.exeption.UserNotFound;
//import com.example.test.repository.UserRepository;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class  UserDetailsServiceImpl implements UserDetailsService {
//    private UserRepository userRepository;
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        User user = userRepository.findByEmail(email);
//
//        if (user == null) {
//            throw new UserNotFound();
//        }
//        if (user.getActivationCode() != null ) {
//            throw new LockedException();
//        }
//        return (UserDetails) user;
//    }
//}
