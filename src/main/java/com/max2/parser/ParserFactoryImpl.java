package com.max2.parser;

import com.max2.parser.formatter.Formatter;
import com.max2.parser.reader.DataReader;
import com.max2.parser.reader.DefaultDataReaderImpl;

import org.springframework.core.convert.converter.Converter;

import com.max2.parser.formatter.CSVFomatter;
import com.max2.support.FormatPattern;

/**
 * Parser Factory concrete implementation 
 * 
 *  @author ebrimatunkara
 ***/
public  class ParserFactoryImpl implements ParserFactory{
	private Formatter defaultFormatter;
	//inject data handle via the constructor
	private Converter converter;

	public ParserFactoryImpl(Converter converter) {
		this.converter = converter;
	}
	/**
	 * {@inheritDoc} 
	 * 
	 * @return Formatter
	 */
	@Override
	public Formatter getFormatterInstance() {
		if(defaultFormatter == null) {
			defaultFormatter = newFormatter();
		}
		return defaultFormatter;
	}
	/**
	 * {@inheritDoc} 
	 * 
	 * @return Formatter
	 */
	@Override
	public Formatter newFormatter() {
		return newFormatter(FormatterType.CSV);
	}

	/**
	 * {@inheritDoc} 
	 * @param type
	 * @return Formatter
	 */
	@Override
	public Formatter newFormatter(FormatterType type) {
		switch(type) {
		  case CSV:
		  default:
			  return getDynamicCSVFormatters();
		}
	}
	
	/**
	 * Get Dynamic CSV formatters chained together
	 * 
	 *  @return Formatter
	 ***/
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Formatter getDynamicCSVFormatters() {
		    int i = 1;
		    CSVFomatter formatter = new CSVFomatter(FormatPattern.getFormatPattern(i),converter);
		    CSVFomatter chainedFormatter = formatter,
		    		         nextFormatter ;
		    //Create a dynamic chain of the formatters -- Chain of Responsibility(COR)
		    for(i = i + 1; i<=FormatPattern.DATA_PATTERNS.size(); i++) {
		    	    nextFormatter = new CSVFomatter(FormatPattern.getFormatPattern(i),converter);
		    	    chainedFormatter.setNextFormatter(nextFormatter);
		    	    chainedFormatter = nextFormatter;
		    }
		    
		    return formatter;
	}

	 /***
	  * {@inheritDoc} 
	  * 
	  * @param eventHandler
	  * @return DataReader
	 */
	@Override
	public DataReader newDataReaderInstance(EventHandler eventHandler) {
		return new DefaultDataReaderImpl(eventHandler,getFormatterInstance());
	}

}
