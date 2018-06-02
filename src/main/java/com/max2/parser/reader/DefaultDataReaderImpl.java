package com.max2.parser.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.commons.io.FileUtils;
import com.max2.parser.EventHandler;
import com.max2.parser.formatter.Formatter;

/**
 * Bytes input stream data reader
 * 
 * @author ebrimatunkara
 **/
public class DefaultDataReaderImpl implements DataReader {
	private EventHandler<String> eventHandler;
	@SuppressWarnings("rawtypes")
	private Formatter formatter;

	public DefaultDataReaderImpl(EventHandler<String> eventHandler, Formatter formatter) {
		super();
		this.eventHandler = eventHandler;
		this.formatter = formatter;
	}

	@Override
	public void read(InputStream item) throws DataReaderException {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(item));
			String line;
			while ((line = br.readLine()) != null) {
				read(line);
			}
			br.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void read(String data) throws DataReaderException {
		eventHandler.handle(formatter, data);
	}

	@Override
	public void read(File file) throws DataReaderException {
		try {
			InputStream inputStream = FileUtils.openInputStream(file);
			read(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
