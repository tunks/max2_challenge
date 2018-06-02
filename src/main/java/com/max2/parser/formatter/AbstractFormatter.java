package com.max2.parser.formatter;

/**
 * Abstract data formatter
 *  
 *  @author ebrimatunkara
 * **/
public abstract class AbstractFormatter<T,V> implements Formatter<T,V>{
    private AbstractFormatter<T,V> nextFormatter;

    /***
     * Format input data to object type
     * 
     * @param data
     * @return object type
     */
	@Override
	public T format(V data) {
        	 T object = process(data);
        	 if(object !=null) {
        		return object;
        	 }
        
        if(getNextFormatter() != null) {
        	   return getNextFormatter().format(data);
        }
        return null;
	}
	
	/**
	 * Get next formatter
	 * 
	 * @param AbstractFormatter 
	 */
	public AbstractFormatter<T,V> getNextFormatter() {
		return nextFormatter;
	}

   /**
    * Set next formatter in the linked formatter chain
    * 
    * @param nextFormatter
    **/
	public void setNextFormatter(AbstractFormatter<T,V> nextFormatter) {
		this.nextFormatter = nextFormatter;
	}

	abstract protected T process(V data);
}
