package com.credit.suisse.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.credit.suisse.entity.CreditSuissesServerLog;
import com.credit.suisse.process.CreditSuisseLogsCollectionBuilder;
import com.credit.suisse.repo.CreditSuisseRepo;
import com.credit.suisse.utility.CreditSuisseCommonUtil;

@Service("CreditSuisseServiceImpl")
public class CreditSuisseServiceImpl implements CreditSuisseService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CreditSuisseServiceImpl.class);

	@Autowired
	CreditSuisseRepo creditSuisseRepo;

	@Autowired
	ResourceLoader resourceLoader;

	@Override
	public String saveEvents() throws FileNotFoundException, InterruptedException {
		LOGGER.info(CreditSuisseCommonUtil.SAVE_EVENTS_SERVICE_STRT);

		String message = "";
		try {
			Resource resource = resourceLoader.getResource("classpath:CreditSuisseLogfile.txt");
			File file = resource.getFile();

			CreditSuisseLogsCollectionBuilder listBuilder = new CreditSuisseLogsCollectionBuilder();
			List<CreditSuissesServerLog> arr = listBuilder.parseFile(file);
			listBuilder.setTheAlertFlagsForDelayedEvents(arr);

			creditSuisseRepo.saveAll(arr);

			if (CreditSuisseLogsCollectionBuilder.isPreviewLogApproved()) {
				listBuilder.getTheEventList(arr, 3);
			}
			message = CreditSuisseCommonUtil.FILE_UPLD_SUCSS + file.getName();
			return message;
		} catch (Exception e) {
			message = CreditSuisseCommonUtil.FILE_UPLD_ERR + e.getMessage();
			LOGGER.info(CreditSuisseCommonUtil.UPLOAD_FILE_METHOD_CNTRL_END + "Catch Block");

			LOGGER.info(CreditSuisseCommonUtil.SAVE_EVENTS_DELEGATE_END);
			return message;
		}
	}
}
