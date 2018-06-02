package com.max2.parser.formatter;

/**
 * Base formatter to format data
 * 
 *  @author ebrimatunkara
 ***/
public interface Formatter<T,V> {
	/**
	 *  @param data
	 *  @return  
	 **/
    public T format( V data);
}
