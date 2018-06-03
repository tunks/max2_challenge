package com.max2.parser.reader;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import org.assertj.core.util.Files;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.max2.model.PersonColor;
import com.max2.parser.ParserFactory;
import com.max2.parser.MockData;
import com.max2.parser.ParseEventHandler;
import com.max2.parser.ParseEventHandler.ParserEvent;
import com.max2.starter.Max2Application;

public abstract class MockAbstractDataReader {    
    private DataReader dataReader;
	
    @Autowired
    private ParserFactory parserFactory;
  
    protected List<PersonColor> personResults = new ArrayList();
    
    private ParseEventHandler eventHandle ;
	
	public void setUp() throws Exception {
		eventHandle = new ParseEventHandler();
		dataReader = parserFactory.newDataReaderInstance(eventHandle);
		eventHandle.addObserver(new DataReaderObserver());
	}

	/**
	 * Read from intputStream and process data
	 * 
	 **/
	public void readFromInputStream() throws IOException {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		for(String data: MockData.VALID_DATA) {
			outputStream.write(data.concat(System.lineSeparator()).getBytes("UTF-8"));
		}
		InputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
		dataReader.read(inputStream);
	}

	public void readFromString() throws DataReaderException {
		for(String data: MockData.VALID_DATA) {
		   dataReader.read(data);
		}
	}

	/**
	 * Read from File input
	 * 
	 **/
	public void readFromFile() throws IOException {
 	   File tempFile = File.createTempFile("max2_valid_data_test", ".tmp"); 
       BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));
       for(String data: MockData.VALID_DATA) {
			bw.write(data.concat(System.lineSeparator()));
		}
       bw.close();
       dataReader.read(tempFile);
	   Files.delete(tempFile);
	}

	private class DataReaderObserver implements Observer{
		@Override
		public void update(Observable o, Object arg) {
			  if(arg instanceof ParserEvent) {
				  ParserEvent event  = (ParserEvent) arg;
				  if(event.getObject() != null && event.getObject() instanceof PersonColor)
				   personResults.add((PersonColor) event.getObject());
			  }
		}
	}
}
