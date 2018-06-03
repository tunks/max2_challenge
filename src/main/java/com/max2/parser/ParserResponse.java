package com.max2.parser;


/**
 *  Parser Response 
 */
public interface ParserResponse {
	/**
	 *  Get number of parser invalid errors lines on input data 
	 *  
	 * @return Long
	 */
    public long getNumberOfErrors();
	
    /**
	 *  Get number of parser success valid lines on input data 
	 *  
	 * @return Long
	 */
    public long getNumberOfSucccess();
}
