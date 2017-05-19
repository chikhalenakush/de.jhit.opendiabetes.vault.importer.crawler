package de.jhit.opendiabetes.vault.importer.crawler;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

public class OpenDiabetesDatesCSVData {
	public static String UserAgent = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36";

	public Boolean GenerateDocument(Map<String, String> loginCookies, String startDate, String endDate) throws IOException {
		try {
			Connection.Response ReportDocument = Jsoup
					.connect("https://carelink.minimed.eu/patient/main/selectCSV.do?t=11?t=11?t=11?t=11").timeout(60000)
					.ignoreContentType(false).userAgent(UserAgent).cookies(loginCookies)
					.header("Content-Type", "text/csv; charset=UTF-8")
					.header("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
					.header("Content-length", "101").data("report", "11").data("listSeparator", ",")
					// .data("customerID","50577452") // customer Id can be
					// optional.
					.data("datePicker2", startDate) // start date
					.data("datePicker1", endDate) // End date
					.header("X-Requested-With", "XMLHttpRequest").method(Connection.Method.GET).execute();

			String userHome = System.getProperty("user.home");
			String outputFolder = userHome + File.separator + "careLink-Export";

			System.out.println("File will be saved to location " + userHome + " with name: " + "\"careLink-Export"
					+ (new Date().getTime()) + ".csv\"");
			PrintWriter pw1 = new PrintWriter(new File(outputFolder + (new Date().getTime()) + ".csv"));
			pw1.write(ReportDocument.body());
			pw1.close();
			System.out.println(" Export Sucessfull!");

			return true;

		} catch (IOException e) {
			System.out.println("There is an issue Downloading File. Please try again after some time!!");

			return false;
		}
	}
}
