package hu.alkfejl.towncatalog.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import hu.alkfejl.towncatalog.view.dialogs.AddCityDialog;
import hu.alkfejl.towncatalog.view.workers.ListCitySwingWorker;

/**
 * A {@link TownCatalogMenuBar} osztály reprezentálja a menüt.
 * Azért teheti ezt meg, mert a {@link JMenuBar} osztályból származik.
 * Így a menüre a this-szel hivatkozhatunk.
 */
public class TownCatalogMenuBar extends JMenuBar implements ActionListener {

	private TownCatalogGui townCatalogGuiGUI;

    public TownCatalogMenuBar(TownCatalogGui townCatalogGui) {
        super();
        this.townCatalogGuiGUI = townCatalogGui;

        createMenuPoint(Labels.city, Labels.add_city, Labels.list_city);

    }

    private void createMenuPoint(String name, String... subnames) {
        JMenu menu = new JMenu(name);

        this.add(menu);

        for (String subname : subnames) {
            JMenuItem menuItem = new JMenuItem(subname);

            menu.add(menuItem);
            menuItem.addActionListener(this);
        }
    }
    
    @Override
	public void actionPerformed(ActionEvent arg0) {
		String actionCommand = arg0.getActionCommand();
		if (actionCommand.equals(Labels.add_city)) {
            // Ha az új ügyfél felvételét választották, akkor egy
            // AddCustomerDialog-ot indítunk
            new AddCityDialog(townCatalogGuiGUI, true);
        } else if (actionCommand.equals(Labels.list_city)) {

            ListCitySwingWorker listCitySwingWorker = new ListCitySwingWorker(townCatalogGuiGUI);

            listCitySwingWorker.execute();
        }
		
	}
    
}
