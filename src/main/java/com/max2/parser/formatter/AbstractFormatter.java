package com.max2.parser.formatter;

/**
 * Abstract data formatter
 *  
 *  @author ebrimatunkara
 * **/
public abstract class AbstractFormatter<T,V> implements BaseFormatter<T,V>{
    private AbstractFormatter<T,V> nextFormatter;

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
	
	
	public AbstractFormatter<T,V> getNextFormatter() {
		return nextFormatter;
	}


	public void setNextFormatter(AbstractFormatter nextFormatter) {
		this.nextFormatter = nextFormatter;
	}

	abstract protected T process(V data);
}
