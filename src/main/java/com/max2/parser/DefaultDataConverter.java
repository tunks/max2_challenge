package com.max2.parser;

import java.io.IOException;
import java.util.Map;

import org.springframework.core.convert.converter.Converter;

import com.max2.support.DataUtil;
/**
 * Data converter implementation transform map values into POJO objects
 * 
 *  @author ebrimatunkara
 **/
public class DefaultDataConverter<T> implements Converter<Map<String,String>, T> {
    private Class<T> classType;
   
	public DefaultDataConverter(Class<T> classType) {
		this.classType = classType;
	}

	/**
	 *  Mapped data handling into POJO
	 *  
	 *  @param data: Map
	 *  @return T , returns object type T when data is valid else returns null 
	 ***/
	@Override
	public T convert(Map<String, String> data) {
		try {
			T obj = DataUtil.mapToObject(data, classType);
			return obj;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
