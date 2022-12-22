package com.credit.suisse.delegate;

import java.io.FileNotFoundException;

public interface CreditSuisseDelegate {

	public String saveEvents()throws FileNotFoundException, InterruptedException;
}
