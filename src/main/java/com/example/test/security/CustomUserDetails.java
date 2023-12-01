//package com.example.test.security;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;
//
//@AllArgsConstructor
//@Data
//public class CustomUserDetails implements UserDetails {
//
//    private final Long id;
//    private final String password;
//    private final String email;
//    private final boolean active;
//    private final Collection<? extends GrantedAuthority> authorities;
//
////    public static UserDetails create(User user) {
////        String userRole= user.getRoles().iterator().toString();
////        List<SimpleGrantedAuthority> authorities1 = Collections.singletonList(new SimpleGrantedAuthority(userRole));
////        return new UserDetails(user.getId(), user.getPassword(),user.getEmail(),authorities1);
////    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return authorities;
//    }
//
//    @Override
//    public String getPassword() {
//        return password;
//    }
//
//    @Override
//    public String getUsername() {
//        return email;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return active;
//    }
//}
