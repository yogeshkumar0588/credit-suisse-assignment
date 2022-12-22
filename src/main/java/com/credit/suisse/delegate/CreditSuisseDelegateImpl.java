package com.credit.suisse.delegate;

import java.io.FileNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.credit.suisse.service.CreditSuisseService;
import com.credit.suisse.utility.CreditSuisseCommonUtil;

@Service("CreditSuisseDelegateImpl")
public class CreditSuisseDelegateImpl implements CreditSuisseDelegate {

	private static final Logger LOGGER = LoggerFactory.getLogger(CreditSuisseDelegateImpl.class);

	@Autowired
	CreditSuisseService creditSuisseService;

	@Override
	public String saveEvents() throws FileNotFoundException, InterruptedException {
		String message = "";
		LOGGER.info(CreditSuisseCommonUtil.SAVE_EVENTS_DELEGATE_STRT);
		message = creditSuisseService.saveEvents();
		LOGGER.info(CreditSuisseCommonUtil.SAVE_EVENTS_DELEGATE_END);
		return message;
	}

}
