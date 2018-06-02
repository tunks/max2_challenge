package com.max2.parser;

/**
 * Base data handle interface 
 *
 ***/
public interface DataHandler<T,V> {
	    /** 
	     *  Transform data to Object type
	     *  
	     *  @param  data
	     *  @return T
	     **/
        public T handle(V data);
}
