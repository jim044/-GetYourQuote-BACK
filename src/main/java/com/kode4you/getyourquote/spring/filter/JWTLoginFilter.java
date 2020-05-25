package com.kode4you.getyourquote.spring.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kode4you.getyourquote.spring.model.User;
import com.kode4you.getyourquote.spring.repository.UserRepository;
import com.kode4you.getyourquote.spring.service.UserService;
import com.kode4you.getyourquote.spring.utils.TokenAuthenticationService;
import org.json.JSONObject;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {
    private ObjectMapper objectMapper = new ObjectMapper();

    private UserRepository userRepository;

    private UserService userService;

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public JWTLoginFilter(String url, AuthenticationManager authManager, UserRepository userRepository , UserService userService) {
        super(new AntPathRequestMatcher(url));
        this.setUserRepository(userRepository);
        this.setUserService(userService);
        setAuthenticationManager(authManager);
    }

//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//        Authentication authentification = null;
//        User userRequest = this.getUserNameAndPAssword(request);
//
//        String login = userRequest.getLogin();
//        String emailRequest = userRequest.getEmail();
//        String password = userRequest.getPassword();
//
//        JSONObject jsonObject = new JSONObject("{ \"login\" : \"" + login + "\",\"email\" : \"" + emailRequest + "\",\"password\" : \"" + password + "\"}");
//
//        User userInDatabse = new User();
//        Boolean boolAuthentification;
//        try {
//            boolAuthentification = userService.authentification(jsonObject);
//            if(boolAuthentification) {
//                userInDatabse = userRepository.getUserByLogin(jsonObject);
//                if(userInDatabse == null) {
//                    userInDatabse = userRepository.getUserByEmail(jsonObject);
//                }
//            }
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        if(userInDatabse != null){
//            authentification = getAuthenticationManager()
//                    .authenticate(new UsernamePasswordAuthenticationToken(userInDatabse.getLogin(), userInDatabse.getPassword(), Arrays.asList(new SimpleGrantedAuthority(userInDatabse.getStatus()))));
//        }
//        else {
//            authentification = getAuthenticationManager()
//                    .authenticate(new UsernamePasswordAuthenticationToken(null, null, Collections.emptyList()));
//        }
//        return authentification;
//    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        return null;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        TokenAuthenticationService.addAuthentication(response, authResult.getName());
        String authorizationString = response.getHeader("Authorization");
        System.out.println("Authorization String=" + authorizationString);
    }

    private User getUserNameAndPAssword(HttpServletRequest request) throws IOException {
        String body = request.getReader().lines().collect(Collectors.joining());
        User usernameAndPassword = objectMapper.readValue(body, User.class);
        return usernameAndPassword;
    }
}
