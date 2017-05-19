package de.jhit.opendiabetes.vault.importer.crawler;

import com.sun.glass.events.KeyEvent;

public class LanguageClass {
	public int getReplacment(String lang) {
		// TODO Auto-generated method stub
		if(lang.contains("en")){
			return KeyEvent.VK_N;
		}
		if(lang.contains("de")){
			return KeyEvent.VK_W;
		}
		if(lang.contains("fr")){
			return KeyEvent.VK_W;
		}
		
		
		return 0;
		
	}

}
