package com.max2.parser;

import java.util.Observable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;

import com.max2.parser.formatter.Formatter;
import com.max2.support.WriteOperation;

/***
 * ParserEventHandler handles data format action events
 * 
 * @author ebrimatunkara
 */
public class ParseEventHandler extends Observable implements EventHandler<String> {   
	private static final Logger logger = LoggerFactory.getLogger(ParseEventHandler.class);
    
	/**
	 * Handle format action of string input data, and notify observers
	 *  
	 *  @param formatter
	 *  @param data
	 **/
	@Async
	@Override
	public void handle(Formatter formatter, String data) {
              Object object = formatter.format(data);		
              updateObservers(ParserEvent.build(object, data));
	}

    private void updateObservers(Object object) {
    	   if(this.countObservers() > 0 ) {
             setChanged();
             notifyObservers(object);
       	 }
    }
    
    public static class ParserEvent{
    	      private ParserEventStatus status;
    	      private Object object;
    	      private String originalData;
			public ParserEventStatus getStatus() {
				return status;
			}
			public void setStatus(ParserEventStatus status) {
				this.status = status;
			}
			public Object getObject() {
				return object;
			}
			public void setObject(Object object) {
				this.object = object;
			}
			public String getOriginalData() {
				return originalData;
			}
			public void setOriginalData(String originalData) {
				this.originalData = originalData;
			}
			
			public static ParserEvent build(Object object, String originalData) {
				ParserEvent event = new ParserEvent();
				event.setObject(object);
				event.setOriginalData(originalData);
				if(object != null) {
				  event.setStatus(ParserEventStatus.SUCCESS);	
				}
				else {
				  event.setStatus(ParserEventStatus.FAILED);	
				}
				return event;
			}
    }
    
    public enum ParserEventStatus{
    	       SUCCESS,
    	       FAILED
    }
}
