package com.max2.parser.handle;

import java.io.IOException;
import java.util.Map;

import com.max2.web.support.DataUtil;
/**
 * Data Handler implementation transform map values into POJO objects
 * 
 *  @author ebrimatunkara
 **/
public class DefaultDataHandle<T> implements DataHandler<T, Map<String,String>> {
    private Class<T> classType;
   
	public DefaultDataHandle(Class<T> classType) {
		super();
		this.classType = classType;
	}

	/**
	 *  Mapped data handling into POJO
	 *  
	 *  @param data: Map
	 *  @return T 
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
