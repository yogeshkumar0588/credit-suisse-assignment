package com.credit.suisse.conroller;

import java.io.FileNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.credit.suisse.delegate.CreditSuisseDelegate;
import com.credit.suisse.utility.CreditSuisseCommonUtil;

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

	/*
	 * This method is used to upload the file(CreditSuisseLogfile.txt) from src/main/resources location
	 * which is JOSN format	 * 
	 * log the data in CreditSuissesServerLog table  what ever is present in CreditSuisseLogfile.txt
	 * Get the time difference from start and end If time stamp is grater than 4 ms then 
	 * make the entry in CreditSuissesServerLog table's column alert_flag as 1 other wise make it 0
	 * 
	 * */
	@RequestMapping("/uploadLogFile")
	public ResponseEntity<String> uploadEventsLogFile() throws FileNotFoundException, InterruptedException {

		String message = "";
		LOGGER.info(CreditSuisseCommonUtil.UPLOAD_FILE_METHOD_CNTRL_STRT);
		try {
			message = creditSuisseDelegate.saveEvents();
			LOGGER.info(CreditSuisseCommonUtil.UPLOAD_FILE_METHOD_CNTRL_END + "TRY Block");
			return ResponseEntity.status(HttpStatus.OK).body(message);
		} catch (Exception e) {
			message = CreditSuisseCommonUtil.FILE_UPLD_ERR + e.getMessage();
			LOGGER.info(CreditSuisseCommonUtil.UPLOAD_FILE_METHOD_CNTRL_END + "Catch Block");
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
		}

	}
}
