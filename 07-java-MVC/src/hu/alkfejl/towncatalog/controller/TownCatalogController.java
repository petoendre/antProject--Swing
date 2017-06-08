package hu.alkfejl.towncatalog.controller;

import java.util.List;

import hu.alkfejl.towncatalog.model.TownCatalogDAO;
import hu.alkfejl.towncatalog.model.TownCatalogDAODBImpl;
import hu.alkfejl.towncatalog.model.bean.City;
import hu.alkfejl.towncatalog.view.TownCatalogGui;

public class TownCatalogController {
	
	private TownCatalogDAO dao = new TownCatalogDAODBImpl();

	public void startDesktop() {
		TownCatalogGui gui = new TownCatalogGui(this);
		gui.startGUI();
	}
	
	public boolean addCity(City c) {
        return dao.addCustomer(c);
    }
	
	public List<City> getCitys() {
        return dao.getCitys();
    }

}
