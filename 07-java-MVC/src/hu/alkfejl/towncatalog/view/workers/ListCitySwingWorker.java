package hu.alkfejl.towncatalog.view.workers;


import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingWorker;

import hu.alkfejl.towncatalog.model.bean.City;
import hu.alkfejl.towncatalog.view.TownCatalogGui;
import hu.alkfejl.towncatalog.view.tableModels.CityTableModel;

public class ListCitySwingWorker extends SwingWorker<List<City>, Void> {
	
	private TownCatalogGui townCatalogGui;

    public ListCitySwingWorker(TownCatalogGui townCatalogGui){
        this.townCatalogGui = townCatalogGui;
    }

	@Override
	protected List<City> doInBackground() throws Exception {
		System.out.println("SwingWorker doInBackground()...");

        // A worker szálon végrehajtuk az adatok lekérdezését
        List<City> queriedCity = townCatalogGui.getController().getCitys();

        // Visszatérünk a lekérdezett vásárló listával
        return queriedCity;
	}
	
	@Override
    protected void done() {
        System.out.println("SwingWorker done()...");
        List<City> queriedCitys = null;

        try {
            queriedCitys = get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        JTable table = new JTable(new CityTableModel(queriedCitys));

        JScrollPane container = new JScrollPane(table);

        townCatalogGui.setActualContent(container);
    }

}
