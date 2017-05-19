package de.jhit.opendiabetes.vault.importer.crawler;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OpenDiabetesDates {

	public static String dateFormat = "dd/MM/yyyy";
	public Date FormatedStartDate;
	public Date FormatedEndDate;
	public Boolean GetStratDate(String startDate) throws ParseException {
		// TODO Auto-generated method stub
		SimpleDateFormat FormatTodayDate = new SimpleDateFormat(dateFormat); // to format today's date as DD/MM/YYYY
		FormatTodayDate.setLenient(false);
		Date Todaydate = new Date();
		String todayDate = FormatTodayDate.format(Todaydate);

		Date ValidStardDate = new SimpleDateFormat(dateFormat).parse("01/01/1998"); 
		// To validate Beginning of Date

		try {
			FormatedStartDate = new SimpleDateFormat(dateFormat).parse(startDate);
		} catch (Exception e) {
			System.out.println(
					"Start Date is not in correct format \n Date should be in Format of DD/MM/YYYY  Example: 13/03/2017");
			return false;
		}
		try {
			DateFormat ValidStartdate = new SimpleDateFormat(dateFormat); // To validate start date
			ValidStartdate.setLenient(false);
			ValidStartdate.parse(startDate);
			if (FormatedStartDate.before(ValidStardDate)
					|| FormatedStartDate.after(new SimpleDateFormat(dateFormat).parse(todayDate))) {
				System.out.println("You can only enter Start date between 01/01/1998 and Today's Date!!");
				return false;
			}
			return true;
		} catch (Exception e) {
			System.out.println("Start Date is not Valid");
			return false;

		}
		
	}
	public Boolean GetEndDate(String EndDate,String startDate) throws ParseException {

		Date Todaydate = new Date();

		SimpleDateFormat FormatTodayDate = new SimpleDateFormat(dateFormat);
		String todayDate = FormatTodayDate.format(Todaydate);
		
		Date ValidStardDate = new SimpleDateFormat(dateFormat).parse("01/01/1998");
		FormatedStartDate = new SimpleDateFormat(dateFormat).parse(startDate);

		try {
			FormatedEndDate = new SimpleDateFormat(dateFormat).parse(EndDate);
		} catch (Exception e) {
			System.out.println(
					"End Date is not in correct format \n Date should be in Format of DD/MM/YYYY  Example: 13/03/2017");
			return false;
		}
		try {
			DateFormat ValidEndDate = new SimpleDateFormat(dateFormat); // To validate End date
			ValidEndDate.setLenient(false);
			ValidEndDate.parse(EndDate);
			if (FormatedEndDate.after(new SimpleDateFormat(dateFormat).parse(todayDate))
					|| FormatedEndDate.before(ValidStardDate)) {
				System.out.println("You can only enter End date between 01/01/1998 and Today's Date!! ");
				return false;
			}
			if (FormatedEndDate.before(FormatedStartDate)) {
				System.out.println("End Date cannot be earlier than Start date");
				return false;
			}
			return true;
		} catch (Exception e) {
			System.out.println("End Date is not Valid");
			return false;
		}
	}

}
