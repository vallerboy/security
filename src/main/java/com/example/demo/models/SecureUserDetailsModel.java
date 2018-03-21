package com.example.demo.models;

import com.example.demo.models.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class SecureUserDetailsModel implements UserDetailsService {


    final UserRepository userRepository;

    @Autowired
    public SecureUserDetailsModel(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserModel userModel = userRepository.findByLogin(s);
        if(userModel == null){
            throw new UsernameNotFoundException(s);
        }
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(userModel.getUserType());
        return new User(userModel.getLogin(), userModel.getPassword(), Collections.singletonList(grantedAuthority));
    }
}
