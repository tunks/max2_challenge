package com.max2.parser.reader;

import java.io.File;
import java.io.InputStream;
import java.net.URI;

/***
 * Data reader
 * 
 *  @author ebrimatunkara
 **/
public interface DataReader {
	  /**
	   * Read string input line
	   * 
	   * @param line
	   **/
       public void read(String line) throws DataReaderException;
       /**
 	   * Read input stream bytes
 	   * 
 	   * @param inputstream
 	   **/
       public void read(InputStream inputStream) throws DataReaderException;
       /**
  	   * Read input File
  	   * 
  	   * @param File
  	   **/
       public void read(File file) throws DataReaderException;
}
