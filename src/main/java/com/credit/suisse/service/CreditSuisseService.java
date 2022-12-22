package com.credit.suisse.service;

import java.io.FileNotFoundException;

public interface CreditSuisseService {

	public String saveEvents()throws FileNotFoundException, InterruptedException;
}
