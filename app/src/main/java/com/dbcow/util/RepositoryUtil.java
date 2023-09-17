package com.dbcow.util;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Triple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.dbcow.config.CustomErrorException;
import com.dbcow.model.Connect;
import com.dbcow.model.CustomUserDetails;
import com.dbcow.model.Setting;
import com.dbcow.repository.ConnectRepository;
import com.dbcow.repository.SettingRepository;
import com.dbcow.repository.UserRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class RepositoryUtil {

    @Autowired
    @Lazy
    EntityManager entityManager;
    @Autowired
    @Lazy
    UserRepository userRepository;
    @Autowired
    @Lazy
    SettingRepository settingRepository;
    @Autowired 
    @Lazy
    ConnectRepository connectRepository;
    @Autowired
    @Lazy
    Util util;

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

    @Transactional(readOnly = false)
    public void saveSetting(@NonNull Setting setting) throws CustomErrorException {
        try {
            settingRepository.saveAndFlush(setting);
        } catch (CustomErrorException ex) {
            throw ex;
        } catch (Exception ex) {
            log.error(util.getMessage("M1000010"), ex);
            throw new CustomErrorException(500, util.getMessage("M1000010"));
        }
    }

    @Transactional(readOnly = false)
    public void saveAllSetting(@NonNull List<Setting> settingList) throws CustomErrorException {
        try {
            settingRepository.saveAllAndFlush(settingList);
        } catch (CustomErrorException ex) {
            throw ex;
        } catch (Exception ex) {
            log.error(util.getMessage("M1000010"), ex);
            throw new CustomErrorException(500, util.getMessage("M1000010"));
        }
    }

    @Transactional(readOnly = false)
    public void saveConnect(@NonNull Connect connect) throws CustomErrorException {
        try {
            connectRepository.saveAndFlush(connect);
        } catch (CustomErrorException ex) {
            throw ex;
        } catch (Exception ex) {
            log.error(util.getMessage("M1000013"), ex);
            throw new CustomErrorException(500, util.getMessage("M1000013"));
        }
    }

    /**
     * 
     * @param <T>
     * @param sb
     * @param paramMap
     * @param tClass
     * @return
     */
    public <T> List selectList(StringBuilder sb, Map<String, Object> paramMap, Class<T> tClass) {
        Query query = entityManager.createNativeQuery(sb.toString(), tClass);

        for (Entry<String, Object> param : paramMap.entrySet())
            query.setParameter(param.getKey(), param.getValue());

        return query.getResultList();
    }

    /**
     * 
     * @param sb
     * @param paramMap
     * @param searchParamList
     * @param searchItemList
     */
    public void setWhereState(StringBuilder sb, Map<String, Object> paramMap,
            List<Triple<String, String, String>> searchParamList, Boolean deleteFlag) {
        if (deleteFlag)
            sb.append("WHERE deleteFlag = false ");
        else
            sb.append("WHERE deleteFlag in (true, false) ");

        for (int i = 0; i < searchParamList.size(); i++ ) {
            if (!StringUtils.isNotBlank(searchParamList.get(i).getLeft())
                    || !StringUtils.isNotBlank(searchParamList.get(i).getMiddle()))
                continue;

            sb.append("AND ");

            switch (searchParamList.get(i).getMiddle()) {
                case "CO":
                    this.setCoState(sb, paramMap, searchParamList.get(i).getLeft(), searchParamList.get(i).getRight(), i);
                    break;
                case "EQ":
                    this.setEqState(sb, paramMap, searchParamList.get(i).getLeft(), searchParamList.get(i).getRight(), i);
                    break;
                case "SW":
                    this.setSwState(sb, paramMap, searchParamList.get(i).getLeft(), searchParamList.get(i).getRight(), i);
                    break;
                case "EW":
                    this.setEwState(sb, paramMap, searchParamList.get(i).getLeft(), searchParamList.get(i).getRight(), i);
                    break;
                case "MT":
                    this.setMtState(sb, paramMap, searchParamList.get(i).getLeft(), searchParamList.get(i).getRight(), i);
                    break;
                case "ML":
                    this.setMlState(sb, paramMap, searchParamList.get(i).getLeft(), searchParamList.get(i).getRight(), i);
                    break;
                case "IN":
                    this.setInState(sb, paramMap, searchParamList.get(i).getLeft(), searchParamList.get(i).getRight(), i);
                    break;
            }
        }
    }

    /**
     * 
     * @param sb
     * @param paramMap
     * @param sortItem
     * @param sortDirc
     */
    @Transactional(readOnly = true)
    public void setOrderbyState(StringBuilder sb, Map<String, Object> paramMap, String sortItem, String sortDirc) {
        if (StringUtils.isNotBlank(sortItem) && StringUtils.isNotBlank(sortDirc)) {
            sb.append("ORDER BY ");
            sb.append(sortItem + " ");
            sb.append(sortDirc + " ");
        }
    }

    /**
     * 
     * @param sb
     * @param paramMap
     * @param pageLimit
     * @param pageOffset
     */
    @Transactional(readOnly = true)
    public void setLimitOffsetState(StringBuilder sb, Map<String, Object> paramMap, Integer pageLimit,
            Integer pageOffset) {
        if (pageLimit != null && pageOffset != null) {
            sb.append("LIMIT ");
            sb.append(pageLimit + " ");
            sb.append("OFFSET ");
            sb.append(pageOffset + " ");
        }
    }

    /**
     * 
     * @param sb
     * @param paramMap
     * @param left
     * @param right
     */
    private void setCoState(StringBuilder sb, Map<String, Object> paramMap, String left, String right, Integer i) {
        sb.append(left);
        sb.append(" like :");
        sb.append(left + i);
        sb.append(" ");
        paramMap.put(left + i, "%" + right + "%");
    }

    /**
     * 
     * @param sb
     * @param paramMap
     * @param left
     * @param right
     */
    private void setEqState(StringBuilder sb, Map<String, Object> paramMap, String left, String right, Integer i) {
        sb.append(left);
        sb.append(" = :");
        sb.append(left + i);
        sb.append(" ");
        paramMap.put(left + i, right);
    }

    /**
     * 
     * @param sb
     * @param paramMap
     * @param left
     * @param right
     */
    private void setSwState(StringBuilder sb, Map<String, Object> paramMap, String left, String right, Integer i) {
        sb.append(left);
        sb.append(" like :");
        sb.append(left + i);
        sb.append(" ");
        paramMap.put(left + i, right + "%");
    }

    /**
     * 
     * @param sb
     * @param paramMap
     * @param left
     * @param right
     */
    private void setEwState(StringBuilder sb, Map<String, Object> paramMap, String left, String right, Integer i) {
        sb.append(left);
        sb.append(" like :");
        sb.append(left + i);
        sb.append(" ");
        paramMap.put(left + i, "%" + right);
    }

    /**
     * 
     * @param sb
     * @param paramMap
     * @param left
     * @param right
     */
    private void setMtState(StringBuilder sb, Map<String, Object> paramMap, String left, String right, Integer i) {
        sb.append(left);
        sb.append(" >= :");
        sb.append(left + i);
        sb.append(" ");
        paramMap.put(left + i, right);
    }

    /**
     * 
     * @param sb
     * @param paramMap
     * @param left
     * @param right
     */
    private void setMlState(StringBuilder sb, Map<String, Object> paramMap, String left, String right, Integer i) {
        sb.append(left);
        sb.append(" <= :");
        sb.append(left + i);
        sb.append(" ");
        paramMap.put(left + i, right);
    }

    /**
     * 
     * @param sb
     * @param paramMap
     * @param left
     * @param right
     */
    private void setInState(StringBuilder sb, Map<String, Object> paramMap, String left, String right, Integer i) {
        Boolean firstflg = true;
        String[] rights = right.replaceAll(" ","").split(",");
        sb.append("(");
        for(int x = 0; x < rights.length; x++) {
            if (firstflg)
                firstflg = false;
            else
                sb.append("OR ");
            sb.append(left);
            sb.append(" = :");
            sb.append(left + i + x);
            sb.append(" ");
            paramMap.put(left + i + x, rights[x]);
        }
        sb.append(") ");
    }
}
