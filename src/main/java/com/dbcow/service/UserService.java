package com.dbcow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dbcow.config.CustomErrorException;
import com.dbcow.model.CustomUserDetails;
import com.dbcow.repository.UserRepository;
import com.dbcow.util.RepositoryUtil;
import com.dbcow.util.Util;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService implements UserDetailsService {

    @Autowired private RepositoryUtil repositoryUtil;
    @Autowired private UserRepository userRepository;
    @Autowired private Util util;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { // ←１１
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found:[" + username + "]"));
    }

    @Transactional(readOnly=false)
    public void registUser(@NonNull CustomUserDetails customUserDetails) throws CustomErrorException {
        try {
            customUserDetails.setRoles("01");
            customUserDetails.setEnableFlag(true);
            repositoryUtil.saveUser(customUserDetails);
        } catch (CustomErrorException ex) {
            throw ex;
        } catch (Exception ex) {
            log.error(util.getMessage("M1000001"), ex);
            throw new CustomErrorException(500, util.getMessage("M1000001"));
        }
    }
}