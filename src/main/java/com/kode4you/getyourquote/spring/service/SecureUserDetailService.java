package com.kode4you.getyourquote.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SecureUserDetailService implements UserDetailsService {

//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        String string = "{ \"login\" : \"" + username + "\"}";
//        JSONObject jsonObject = new JSONObject(string);
//
//        User user = userRepository.getUserByLogin(jsonObject);
//
//        if(user == null) {
//            throw new UsernameNotFoundException("User not found");
//        }
//
//        String encrytedPazzword = this.passwordEncoder().encode(user.getPassword());
//        List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority(user.getStatus()));
//        UserDetails userDetail = new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), authorities);
//        //org.springframework.security.core.userdetails.User.withUsername(user.getLogin()).password(encrytedPazzword).authorities(authorities).build();
//        return userDetail;
//    }

    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}
