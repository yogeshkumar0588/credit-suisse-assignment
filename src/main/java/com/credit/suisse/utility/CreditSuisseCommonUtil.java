package com.credit.suisse.utility;

import org.springframework.stereotype.Service;

@Service
public class CreditSuisseCommonUtil {

	public static final String START = "START";
	public static final String END = "END";
	public static final String UPLOAD_FILE_METHOD_CNTRL_STRT = "=== CreditSuisseController uploadFile() START === ";
	public static final String UPLOAD_FILE_METHOD_CNTRL_END = "=== CreditSuisseController uploadFile() END === ";
	public static final String SAVE_EVENTS_DELEGATE_STRT = "=== CreditSuisseDelegateImpl saveEvents() START === ";
	public static final String SAVE_EVENTS_DELEGATE_END = "=== CreditSuisseDelegateImpl saveEvents() END === ";
	public static final String SAVE_EVENTS_SERVICE_STRT = "=== CreditSuisseServiceImpl saveEvents() START === ";
	public static final String SAVE_EVENTS_SERVICE_END = "=== CreditSuisseServiceImpl saveEvents() END === ";
	public static final String FILE_UPLD_SUCSS = "Uploaded the file successfully: ";
	public static final String FILE_UPLD_ERR = "Could not upload the file: ";
	public static final String FILE_NOT_FOUND_AT_PATH = "The JSON file has not been found in the following path: ";
	
}
