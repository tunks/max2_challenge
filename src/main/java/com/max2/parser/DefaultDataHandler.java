package com.max2.parser;

import java.io.IOException;
import java.util.Map;

import com.max2.support.DataUtil;
/**
 * Data Handler implementation transform map values into POJO objects
 * 
 *  @author ebrimatunkara
 **/
public class DefaultDataHandler<T> implements DataHandler<T, Map<String,String>> {
    private Class<T> classType;
   
	public DefaultDataHandler(Class<T> classType) {
		super();
		this.classType = classType;
	}

	/**
	 *  Mapped data handling into POJO
	 *  
	 *  @param data: Map
	 *  @return T , returns object type T when data is valid else returns null 
	 ***/
	@Override
	public T handle(Map<String, String> data) {
		try {
			T obj = DataUtil.mapToObject(data, classType);
			System.out.println(" obj :"+obj);
			return obj;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
