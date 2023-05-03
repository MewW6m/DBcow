package com.dbcow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dbcow.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {
    
    @Autowired private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { //←１１
        return userRepository.findByName(username)
        .orElseThrow(() -> new UsernameNotFoundException("User not found by email:[" + username + "]"));
    }
}
