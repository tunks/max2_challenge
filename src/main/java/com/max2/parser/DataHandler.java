package com.max2.parser.handle;

/**
 * Base data handle interface 
 *
 ***/
public interface DataHandler<T,V> {
        public T handle(V data);
}
