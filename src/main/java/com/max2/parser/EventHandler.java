package com.max2.parser;

import com.max2.parser.formatter.Formatter;

/**
 * Event handler handles formatter action on data
 *
 ***/
public interface EventHandler<T> {
	    /** 
	     * Handle format data action
	     *  
	     *  @param  data
	     **/
        void handle(Formatter formatter, T data);
}
