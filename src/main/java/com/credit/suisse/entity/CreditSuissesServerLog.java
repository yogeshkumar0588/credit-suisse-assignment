package com.credit.suisse.entity;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class CreditSuissesServerLog {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int logId;
	
	@Column
	private String id;
	
	@Column
	private String state;
	
	@Column
	private long timestamp;
	
	@Column
	private String host;
	
	@Column
	private String type;
	
	@Column
	private LocalDateTime logInputDate;
	
	@Column
	private boolean alertFlag;
	
	@Column
	private int processedTime;
	    
	public CreditSuissesServerLog(String id, String state, long timestamp, String host, String type) {
        this.id = id;
        this.state = state;
        this.timestamp = timestamp;
        this.host = host;
        this.type = type;
        this.logInputDate = convertMillisecondsIntoDate(this.getTimestamp());
    }

    public void setAlertFlag(boolean alertFlag) {
        this.alertFlag = alertFlag;
    }

    public LocalDateTime convertMillisecondsIntoDate(long timeInMilliseconds) {
        Instant inst  = Instant.ofEpochMilli(timeInMilliseconds);
        return LocalDateTime.ofInstant(inst,ZoneId.of("CET"));
    }
    @Override
    public String toString() {
        return "PROCESS_ID: " + this.id + "\nSTATE: " + this.state + "\nTIME_STAMP: " + this.timestamp
                + "\nHOST: " + this.host + "\nTYPE: " + this.type + "\nEVENT_CREATION: " + this.getLogInputDate()
                + "\nALERT_FLAG: " + this.isAlertFlag() + "\nPROCESS_TIME: " + this.processedTime +" ms";
    }

    public LocalDateTime getLogInputDate() {
        return logInputDate;
    }

    public boolean isAlertFlag() {
        return alertFlag;
    }

    public String getId() {
        return id;
    }

    public String getState() {
        return state;
    }

    public int getProcessedTime() {
        return processedTime;
    }

    public void setProcessedTime(int processedTime) {
        this.processedTime = processedTime;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getHost() {
        return host;
    }

    public String getType() {
        return type;
    }
}
