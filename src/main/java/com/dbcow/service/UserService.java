package com.dbcow.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
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

/**
 * ユーザー情報関連のサービスクラス
 */
@Service
@Slf4j
public class UserService implements UserDetailsService {

    @Autowired
    private RepositoryUtil repositoryUtil;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private Util util;

    /**
     * ユーザー情報を返す(認証時に使用)
     * 
     * @param username ユーザー名
     * @return ユーザー情報
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException(
                                util.getMessage("M1000004", new String[] { username })));
    }

    /**
     * ユーザー情報を返す
     * 
     * @param username           ユーザー名
     * @param deleteFlagRequired 削除フラグ考慮
     * @return ユーザー情報
     * @throws CustomErrorException
     */
    @Transactional(readOnly = false)
    public CustomUserDetails getUser(@NonNull String username, @NonNull Boolean deleteFlagRequired)
            throws CustomErrorException {
        if (deleteFlagRequired)
            return userRepository.findByUsername(username)
                    .orElseThrow(
                            () -> new CustomErrorException(500,
                                    util.getMessage("M1000004", new String[] { username })));
        else
            return userRepository.findByUsernameNoDeleteFlag(username)
                    .orElseThrow(
                            () -> new CustomErrorException(500,
                                    util.getMessage("M1000004", new String[] { username })));
    }

    /**
     * ユーザー情報登録を実行する
     * 
     * @param user ユーザー情報
     * @throws CustomErrorException
     */
    @Transactional(readOnly = false)
    public void registUser(@NonNull CustomUserDetails user) throws CustomErrorException {
        try {
            if (StringUtils.isBlank(user.getUsername()) ||
                    StringUtils.isBlank(user.getPassword()))
                throw new CustomErrorException(500, util.getMessage("M1000003"));

            if (userRepository.findByUsername(user.getUsername()).isPresent())
                throw new CustomErrorException(500,
                        util.getMessage("M1000005", new String[] { user.getUsername() }));

            user.setRoles("ROLE_USER");

            repositoryUtil.saveUser(user);
        } catch (CustomErrorException ex) {
            throw ex;
        } catch (Exception ex) {
            log.error(util.getMessage("M1000001"), ex);
            throw new CustomErrorException(500, util.getMessage("M1000001"));
        }
    }

    /**
     * ユーザー情報更新を実行する
     * 
     * @param user ユーザー情報
     * @throws CustomErrorException
     */
    @Transactional(readOnly = false)
    public void updateUser(@NonNull CustomUserDetails user) throws CustomErrorException {
        try {
            CustomUserDetails existedUser = this.getUser(user.getUsername(), false);
            util.copyEntity(user, existedUser);
            repositoryUtil.saveUser(existedUser);
        } catch (CustomErrorException ex) {
            throw ex;
        } catch (Exception ex) {
            log.error(util.getMessage("M1000007"), ex);
            throw new CustomErrorException(500, util.getMessage("M1000007"));
        }
    }

    /**
     * ユーザー情報削除を実行する
     * 
     * @param user ユーザー情報
     * @throws CustomErrorException
     */
    @Transactional(readOnly = false)
    public void deleteUser(@NonNull String username) throws CustomErrorException {
        try {
            if (StringUtils.isBlank(username))
                throw new CustomErrorException(500, util.getMessage("M1000003"));

            CustomUserDetails user = this.getUser(username, false);

            if (StringUtils.equals(user.getRoles(), "ROLE_ADMIN"))
                throw new CustomErrorException(500, util.getMessage("M1000008"));

            user.setDeleteFlag(true);
            repositoryUtil.saveUser(user);
        } catch (CustomErrorException ex) {
            throw ex;
        } catch (Exception ex) {
            log.error(util.getMessage("M1000006", new String[] { username }), ex);
            throw new CustomErrorException(500, util.getMessage("M1000006", new String[] { username }));
        }
    }

    @Transactional(readOnly = false)
    public List<CustomUserDetails> getUserList() throws CustomErrorException {
        return userRepository.findAllNoDeleteFlag();
    }
}