package com.dbcow.service;

import java.util.List;

import org.apache.commons.lang3.tuple.Triple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dbcow.config.CustomErrorException;
import com.dbcow.model.Connect;
import com.dbcow.repository.ConnectRepository;
import com.dbcow.repository.ConnectRepositoryImpl;
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
    @Autowired ConnectRepositoryImpl connectRepositoryImpl;
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
            connect.setConnectstring("aaa"); // 暫定
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
            connect.setConnectstring("aaa"); // 暫定
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

    /**
     * 条件をもとに接続情報一覧を取得する
     * 
     * @param searchParamList
     * @param sortItem
     * @param sortDirc
     * @param pageLimit
     * @param pageOffset
     * @return
     */
    @Transactional(readOnly = false)
    public List<Connect> getConnectListBySearch(List<Triple<String, String, String>> searchParamList, 
            String sortItem, String sortDirc, Integer pageLimit, Integer pageOffset) {
        return connectRepositoryImpl.selectConnectList(searchParamList, sortItem, sortDirc, pageLimit, pageOffset);
    }
}