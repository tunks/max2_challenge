package com.max2.parser;

import com.max2.parser.formatter.BaseFormatter;
import com.max2.parser.formatter.CSVFomatter1;
import com.max2.parser.handle.DataHandler;
import com.max2.web.support.FormatPattern;

/**
 * Parser Factory concrete implementation 
 * 
 *  @author ebrimatunkara
 ***/
public  class ParserFactoryImpl implements BaseParserFactory{
	private BaseFormatter defaultFormatter;
	//inject data handle via the constructor
	private DataHandler dataHandler;

	public ParserFactoryImpl(DataHandler dataHandler) {
		this.dataHandler = dataHandler;
	}

	@Override
	public BaseFormatter getFormatterInstance() {
		if(defaultFormatter == null) {
			defaultFormatter = newFormatter();
		}
		return defaultFormatter;
	}

	@Override
	public BaseFormatter newFormatter() {
		return newFormatter(FormatterType.CSV);
	}

	@Override
	public BaseFormatter newFormatter(FormatterType type) {
		switch(type) {
		  case CSV:
		  default:
			  return getCSVFormatters();
		}
	}
	
	/**
	 * Get CSV formatters chained together
	 * 
	 *  @return Formatter
	 ***/
	@SuppressWarnings("rawtypes")
	private BaseFormatter getCSVFormatters() {
		    int i=1;
		    CSVFomatter1 formatter = new CSVFomatter1(FormatPattern.getFormatPattern(i),dataHandler);
		    CSVFomatter1 chainedFormatter = formatter,
		    		         nextFormatter ;
		    //Create a dynamic chain of the formatters -- Chain of Responsibility(COR)
		    for(i = i + 1; i<=FormatPattern.DATA_PATTERNS.size(); i++) {
		    	    nextFormatter = new CSVFomatter1(FormatPattern.getFormatPattern(i),dataHandler);
		    	    chainedFormatter.setNextFormatter(nextFormatter);
		    	    chainedFormatter = nextFormatter;
		    }
		    
		    return formatter;
	}

}
