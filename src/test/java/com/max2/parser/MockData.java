package com.max2.parser;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MockData {
    public static  List<String> VALID_DATA = new ArrayList();
    public static  List<String> INVALID_DATA = new ArrayList();
    public static String PERSON_COLOR_ENDPOINT_URL = "/max2/api/v1/persons/colors";
    public static String VENUE_ENDPOINT_URL = "/max2/api/v1/venues";


    static {
    	    /**
    	     * Invalid mock data 
    	     **/
	    	INVALID_DATA.add("Donald Duck, 1 Disneyland, 99999, 876-543-2104, Golden, sss, yyy"); //invalid data
	    	INVALID_DATA.add("96 Drive, Donald, 99999, 646 111 0101, Golden");  //invalid
        
        /***
         * Valid mock data 
         ***/
        VALID_DATA.add("Duck, Donald, (703)-742-0996, Golden, 99999");
        VALID_DATA.add("Donald Duck, Golden, 99999-1234, 703 955 0373");
        VALID_DATA.add("Donald, Duck, 99999, 646 111 0101, Golden");
        VALID_DATA.add("John Max, 1 Church Street, 99999, 876-543-2104, Green");
        VALID_DATA.add("Keri Peterson, 20 45th Avenue , 99999, 876-543-2104, Golden");
    }
    
	public static byte[] getMockDataInBytes(List<String> inputs) throws IOException {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		for(String data: inputs) {
			outputStream.write(data.concat(System.lineSeparator()).getBytes("UTF-8"));
		}
       return outputStream.toByteArray();
	}

}
