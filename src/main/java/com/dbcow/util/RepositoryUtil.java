package com.dbcow.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.dbcow.config.CustomErrorException;
import com.dbcow.model.CustomUserDetails;
import com.dbcow.repository.UserRepository;

import jakarta.persistence.EntityManager;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class RepositoryUtil {

	@Autowired EntityManager entityManager;
	@Autowired UserRepository userRepository;
	@Autowired Util util;

	@Transactional(readOnly = false)
    public void saveUser(@NonNull CustomUserDetails user) throws CustomErrorException {
        try {
			userRepository.saveAndFlush(user);
        } catch (CustomErrorException ex) {
            throw ex;
        } catch (Exception ex) {
            log.error(util.getMessage("M1000002"), ex);
            throw new CustomErrorException(500, util.getMessage("M1000002"));
        }
    }

}
