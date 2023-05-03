package com.dbcow.util;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import jakarta.persistence.Tuple;
import jakarta.persistence.TupleElement;

@Component
public class Util {

    @Autowired private MessageSource messageSource;
    
	public Object mapget(Map<String, Object> map, String key) {
		return Optional.ofNullable(map.get(key)).orElse(null);
	}
	
	public List<Map<String, Object>> convertTuplesToMap(List<Tuple> tuples) {
	    List<Map<String, Object>> result = new ArrayList<>();
	    for (Tuple single : tuples) {
	        Map<String, Object> tempMap = new LinkedHashMap<>();
	        for (TupleElement<?> key : single.getElements()) {
	            tempMap.put(key.getAlias(), single.get(key));
	        }
	        result.add(tempMap);
	    }
	    return result;
	}
	
    /**
     * getMessage<br>
     * message.propertiesからメッセージを取得する
     * @param messageId メッセージID
     * @return メッセージ
     */
    public String getMessage(String messageId){
        return messageSource.getMessage(messageId, null , Locale.JAPAN);
    }

    /**
     * getMessage<br>
     * message.propertiesからメッセージを取得する
     * @param messageId メッセージID
     * @param args メッセージに挿入する値
     * @return メッセージ
     */
    public String getMessage(String messageId, String[] args){
        return messageSource.getMessage(messageId, args, Locale.JAPAN);
    }
}
