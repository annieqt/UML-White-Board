package util.main;

import view.client.ClientGuiManager;


public class ClientEnter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Mac OS X settings
	    if (System.getProperty("mrj.version") != null) {
	      System.setProperty("apple.laf.useScreenMenuBar", "true");
	      System.setProperty("com.apple.eawt.CocoaComponent.CompatibilityMode",
	          "false");
	      System.setProperty("com.apple.mrj.application.apple.menu.about.name",
	          "TinyUML");
	    }

        ClientGuiManager.getInstance().toSignView();
	}

}
