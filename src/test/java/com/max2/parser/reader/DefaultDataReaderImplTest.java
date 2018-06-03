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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.max2.parser.MockData;
import com.max2.starter.Max2Application;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=Max2Application.class)
@ActiveProfiles("test")
public class DefaultDataReaderImplTest extends MockAbstractDataReader{    
    @Before
	public void setUp() throws Exception {
    	   super.setUp();
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
		readFromInputStream();
		Assert.assertEquals(personResults.size(), MockData.VALID_DATA.size());	
	}

	/**
	 * Read from String data input
	 * 
	 **/
	@Test
	public void testReadString() throws DataReaderException {
		this.readFromString();
		Assert.assertEquals(personResults.size(), MockData.VALID_DATA.size());	
	}

	/**
	 * Read from File input
	 * 
	 **/
	@Test
	public void testReadFile() throws IOException {
		this.readFromFile();
	    Assert.assertEquals(personResults.size(), MockData.VALID_DATA.size());	
	}
}
