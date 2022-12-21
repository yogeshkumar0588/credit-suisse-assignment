package com.credit.suisse.delegate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.credit.suisse.service.CreditSuisseService;

@Service("CreditSuisseDelegateImpl")
public class CreditSuisseDelegateImpl implements CreditSuisseDelegate{

	@Autowired
	CreditSuisseService creditSuisseService;
}
