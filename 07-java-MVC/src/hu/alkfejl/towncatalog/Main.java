package hu.alkfejl.towncatalog;

import hu.alkfejl.towncatalog.controller.TownCatalogController;

public class Main {
	
	public static String username;
	
	public static void main(String[] args) {
		TownCatalogController controller = new TownCatalogController();
		controller.startDesktop();
	}

}
