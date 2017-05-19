package de.jhit.opendiabetes.vault.importer.crawler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

public class OpenDiabetesLoginDeatils {
	private Map<String, String> loginCookies = new HashMap<String, String>();
	public static String UserAgent = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36";

	public Map<String, String> getcookies() {
		return this.loginCookies;

	}
	
	public Boolean CheckConnection(String username, String password) {
		// TODO Auto-generated method stub
		try {

			Connection.Response res = Jsoup.connect("https://carelink.minimed.eu/patient/j_security_check")
					.data("j_username", username).data("j_password", password).method(Connection.Method.POST).execute();
			loginCookies = res.cookies();
			System.out.println("correct Username and Password");
			return true;
		} catch (Exception e) {
			System.out.println("Incorrect Username or Password");
			return false;
		}

	}
}
