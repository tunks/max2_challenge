package com.max2.parser;

import com.max2.parser.formatter.Formatter;
import com.max2.parser.reader.DataReader;

/**
 * Base formatter factory
 * @author ebrimatunkara 
 **/
public interface ParserFactory {
	  /**
	   * Get and return new or existing formatter instance(singleton)
	   * 
	   * @return BaseFormatter
	   **/
       public Formatter getFormatterInstance();
       
       /**
 	   * Get new DataReader instance
 	   * 
 	   * @param eventHandler
 	   * @return DataReader
 	   **/
        public DataReader newDataReaderInstance(EventHandler eventHandle);
	  /**
	   * Get and return new formatter instance
	   * 
	   * @return BaseFormatter
	   **/
       public Formatter newFormatter();
     
       /**
 	   * Get and return formatter instance
 	   * 
 	   * @param formatter type
 	   * @return BaseFormatter
 	   **/
       public Formatter newFormatter( FormatterType type);

       /***
        * Formatter types
        *  
        **/
       public static enum FormatterType{
    	       CSV,
    	       PLAIN
       }
}
