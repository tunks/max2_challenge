package com.max2.service;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.max2.parser.MockData;
import com.max2.parser.ParserResponse;
import com.max2.starter.Max2Application;
import com.max2.support.DataReaderOperation;

@RunWith(SpringRunner.class)
@SpringBootTest(properties= {"spring.jpa.hibernate.ddl-auto=create-drop"},classes=Max2Application.class)
@ActiveProfiles("test")
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
		ParserResponse response = dataReaderService.readAsBytes(MockData.getMockDataInBytes(MockData.VALID_DATA));
		Assert.assertTrue(response.getNumberOfSucccess()== MockData.VALID_DATA.size());	
		Assert.assertTrue(response.getNumberOfErrors() == 0);	
	}
	
	@Test
	public void testReadAsInputStream() throws IOException {
		ParserResponse response = dataReaderService.readAsInputStream(new ByteArrayInputStream(MockData.getMockDataInBytes(MockData.VALID_DATA)));
		Assert.assertTrue(response.getNumberOfSucccess()== MockData.VALID_DATA.size());	
		Assert.assertTrue(response.getNumberOfErrors() == 0);	
	}
	
	@Test
	public void testReadAsInputStreamWithInvalidData() throws IOException {
		ParserResponse response = dataReaderService.readAsInputStream(new ByteArrayInputStream(MockData.getMockDataInBytes(MockData.INVALID_DATA)));
	    Assert.assertTrue(response.getNumberOfSucccess() ==0);	
	    Assert.assertTrue(response.getNumberOfErrors()  == MockData.INVALID_DATA.size());	
	}
}
