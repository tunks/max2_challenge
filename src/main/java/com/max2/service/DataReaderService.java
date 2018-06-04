package com.max2.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.max2.model.PersonColor;
import com.max2.parser.ParserResponseImpl;
import com.max2.parser.ParseEventHandler;
import com.max2.parser.ParseEventHandler.ParserEvent;
import com.max2.parser.ParserFactory;
import com.max2.parser.ParserResponse;
import com.max2.parser.reader.DataReader;
import com.max2.parser.reader.DataReaderException;
import com.max2.support.DataReaderOperation;
import com.max2.support.WriteOperation;

/**
 * Dynamic data reader service -- reads and processes the data inputs 
 * 
 * @author ebrimatunkara
 * 
 **/
@Service("dataReaderService")
public class DataReaderService implements DataReaderOperation<ParserResponse> {
	private static final Logger logger = LoggerFactory.getLogger(DataReaderService.class);

	@Autowired
	@Qualifier("personColorService")
	private WriteOperation<PersonColor> writeOperation;

	@Autowired
	private ParserFactory parserFactory;

	/***
	 * Read input data of the form of bytes array and parse the data
	 * 
	 *  @param bytes 
	 */
	@Override
	public ParserResponse readAsBytes(byte[] bytes) throws DataReaderException {
		return readAsInputStream(new ByteArrayInputStream(bytes));
	}
	/***
	 * Read input data of inputStream and parse the data
	 * 
	 *  @param bytes 
	 */
	@Override
	public ParserResponse readAsInputStream(InputStream inputstream) throws DataReaderException {
		ParserResponseImpl parserResponse = new ParserResponseImpl();
		List<Observer> observers = Collections.singletonList(new WriteOperationObserver(parserResponse));
		try {
			getDataReader(observers).read(inputstream);
		} catch (DataReaderException e) {
			logger.error("Error processing data reader: "+e.getMessage());
		}
		return parserResponse;
	}

	private DataReader getDataReader(List<Observer> observers) {
		ParseEventHandler eventHandle = new ParseEventHandler();
		observers.stream().forEach(observer -> {
			eventHandle.addObserver(observer);
		});
		return parserFactory.newDataReaderInstance(eventHandle);
	}
	
	/**
	 * Write Operation observer -- gets notification from the parser event handler delegated by the formatter and saves object to database
	 * 
	 **/
	private class WriteOperationObserver implements Observer {
		private ParserResponseImpl parserResponse;
		
		public WriteOperationObserver(ParserResponseImpl parserResponse) {
			this.parserResponse = parserResponse;
		}

		@Override
		public void update(Observable o, Object arg) {
			if (arg instanceof ParserEvent) {
				ParserEvent event = (ParserEvent) arg;
				if (event.getObject() != null && event.getObject() instanceof PersonColor) {
					writeOperation.save((PersonColor) event.getObject());
					logger.info("Number of success lines : "+parserResponse.success().incrementAndGet());
				} else {
					logger.warn("Invalid data: " + event.getOriginalData());
					logger.info("Number of invalid lines : "+parserResponse.errors().incrementAndGet());
				}
			}
		}
	}
}
