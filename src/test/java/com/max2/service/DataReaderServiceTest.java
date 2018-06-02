package com.max2.service;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.max2.parser.MockData;
import com.max2.parser.ParserResponse;
import com.max2.parser.reader.DataReaderException;
import com.max2.support.DataReaderOperation;
import com.max2.web.Max2Application;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=Max2Application.class)
public class DataReaderServiceTest {
	
	@Autowired
	private DataReaderOperation<ParserResponse> dataReaderService;
	
	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testReadAsBytes() throws IOException {
		ParserResponse response = dataReaderService.readAsBytes(getMockDataInBytes(MockData.VALID_DATA));
		Assert.assertTrue(response.getNumberOfSucccess().equals(new Long(MockData.VALID_DATA.size())));	
		Assert.assertTrue(response.getNumberOfErrors().equals(new Long(0)));	
	}
	
	@Test
	public void testReadAsInputStream() throws IOException {
		ParserResponse response = dataReaderService.readAsInputStream(new ByteArrayInputStream(getMockDataInBytes(MockData.VALID_DATA)));
	    Assert.assertTrue(response.getNumberOfSucccess().equals(new Long(MockData.VALID_DATA.size())));	
	    Assert.assertTrue(response.getNumberOfErrors().equals(new Long(0)));	
	}
	
	@Test
	public void testReadAsInputStreamWithInvalidData() throws IOException {
		ParserResponse response = dataReaderService.readAsInputStream(new ByteArrayInputStream(getMockDataInBytes(MockData.INVALID_DATA)));
	    Assert.assertTrue(response.getNumberOfSucccess().equals(new Long(0)));	
	    Assert.assertTrue(response.getNumberOfErrors().equals(new Long(MockData.INVALID_DATA.size())));	
	 }
	
	private byte[] getMockDataInBytes(List<String> inputs) throws IOException {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		for(String data: inputs) {
			outputStream.write(data.concat(System.lineSeparator()).getBytes("UTF-8"));
		}
       return outputStream.toByteArray();
	}

}
