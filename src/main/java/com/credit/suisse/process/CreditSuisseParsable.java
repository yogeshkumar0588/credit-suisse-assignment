package com.credit.suisse.process;

import java.util.List;

import com.credit.suisse.entity.CreditSuissesServerLog;

public interface CreditSuisseParsable <File> {
    List<CreditSuissesServerLog> parseFile(File f);
}
