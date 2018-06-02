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
import com.max2.parser.DefaultDataHandler;
import com.max2.parser.MockData;
import com.max2.parser.ParseEventHandler;
import com.max2.parser.ParseEventHandler.ParserEvent;
import com.max2.parser.ParserFactoryImpl;
import com.max2.support.WriteOperation;
import com.max2.web.Max2Application;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=Max2Application.class)
@ActiveProfiles("dev")
public class DefaultDataReaderImplTest {    
    private DataReader dataReader;
	
    @Autowired
    private ParserFactory parserFactory;
  
    private List<PersonColor> personResults = new ArrayList();
    
    private ParseEventHandler eventHandle ;
	
    @Before
	public void setUp() throws Exception {
		eventHandle = new ParseEventHandler();
		//ParserFactory parserFactory = new ParserFactoryImpl(new DefaultDataHandler(Person.class));
		dataReader = parserFactory.newDataReaderInstance(eventHandle);
		eventHandle.addObserver(new DataReaderObserver());
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Read from intputStream and process data
	 * 
	 **/
	@Test
	public void testReadInputStream() throws IOException {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		for(String data: MockData.VALID_DATA) {
			outputStream.write(data.concat(System.lineSeparator()).getBytes("UTF-8"));
		}
		InputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
		dataReader.read(inputStream);
		Assert.assertEquals(personResults.size(), MockData.VALID_DATA.size());	
	}

	/**
	 * Read from String data input
	 * 
	 **/
	@Test
	public void testReadString() throws DataReaderException {
		for(String data: MockData.VALID_DATA) {
		   dataReader.read(data);
		}
		Assert.assertEquals(personResults.size(), MockData.VALID_DATA.size());	
	}

	/**
	 * Read from File input
	 * 
	 **/
	@Test
	public void testReadFile() throws IOException {
 	   File tempFile = File.createTempFile("max2_valid_data_test", ".tmp"); 
       BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));
       for(String data: MockData.VALID_DATA) {
			bw.write(data.concat(System.lineSeparator()));
		}
       bw.close();
       dataReader.read(tempFile);
	   Assert.assertEquals(personResults.size(), MockData.VALID_DATA.size());	
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
