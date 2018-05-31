package com.max2.parser;

import com.max2.parser.formatter.BaseFormatter;

/**
 * Base formatter factory
 * @author ebrimatunkara 
 **/
public interface BaseParserFactory {
	  /**
	   * Get and return new or existing formatter instance(singleton)
	   * 
	   * @return BaseFormatter
	   **/
       public BaseFormatter getFormatterInstance();
       
	  /**
	   * Get and return new formatter instance
	   * 
	   * @return BaseFormatter
	   **/
       public BaseFormatter newFormatter();
     
       /**
 	   * Get and return formatter instance
 	   * 
 	   * @param formatter type
 	   * @return BaseFormatter
 	   **/
       public BaseFormatter newFormatter( FormatterType type);

       /***
        * Formatter types
        *  
        **/
       public static enum FormatterType{
    	       CSV,
    	       PLAIN
       }
}
