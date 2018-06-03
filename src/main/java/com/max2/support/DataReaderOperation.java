package com.max2.support;
import java.io.InputStream;
import com.max2.parser.reader.DataReaderException;

/**
 *  Data reader operations that reads input data feeds
 *  
 *  @author ebrimatunkara
 **/
public interface DataReaderOperation<R> {
	/**
	 * Read and process input data as bytes
	 * 
	 * @param bytes
	 * @return R response type
	 * @throws DataReaderException
	 */
    public R readAsBytes(byte[] bytes) throws DataReaderException;
    
	/**
	 * Read and process input data as inputStreams
	 * 
	 * @param bytes
	 * @return R response type
	 * @throws DataReaderException
	 */
    public R readAsInputStream(InputStream inputstream) throws DataReaderException;
}
