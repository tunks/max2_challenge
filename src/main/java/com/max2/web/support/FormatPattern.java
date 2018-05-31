package com.max2.web.support;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Data format pattern
 * 
 * @author ebrimatunkara
 **/
public class FormatPattern {
	public static String DATA_DELIMITER = ",";
	public static String FIELD_DELIMITER = "_";
	public static String WORDS_REGEX = "^[a-zA-Z ]*$";
	public static String PHONE_NUMBER_REGEX = "^\\(?([0-9]{3})\\)?[-\\s]+([0-9]{3})[-\\s]+([0-9]{4})$";
	public static String ZIPCODE_REGEX = "^\\d{5}(?:[-\\s]\\d{4})?$";
	public static String ADDRESS_REGEX = "(\\d+\\s?[a-zA-Z]+\\s*([a-zA-Z]+)?)$";

	public static String FIRST_NAME = "firstName";
	public static String LAST_NAME = "lastName";
	public static String FULL_NAME = FIRST_NAME.concat(FIELD_DELIMITER).concat(LAST_NAME);
	public static String ADDRESS = "address";
	public static String COLOR = "color";
	public static String ZIP_CODE = "zipcode";
	public static String PHONE_NUMBER = "phoneNumber";
	public static List<DataPattern> patterns = new ArrayList();
	

	/**
	 * Get format pattern and return list
	 * 
	 * @param format  option
	 * 
	 * @return array of format patterns in order
	 **/
	public static DataPattern getFormatPattern(int formatOption) {
		//refactoring here
		switch (formatOption) {
		case 1:
			// (firstname, lastname, number, color, zipcode)
			return new DataPattern(new String[] { WORDS_REGEX, WORDS_REGEX, PHONE_NUMBER_REGEX, WORDS_REGEX, ZIPCODE_REGEX },
					               new String[] { FIRST_NAME, LAST_NAME, PHONE_NUMBER, COLOR, ZIP_CODE });
		case 2:
			// (firstname lastname, color, zipcode, phone number)
			return new DataPattern(new String[] { WORDS_REGEX, WORDS_REGEX, ZIPCODE_REGEX, PHONE_NUMBER_REGEX },
					               new String[] { FULL_NAME, COLOR, ZIP_CODE, PHONE_NUMBER });
		case 3:
			// (firstname, lastname, zipcode, phone number, color)
			return new DataPattern(
					new String[] { WORDS_REGEX, WORDS_REGEX, ZIPCODE_REGEX, PHONE_NUMBER_REGEX, WORDS_REGEX },
					new String[] { FIRST_NAME, LAST_NAME, ZIP_CODE, PHONE_NUMBER, COLOR });
		case 4:
			// (firstname lastname, address, zipcode, phone number, color)
			// data.add("Donald Duck, 1 Disneyland, 99999, 876-543-2104, Golden");

			return new DataPattern(
					new String[] { WORDS_REGEX, ADDRESS_REGEX, ZIPCODE_REGEX, PHONE_NUMBER_REGEX, WORDS_REGEX },
					new String[] { FULL_NAME, ADDRESS, ZIP_CODE, PHONE_NUMBER, COLOR });
		}
		return null;
	}
	
	/***
	 * Validate pattern string with given regex
	 * 
	 * @param patternRegex
	 * @param value
	 * @return boolean
	 **/
	public static boolean isValidatePattern(String patternRegex, String value) {
		Pattern pattern = Pattern.compile(patternRegex);
		Matcher matcher = pattern.matcher(value);
		return matcher.matches();
	}
}
