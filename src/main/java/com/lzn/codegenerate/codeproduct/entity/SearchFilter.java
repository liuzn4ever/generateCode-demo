package com.lzn.codegenerate.codeproduct.entity;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.elogside.modules.utils.ConvertUtils;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.Map.Entry;

public class SearchFilter {

	public enum Operator {
		EQ, LIKE, GT, LT, GTE, LTE, NEQ ,IN, ISNULL, NOTNULL
	}
	
	
	/** 属性数据类型. */
	public enum PropertyType {
		S(String.class), I(Integer.class), L(Long.class), N(Double.class), D(Date.class), B(Boolean.class) ,E(Enum.class),M(BigDecimal.class),C(Collection.class);
		
		
		private Class<?> clazz;

		private PropertyType(Class<?> clazz) {
			this.clazz = clazz;
		}

		public Class<?> getValue() {
			return clazz;
		}
	}
	BaseMapper
	private static Class<?> propertyClass = null;
	
	/**需要转换的枚举类型Class集合*/
	private static List<Class<?>> enumObjects;
	private static Map<String,Class<?>> enumMaps;

	public String fieldName;
	public Object value;
	public Operator operator;
	/*private static Object matchValue = null;
	
	public Object getMatchValue() {
		return matchValue;
	}*/


	static{
		Properties properties = new Properties();
		List<Class<?>> clazzList = new ArrayList<Class<?>>();
		Map<String,Class<?>> clazzMap = new HashMap<>();
		try {
			properties.load(SearchFilter.class.getClassLoader().getResourceAsStream("enumConvert.properties"));

			for (Object key : properties.keySet()) {
				String skey = (String)key;
				String str = (String)properties.get(key);
				try {
					Class<?> aClass = Class.forName(str);
					clazzList.add(aClass);
					clazzMap.put(skey,aClass);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}


//			Enumeration<Object> en = properties.elements();
//			while(en.hasMoreElements()){
//				String str = (String)en.nextElement();
//				try {
//					clazzList.add(Class.forName(str));
//				} catch (ClassNotFoundException e) {
//					e.printStackTrace();
//				}
//			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		enumObjects = clazzList;
		enumMaps = clazzMap;
	}
	

	public SearchFilter(String fieldName, Operator operator, Object value) {
		this.fieldName = fieldName;
		this.value = value;
		this.operator = operator;
	}

	/**
	 * searchParams中key的格式为LIKES_name
	 */
	public static Map<String, SearchFilter> parse(Map<String, Object> searchParams) {
		Object matchValue = null;
		Map<String, SearchFilter> filters = Maps.newHashMap();

		for (Entry<String, Object> entry : searchParams.entrySet()) {
			// 过滤掉空值
			String key = entry.getKey();
			
			Object value = entry.getValue();
			if (value instanceof String && StringUtils.isBlank((String) value)) {
				continue;
			}else if(value instanceof Collection && CollectionUtils.isEmpty( (Collection)value )){
			    continue;
			}
			
			String firstPart = StringUtils.substringBefore(key, "_");
			String propertyTypeCode = StringUtils.substring(firstPart, firstPart.length() - 1, firstPart.length());
			try {
				propertyClass = Enum.valueOf(PropertyType.class, propertyTypeCode).getValue();
			} catch (RuntimeException e) {
				throw new IllegalArgumentException("filter名称" + key + "没有按规则编写,无法得到属性值类型.", e);
			}
			
			
			
			String matchTypeCode = StringUtils.substring(firstPart, 0, firstPart.length() - 1);
			String propertyNameStr = StringUtils.substringAfter(key, "_");
			if(propertyClass == Enum.class){
				boolean tag = false;
				for(Class<?> clazz: enumObjects){
					if(propertyNameStr.equalsIgnoreCase(clazz.getSimpleName())){
						Class<Enum> enumObjet = (Class<Enum>) clazz;
						matchValue = Enum.valueOf(enumObjet, (String)value);
						tag = true;
						break;
					}
				}
				if(tag == false ){
					for (Entry<String, Class<?>> entry1 : enumMaps.entrySet()) {
						String key1 = entry1.getKey();
						if(propertyNameStr.equalsIgnoreCase(key1)){
							Class<Enum> enumObjet = (Class<Enum>) entry1.getValue();
							matchValue = Enum.valueOf(enumObjet, (String)value);
							break;
						}
					}
				}
			}else if (propertyClass == Collection.class){
				matchValue = value;
			}else{
                matchValue = ConvertUtils.convertStringToObject((String)value, propertyClass);
            }
			// 拆分operator与filedAttribute
			String[] names = StringUtils.split(key, "_");
			if (names.length != 2) {
				throw new IllegalArgumentException(key + " is not a valid search filter name");
			}
			String filedName = propertyNameStr;
			Operator operator = Operator.valueOf(matchTypeCode);

			// 创建searchFilter
			SearchFilter filter = new SearchFilter(filedName, operator, matchValue);
			filters.put(key, filter);
		}

		return filters;
	}
}
