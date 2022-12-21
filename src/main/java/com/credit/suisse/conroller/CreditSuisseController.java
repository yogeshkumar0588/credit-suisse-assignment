package com.credit.suisse.conroller;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.credit.suisse.delegate.CreditSuisseDelegate;

@RestController
@RequestMapping("/creditSuisse")
public class CreditSuisseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CreditSuisseController.class);
	
	@Autowired
	CreditSuisseDelegate creditSuisseDelegate;
	
	@Autowired
	ResourceLoader resourceLoader;
	
	@RequestMapping(value = "/getCall" , produces = {MediaType.APPLICATION_JSON_VALUE} , consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<String> getCall() {
		LOGGER.info("Inside getCall()");
		return new ResponseEntity<>("Test",HttpStatus.OK);
	}
	
	@RequestMapping("/uploadFile")
	  public ResponseEntity<String> uploadFile() {
	    String message = "";
	    try {
	    	Resource resource=resourceLoader.getResource("classpath:CreditSuisseLogfile.txt");
	    	File file2 = resource.getFile();
	    	    
			List<String> allLines = Files.readAllLines(Paths.get(file2.getAbsolutePath()));
			
			String text = "";
			for (String string : allLines) {
				text = string;
			}
			String[] array = text.split("{");
			for (String string : array) {
				System.out.println(string);
			}
			
	    	LOGGER.info("Inside uploadFile()");

	      message = "Uploaded the file successfully: " + file2.getName();
	      return ResponseEntity.status(HttpStatus.OK).body(message);
	    } catch (Exception e) {
	      message = "Could not upload the file: " + ". Error: " + e.getMessage();
	      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
	    }
	  }
}
