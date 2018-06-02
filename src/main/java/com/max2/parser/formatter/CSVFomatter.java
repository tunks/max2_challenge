package com.max2.parser.formatter;

import java.util.HashMap;
import java.util.Map;

import com.max2.parser.DataHandler;
import com.max2.support.DataPattern;
import com.max2.support.FormatPattern;

/**
 * CSV Formatter implementation
 * 
 *  @author ebrimatunkara
 ***/
public class CSVFomatter<T> extends AbstractFormatter<T,String>{  
	/**
	 * Handle transforms the formatted data to runtime POJO
	 **/
	private DataPattern dataPattern;
	private DataHandler<T,Map<String,String>> dataHandler;
	
	public CSVFomatter( DataPattern dataPattern , DataHandler handler) {
		this.dataPattern = dataPattern;
		this.dataHandler = handler;
	}

	/**
	 * Process input data to valid object type
	 * 
	 * @param data
	 ***/
	@Override
	protected T process(String data) {
		String [] items = data.split(FormatPattern.DATA_DELIMITER);
		if(items.length != dataPattern.getPatterns().length) {
			return null;
		}
		String patterRegex;
		String value;
		String field;
		Map<String,String> valueMap = new HashMap();
		for(int i =0; i < items.length; i++) {
			value = items[i].trim();
			patterRegex = dataPattern.getPatterns()[i];
			field = dataPattern.getPatternsMapper()[i];
			if(!FormatPattern.isValidatePattern(patterRegex,value)) {
				return null;
			}
			
			if(FormatPattern.FULL_NAME.equals(field)) {
				String[] name = value.split(" ",2);
				if(name.length <2) {
					return null;
				}
			     valueMap.put(FormatPattern.FIRST_NAME, name[0]);
			     valueMap.put(FormatPattern.LAST_NAME, name[1]);
			}
			else {
			    valueMap.put(field, value);
			}
		}
		return dataHandler.handle(valueMap);
	}
}
