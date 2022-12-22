package com.credit.suisse.process;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import org.json.JSONException;
import org.json.JSONObject;

import com.credit.suisse.entity.CreditSuissesServerLog;

public class CreditSuisseLogsCollectionBuilder implements CreditSuisseParsable <File>{

	 private File file;
	 private static List <CreditSuissesServerLog> logList;
	 
	 @Override
	    public List <CreditSuissesServerLog> parseFile(File f) {

	        this.file = f;
	        Pattern pattern = Pattern.compile("}");
	        String hostName = "";
	        String typeOfMessage = "";
	        logList = new ArrayList <>();
	        try (Scanner input = new Scanner(file).useDelimiter(pattern)) {
	            while (input.hasNext()) {
	                String line = input.next().trim();
	                if (line != "") {
	                    JSONObject obj = new JSONObject(line + "}");
	                    String checkLog = obj.toString();
	                    if (!checkLog.contains("host")) {
	                        hostName = "N/A";
	                    } else {
	                        hostName = obj.getString("host");
	                    }
	                    if (!checkLog.contains("type")) {
	                        typeOfMessage = "N/A";
	                    } else {
	                        typeOfMessage = obj.getString("type");
	                    }
	                    logList.add(new CreditSuissesServerLog(obj.getString("id"), obj.getString("state"),
	                            obj.getLong("timestamp"), hostName, typeOfMessage));
	                } else {
	                    break;
	                }
	            }
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        }
	        StringBuilder sb = new StringBuilder();
	        sb.append("The .json file in the path: (")
	                .append(this.file)
	                .append(") has been successfully scanned.\n")
	                .append("All of the logs (")
	                .append(logList.size())
	                .append(") are collected in a list.");
	        System.out.println(sb);
	        return logList;
	    }

	    public void setTheAlertFlagsForDelayedEvents(List <CreditSuissesServerLog> list) {

	        long timeDifference = 0;
	        for (int i = 0; i < list.size() - 1; i++) {
	            for (int j = 1; j < list.size(); j++) {
	                if (list.get(i).getId().equals(list.get(j).getId())) {
	                    if (!list.get(i).getState().equals(list.get(j).getState())) {
	                        if (list.get(i).getState().toUpperCase().equals("STARTED")) {
	                            timeDifference = list.get(j).getTimestamp() - list.get(i).getTimestamp();
	                        } else {
	                            timeDifference = list.get(i).getTimestamp() - list.get(j).getTimestamp();
	                        }
	                    }
	                }
	                if (timeDifference > 4) {
	                    list.get(i).setAlertFlag(true);
	                    list.get(i).setProcessedTime((int) timeDifference);
	                    list.get(j - 1).setAlertFlag(true);
	                    list.get(j - 1).setProcessedTime((int) timeDifference);

	                    timeDifference = 0;
	                    break;
	                } else if (timeDifference <= 4) {
	                    if (list.get(j).getId().equals(list.get(0).getId())) {
	                        list.get(j).setProcessedTime(list.get(0).getProcessedTime());
	                    }
	                    if (list.get(i).getId().equals(list.get(j).getId())) {
	                        list.get(i).setAlertFlag(false);
	                        list.get(i).setProcessedTime((int) timeDifference);
	                        list.get(j).setAlertFlag(false);
	                        list.get(j).setProcessedTime((int) timeDifference);
	                    }
	                }
	            }
	        }
	    }

	    public boolean isHostNameAvailable(JSONObject log) {
	        try {
	            if (log.getString("host").equals("N/A")) {
	                return false;
	            } else {
	                return true;
	            }
	        } catch (JSONException e) {
	            e.printStackTrace();
	        }
	        return false;
	    }

	    public boolean isTypeOfMessageAvailable(JSONObject log) {
	        try {
	            if (!log.getString("type").equals("APPLICATION_LOG")) {
	                return false;
	            } else {
	                return true;
	            }
	        } catch (JSONException e) {
	            e.printStackTrace();
	        }
	        return false;
	    }

	    public void getTheEventList(List <CreditSuissesServerLog> logList, int range) {
	        int counter = 1;
	        for (CreditSuissesServerLog logElement : logList) {
	            if (counter == range) {
	                return;
	            } else {
	                System.out.println("Log: " + counter + "\n" + logElement.toString() + "\n");
	                counter++;
	            }
	        }
	    }

	    public static boolean isPreviewLogApproved() throws InterruptedException {

	        Thread.sleep(1000);
	        boolean isCorrectAnswer = false;
	        Scanner scan = new Scanner(System.in);
	        System.out.print("\nDo you want to preview the file.log ? (Y/N) ");
	        String userInput = scan.nextLine();
	        while (isCorrectAnswer == false) {
	            switch (userInput.toLowerCase()) {
	                case "y": {
	                    isCorrectAnswer = true;
	                    return true;
	                }
	                case "n": {
	                    isCorrectAnswer = true;
	                    return false;
	                }
	                default:
	                    System.out.println("Inputted incorrect value.\nPlease try again:");
	                    userInput = scan.nextLine();
	                    break;
	            }
	        }
	        scan.close();
	        return false;
	    }
}
