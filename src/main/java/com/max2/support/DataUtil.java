package com.max2.support;

import java.io.IOException;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Data utility helper class
 * 
 * @author ebrimatunkara
 **/
public class DataUtil {
   private static ObjectMapper mapper;

   static {
	   mapper = new ObjectMapper();
	   mapper.setSerializationInclusion(Include.NON_NULL);
	   mapper.setSerializationInclusion(Include.NON_EMPTY);
	   mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
   }
   
   /**
    * Convert object to JSON string
    * 
    * @param object
    * @return String
    * @throws JsonProcessingException
    **/
   public static String objectToJson(Object object) throws JsonProcessingException{
	   return mapper.writeValueAsString(object);
   }
   
   /**
    * Convert Map  to object
    * 
    * @param Map
    * @param classType
    * @return <T>
    * @throws IOException 
    **/
   public static <T> T mapToObject(Map map, Class<?> classType) throws IOException{
	   byte[] data = mapper.writeValueAsBytes(map);
	   return jsonBytesToObject(data,classType);
   }
   
   /**
    * Convert JSON string to object
    * 
    * @param json
    * @param classType
    * @return <T>
    * @throws IOException 
    **/
   public static <T> T jsonToObject(String json, Class<?> classType) throws IOException{
	   return (T) mapper.readValue(json, classType);
   }
   
   
   /**
    * Convert JSON bytes to object
    * 
    * @param json
    * @param classType
    * @return <T>
    * @throws IOException 
    **/
   public static <T> T jsonBytesToObject(byte[] data, Class<?> classType) throws IOException{
	   return (T) mapper.readValue(data, classType);
   }

}
