package hu.alkfejl.towncatalog.view;

import java.awt.Container;

import javax.swing.JFrame;

import hu.alkfejl.towncatalog.controller.TownCatalogController;



public class TownCatalogGui {
	
	private JFrame window;
	private TownCatalogController controller;
	
	public TownCatalogGui(TownCatalogController controller) {
        this.controller = controller;
    }
	
	
	public void startGUI() {
        // A GUI kirajzolására és az azon történõ események kezelésére a Java egy külön szálat használ.
        // Ezen a szálon indítjuk el a createAndShowGUI() metódust.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
	
	private void createAndShowGUI() {
        // Elõállítjuk az alkalmazás címsorát
        //   - ha meg volt adva usernév, akkor azt írjuk bele,
        //   - ha nem, akkor "ismeretlen"-t tüntetünk fel
		String title = String.format(
                Labels.main_window_title_format,
                hu.alkfejl.towncatalog.Main.username == null ? Labels.main_window_title_unknown_user : hu.alkfejl.towncatalog.Main.username);

        // A JFrame egy magas szintû konténer, egy ablak címmel és kerettel.
        window = new JFrame(title);

        // Ha bezárjuk az ablakot, akkor alapértelmezésben azt csak elrejtjuk.
        // Ezt a viselkedést módosítjuk arra, hogy az ablak ténylegesen záródjon be.
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        TownCatalogMenuBar catalogMenuBar = new TownCatalogMenuBar(this);
        
     // Amit rárakunk a Book Shop ablakunkra
        window.setJMenuBar(catalogMenuBar);

        // Az ablaknak beállítjuk a méretét
        window.setSize(800,600);

        // Készen vagyunk, megjeleníthetjük az ablakot
        window.setVisible(true);
    }
	
	public JFrame getWindow() {
        return window;
    }
	
	public TownCatalogController getController() {
        return controller;
    }
	
	public void setActualContent(Container container) {
        window.setContentPane(container);
        window.setVisible(true);
    }

}
