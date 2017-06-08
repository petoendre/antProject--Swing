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

        // A worker sz�lon v�grehajtuk az adatok lek�rdez�s�t
        List<City> queriedCity = townCatalogGui.getController().getCitys();

        // Visszat�r�nk a lek�rdezett v�s�rl� list�val
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
