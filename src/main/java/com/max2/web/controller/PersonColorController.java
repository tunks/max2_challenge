package com.max2.web.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.max2.parser.ParserResponse;
import com.max2.parser.reader.DataReaderException;
import com.max2.support.ColorQueryOperation;
import com.max2.support.DataReaderOperation;

/**
 * Person Color HTTP Restful controller
 * 
 * @author ebrimatunkara
 **/
@RestController
@RequestMapping("/max2/api/v1/persons/colors")
public class PersonColorController {
	private static final Logger logger = LoggerFactory.getLogger(PersonColorController.class);

	@Autowired
	@Qualifier("personColorService")
	private ColorQueryOperation personColorQueryOperation;
    @Autowired
    @Qualifier("dataReaderService")
    private DataReaderOperation<ParserResponse> dataReaderOperation;

	/**
	 *  Consume and save person color text(string) data separated by lines in bytes form 
	 * 
	 *  @param bytes
	 *  @return RespondeEntity
	 **/
	@RequestMapping(method=RequestMethod.POST, consumes = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<?> savePersonColorEntries(@RequestBody byte[] bytes) {
		try {
			 ParserResponse response = dataReaderOperation.readAsBytes(bytes);
			 return ResponseEntity.ok(response);
		} catch (DataReaderException e) {
			logger.error("Save person color failed: "+e.getMessage());
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	/**
	 * Get all count counts and persons
	 * 
	 *  @param includeNames: Boolean (optional) to include or exclude person names in the response
	 *  @return ResponseEntity
	 */
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<?> getAllPersonColorCount(
			@RequestParam(name = "names", defaultValue = "false", required = false) boolean includeNames) 
	{
		return (includeNames) ? ResponseEntity.ok(personColorQueryOperation.getAllColorCountAndPersons())
				: ResponseEntity.ok(personColorQueryOperation.getAllColorCount());

	}
	
	/**
	 * Upload any dynamic csv formatted file of  person color data 
	 * 
	 *  @param file
	 *  @return RespondeEntity
	 **/
	@RequestMapping(value="/upload", method=RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE )
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
		   try {
			    ParserResponse response = dataReaderOperation.readAsInputStream(file.getInputStream());
			    logger.info("File upload successful: "+file.getOriginalFilename());
				return ResponseEntity.ok(response);
			} catch (IOException e) {
				logger.error("File upload failed: "+e.getMessage());
				return ResponseEntity.badRequest().body("Failed to upload file");
			}
    }
}
