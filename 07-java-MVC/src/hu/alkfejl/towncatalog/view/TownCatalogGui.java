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
        // A GUI kirajzol�s�ra �s az azon t�rt�n� esem�nyek kezel�s�re a Java egy k�l�n sz�lat haszn�l.
        // Ezen a sz�lon ind�tjuk el a createAndShowGUI() met�dust.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
	
	private void createAndShowGUI() {
        // El��ll�tjuk az alkalmaz�s c�msor�t
        //   - ha meg volt adva usern�v, akkor azt �rjuk bele,
        //   - ha nem, akkor "ismeretlen"-t t�ntet�nk fel
		String title = String.format(
                Labels.main_window_title_format,
                hu.alkfejl.towncatalog.Main.username == null ? Labels.main_window_title_unknown_user : hu.alkfejl.towncatalog.Main.username);

        // A JFrame egy magas szint� kont�ner, egy ablak c�mmel �s kerettel.
        window = new JFrame(title);

        // Ha bez�rjuk az ablakot, akkor alap�rtelmez�sben azt csak elrejtjuk.
        // Ezt a viselked�st m�dos�tjuk arra, hogy az ablak t�nylegesen z�r�djon be.
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        TownCatalogMenuBar catalogMenuBar = new TownCatalogMenuBar(this);
        
     // Amit r�rakunk a Book Shop ablakunkra
        window.setJMenuBar(catalogMenuBar);

        // Az ablaknak be�ll�tjuk a m�ret�t
        window.setSize(800,600);

        // K�szen vagyunk, megjelen�thetj�k az ablakot
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
