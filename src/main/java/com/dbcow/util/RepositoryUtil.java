package com.dbcow.util;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Component
public class RepositoryUtil {
	
	@Autowired EntityManager entityManager;
	
	public Map<String, Object> bindInsertCommonParam(Map<String, Object> paramMap) {
		paramMap.put("sysinsTermId", "AtomsTool");
		paramMap.put("sysinsUsrId", "100001");
		paramMap.put("sysinsYmdhms", new Date());
		paramMap.put("sysupdTermId", "AtomsTool");
		paramMap.put("sysupdUsrId", "100001");
		paramMap.put("sysupdYmdhms", new Date());
		return paramMap;
	}

	public Map<String, Object> bindUpdateCommonParam(Map<String, Object> paramMap) {
		paramMap.put("sysupdTermId", "AtomsTool");
		paramMap.put("sysupdUsrId", "100001");
		paramMap.put("sysupdYmdhms", new Date());
		return paramMap;
	}
	
	@Transactional(readOnly=false)
	public void executeInsert(String queryString, Map<String, Object> paramMap) {
		Query query = entityManager.createNativeQuery(queryString);
		this.setPrameters(paramMap, query);
		query.executeUpdate();
	}
	
	public void setPrameters(Map<String, Object> paramMap, Query query) {
		for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
			query.setParameter(entry.getKey(),entry.getValue());
		}
	}
}
