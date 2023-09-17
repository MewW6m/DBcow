package com.dbcow.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.tuple.Triple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dbcow.model.CustomUserDetails;
import com.dbcow.util.RepositoryUtil;

@Repository
public class UserRepositoryImpl {

    @Autowired RepositoryUtil repositoryUtil;

    /**
     * 
     * @param searchParamList
     * @param sortItem
     * @param sortDirc
     * @param pageLimit
     * @param pageOffset
     * @return
     */
    public List<CustomUserDetails> selectUserList(List<Triple<String, String, String>> searchParamList, 
            String sortItem, String sortDirc, Integer pageLimit, Integer pageOffset) {
        StringBuilder sb = new StringBuilder();
        Map<String, Object> paramMap = new HashMap<String, Object>();

        this.setSelectFromState(sb);
        repositoryUtil.setWhereState(sb, paramMap, searchParamList, false);
        repositoryUtil.setOrderbyState(sb, paramMap, sortItem, sortDirc);
        repositoryUtil.setLimitOffsetState(sb, paramMap, pageLimit, pageOffset);
        
        return repositoryUtil.selectList(sb, paramMap, CustomUserDetails.class);
    }
    
    private void setSelectFromState(StringBuilder sb) {
        sb.append("SELECT * FROM user ");
    }

}
