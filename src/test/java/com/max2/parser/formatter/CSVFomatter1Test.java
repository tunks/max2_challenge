package com.max2.parser.formatter;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.max2.model.Person;
import com.max2.parser.MockData;
import com.max2.parser.handle.DefaultDataHandle;
import com.max2.web.support.DataPattern;
import com.max2.web.support.FormatPattern;

public class CSVFomatter1Test {
    private CSVFomatter1<Person> formatter;
    /**
     * 1. Duck, Donald, (703)-742-0996, Golden, 99999
(firstname, lastname, number, color, zipcode)
2. Donald Duck, Golden, 99999-1234, 703 955 0373
(firstname lastname, color, zipcode, phone number)
3. Donald, Duck, 99999, 646 111 0101, Golden
(firstname, lastname, zipcode, phone number, color)
4. Donald Duck, 1 Disneyland, 99999, 876-543-2104, Golden
(firstname lastname, address, zipcode, phone number, color)
     * */
	@Before
	public void setUp() throws Exception {
		//rn(new String[] { WORDS_REGEX, WORDS_REGEX, PHONE_NUMBER_REGEX, WORDS_REGEX, ZIPCODE_REGEX },
        //new String[] { FIRST_NAME, LAST_NAME, PHONE_NUMBER, COLOR, ZIP_CODE });
       formatter = new CSVFomatter1(FormatPattern.getFormatPattern(1), new DefaultDataHandle<Person>(Person.class));
 
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFormat() {
	   for(String d: MockData.data) {
		formatter.format(d);
	   }
	}
}
