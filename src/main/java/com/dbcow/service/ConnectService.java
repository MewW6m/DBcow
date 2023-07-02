package com.dbcow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dbcow.config.CustomErrorException;
import com.dbcow.model.Connect;
import com.dbcow.repository.ConnectRepository;
import com.dbcow.util.RepositoryUtil;
import com.dbcow.util.Util;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

/**
 * 接続情報関連のサービスクラス
 */
@Service
@Slf4j
public class ConnectService {

    @Autowired RepositoryUtil repositoryUtil;
    @Autowired ConnectRepository connectRepository;
    @Autowired Util util;

    @Transactional(readOnly = false)
    public Connect getConnect(@NonNull String conname, @NonNull String username) throws CustomErrorException {
        return connectRepository.findByConnameAndUsername(conname, username)
                .orElseThrow(
                        () -> new CustomErrorException(500,
                                util.getMessage("M1000011", new String[] { conname })));
    }

    @Transactional(readOnly = false)
    public void registConnect(@NonNull Connect connect, @NonNull String username) throws CustomErrorException {
        try {
            if (connectRepository.findByConnameAndUsername(connect.getConname(), username).isPresent())
                throw new CustomErrorException(500,
                        util.getMessage("M1000012", new String[] { connect.getConname() }));
            connect.setUsername(username);
            repositoryUtil.saveConnect(connect);
        } catch (CustomErrorException ex) {
            throw ex;
        } catch (Exception ex) {
            log.error(util.getMessage("M1000015"), ex);
            throw new CustomErrorException(500, util.getMessage("M1000015"));
        }
    }

    @Transactional(readOnly = false)
    public void updateConnect(@NonNull Connect connect, @NonNull String username) throws CustomErrorException {
        try {
            Connect existedConnect = this.getConnect(connect.getConname(), username);
            util.copyEntity(connect, existedConnect);
            existedConnect.setUsername(username);
            repositoryUtil.saveConnect(existedConnect);
        } catch (CustomErrorException ex) {
            throw ex;
        } catch (Exception ex) {
            log.error(util.getMessage("M1000013", new String[] { connect.getConname() }), ex);
            throw new CustomErrorException(500, util.getMessage("M1000013", new String[] { connect.getConname() }));
        }
    }

    @Transactional(readOnly = false)
    public void deleteConnect(@NonNull String conname, @NonNull String username) throws CustomErrorException {
        try {
            Connect existedConnect = this.getConnect(conname, username);
            existedConnect.setDeleteFlag(true);
            repositoryUtil.saveConnect(existedConnect);
        } catch (CustomErrorException ex) {
            throw ex;
        } catch (Exception ex) {
            log.error(util.getMessage("M1000016", new String[] { conname }), ex);
            throw new CustomErrorException(500, util.getMessage("M1000016", new String[] { conname }));
        }
    }
}