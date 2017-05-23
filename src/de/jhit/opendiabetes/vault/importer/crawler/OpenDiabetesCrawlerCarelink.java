package de.jhit.opendiabetes.vault.importer.crawler;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import java.awt.AWTException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

public class OpenDiabetesCrawlerCarelink {
	public static String Username;
	public static String Password;
	public static String StartDate;
	public static String EndDate;
	public static Boolean IsLoginDetailsCorrect = false;
	public static Boolean IsCsvOrUpload = false;
	public static Boolean IsStartDateCorrect = false;
	public static Boolean IsEndDateCorrect = false;
	public static Boolean IsCSVDownloadSucessfull = false;

	private static Map<String, String> LoginCookies = new HashMap<String, String>();

	private static Scanner keyboard;

	public static void main(String[] args) throws ParseException, IOException, SecurityException, AWTException, InterruptedException {
		// TODO Auto-generated method stub
		// keyboard = new Scanner(System.in);

		keyboard = new Scanner(System.in);

		AskForLoginDetails();

		//CSVORUpload();

	}

	private static void CSVORUpload() throws ParseException, IOException, AWTException, InterruptedException {
		IsCsvOrUpload = DoYouWantToDownloadCSVOrUploadFiles();
		Boolean IsStarMagicComplete = false;
		OpenDiabetesSimulateMouse sm = new OpenDiabetesSimulateMouse();
		if (IsCsvOrUpload) {
			// Logic for CSV File
			System.out.println("Note: Date should be in Format of DD/MM/YYYY  Example: 13/03/2017.");
			System.out.println(
					"Start and end date should not be less than 01/01/1998 and should not be greater than Today's date.");
			System.out.println("End date should not be less than Start date.");

			AskForStartDate();
			AskForEndDate();
			OpenDiabetesDatesCSVData CsvData = new OpenDiabetesDatesCSVData();

			IsCSVDownloadSucessfull = CsvData.GenerateDocument(LoginCookies, StartDate, EndDate);
			CSVORUpload();
		} else {
			// Logic for Upload File
			String IEDriver = GetIEDriver();
		//	OpenDiabetesSimulateMouse sm = new OpenDiabetesSimulateMouse();
			if(AskForAutoAckOrBGStick())
			{
				// AutoAck logic
				
				
				if(IEDriver!=null){
					
					
					
					IsStarMagicComplete =	sm.startmagic(IEDriver, "1234657980",/*BG.selectedProperty().getValue() */ true, /*Stick.selectedProperty().getValue()*/ false, Username, Password,
							/*BG.selectedProperty().getValue() */ true);
					CSVORUpload();
				}
				else
				{
					AskForAutoAckOrBGStick();
					
				}
			}
			else
			{
				//BG or Stick logic
				
			String sn = 	AskForSNNumber();
				if(AskForBGOrStick())
				{
					//BG
					IsStarMagicComplete =	sm.startmagic(IEDriver, sn,/*BG.selectedProperty().getValue() */ true, /*Stick.selectedProperty().getValue()*/ false, Username, Password,
							/*BG.selectedProperty().getValue() */ true);
					CSVORUpload();
				}
				else
				{
					//Stick
					IsStarMagicComplete =	sm.startmagic(IEDriver, sn,/*BG.selectedProperty().getValue() */ false, /*Stick.selectedProperty().getValue()*/ true, Username, Password,
							/*BG.selectedProperty().getValue() */ true);
					CSVORUpload();
					
				}
				
				
			}
		}
	}

	private static String AskForSNNumber() {
		// TODO Auto-generated method stub
		System.out.println("Please Enter 10 char SN Nummer");
		
		
		keyboard = new Scanner(System.in);
		String SN = keyboard.nextLine();
		return SN;
	}

	private static Boolean AskForBGOrStick() {
		// TODO Auto-generated method stub
		try {
			System.out.println("Please Type true for BG OR false for STICK");
			keyboard = new Scanner(System.in);
			Boolean TrueorFalse = keyboard.nextBoolean();
			return TrueorFalse;
		} catch (Exception e) {
			System.out.println("Entered Value is not Boolean");
			return AskForBGOrStick();
		}
		
	}

	private static String GetIEDriver() {
		// TODO Auto-generated method stub

		
			System.out.println("Please Enter complete Path for Selinium IE WebDriver");
			//String path = "achikhale\Downloads\IEDriverServer_Win32_3.4.0\IEDriverServer.exe";
			//System.out.println("Exampe for complete Path : C:\\Users\\)"/+ "achikhale\Downloads\IEDriverServer_Win32_3.4.0\IEDriverServer.exe");
			
			
			System.out.println("If you do not have driver, you can download it from http://www.seleniumhq.org/download/ ");
			
			keyboard = new Scanner(System.in);
			String IeDriverPath = keyboard.nextLine();
			return IeDriverPath;
		
	}

	private static Boolean  AskForAutoAckOrBGStick() {
		// TODO Auto-generated method stub
		
		try {
			System.out.println("Please Type true for Auto Ack OR false for BG/STICK");
			keyboard = new Scanner(System.in);
			Boolean TrueorFalse = keyboard.nextBoolean();
			return TrueorFalse;
		} catch (Exception e) {
			System.out.println("Entered Value is not Boolean");
			return AskForAutoAckOrBGStick();
		}
		
	}

	private static Boolean AskForEndDate() throws ParseException {
		// TODO Auto-generated method stub
		System.out.println("Please Enter End Date");
		EndDate = keyboard.nextLine();

		OpenDiabetesDates DatesResult = new OpenDiabetesDates();
		IsEndDateCorrect = DatesResult.GetEndDate(EndDate, StartDate);
		if (IsEndDateCorrect) {
			return true;
		} else {

			return AskForEndDate();
		}

	}

	private static Boolean AskForStartDate() throws ParseException {
		// TODO Auto-generated method stub

		System.out.println("Please Enter Start Date");
		StartDate = keyboard.nextLine();

		OpenDiabetesDates DatesResult = new OpenDiabetesDates();
		IsStartDateCorrect = DatesResult.GetStratDate(StartDate);
		if (IsStartDateCorrect) {

			return true;
		} else {

			return AskForStartDate();
		}

	}

	private static Boolean DoYouWantToDownloadCSVOrUploadFiles() {
		// TODO Auto-generated method stub
		try {
			System.out.println("Do You Want To Download CSV File Or Upload Files to system");

			System.out.println("Please Enter Type true for CSV File \n Type false for Upload File");
			keyboard = new Scanner(System.in);
			Boolean TrueorFalse = keyboard.nextBoolean();
			
			return TrueorFalse;
		} catch (Exception e) {
			System.out.println("Entered Value is not Boolean");
			return DoYouWantToDownloadCSVOrUploadFiles();
		}
	}

	private static Boolean AskForLoginDetails() throws ParseException, IOException, SecurityException, AWTException, InterruptedException {
		System.out.println("Starting Unit Test case for Carelink Crawler");

		System.out.println("Please Enter user name");
		Username = keyboard.nextLine();

		System.out.println("Please Enter Password");
		Password = keyboard.nextLine();
		OpenDiabetesLoginDeatils LoginResult = new OpenDiabetesLoginDeatils();
		IsLoginDetailsCorrect = LoginResult.CheckConnection(Username, Password);
		if (IsLoginDetailsCorrect) {
			LoginCookies = LoginResult.getcookies();
			CSVORUpload();
			return true;
		} else {
			return AskForLoginDetails();
		}
	}

}
