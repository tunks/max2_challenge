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
		    CSVFomatter1 formatter1 = new CSVFomatter1(FormatPattern.getFormatPattern(1),dataHandler);
		    CSVFomatter1 formatter2 = new CSVFomatter1(FormatPattern.getFormatPattern(2),dataHandler);
		    CSVFomatter1 formatter3 = new CSVFomatter1(FormatPattern.getFormatPattern(3),dataHandler);
		    CSVFomatter1 formatter4 = new CSVFomatter1(FormatPattern.getFormatPattern(4),dataHandler);    
		    //create a chain among the the different CSV formatters
		    formatter1.setNextFormatter(formatter2);
		    formatter2.setNextFormatter(formatter3);
		    formatter3.setNextFormatter(formatter4);
		    return formatter1;
	}

}
