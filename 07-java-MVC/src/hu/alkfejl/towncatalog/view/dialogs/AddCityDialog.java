package hu.alkfejl.towncatalog.view.dialogs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import hu.alkfejl.towncatalog.model.bean.City;
import hu.alkfejl.towncatalog.view.Labels;
import hu.alkfejl.towncatalog.view.TownCatalogGui;

public class AddCityDialog extends JDialog implements ActionListener {
	
	private TownCatalogGui townCatalogGuiGUI;
	
	private JTextField cityNameTextfield = new JTextField(10);
    private JSpinner createdAtSpinner = new JSpinner();
    private JButton okButton = new JButton(Labels.ok);
    private JButton cancelButton = new JButton(Labels.cancel);

	public AddCityDialog(TownCatalogGui townCatalogGuiGUI, boolean modal) {
		super(townCatalogGuiGUI.getWindow(), modal);
        this.townCatalogGuiGUI = townCatalogGuiGUI;

        // A dialógus címének beállítása
        this.setTitle(Labels.add_city);

        // A beállításokat tartalmazó panel gyártása
        JPanel settingPanel = createSettingPanel();

        // A gombokat tartalmazó panel gyártása
        JPanel buttonPanel = createButtonPanel();

        // Az elõzõ két panelt egy panelre rakjuk
        JPanel dialogPanel = createDialogPanel(settingPanel, buttonPanel); 

        // A dialogPanelt rárakjuk a dialógusra
        getContentPane().add(dialogPanel);

        // A dialógus megfelelõ méretének beállítása (a tartalmazott elemek alapján)
        pack();

        // A dialógust a BookShopGUI-hoz képest rajzolja ki
        setLocationRelativeTo(townCatalogGuiGUI.getWindow());

        // Dialogus megjelenítése
        setVisible(true);
    }
	
	private JPanel createSettingPanel() {
        JPanel settingPanel = new JPanel();

        // A panel elrendezése mátrix, 2 sor és 2 oszlop, a cellák egyforma méretûek
        settingPanel.setLayout(new GridLayout(2,2));

        // Az elsõ sorban egy név címke és egy szövegmezõ lesz
        settingPanel.add(new JLabel(Labels.city_name));
        settingPanel.add(this.cityNameTextfield);

        // A második sorban egy kor címke és egy spinner lesz
        settingPanel.add(new JLabel(Labels.city_created));
        settingPanel.add(this.createdAtSpinner);


        return settingPanel;
    }
	
	 private JPanel createButtonPanel() {
	        JPanel buttonPanel = new JPanel();

	        // A panel elrendezése folytonos, középre igazítva
	        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

	        // Hozzáadjuk az ok gombot, és figyelünk rá
	        buttonPanel.add(okButton);
	        okButton.addActionListener(this);

	        // Hozzáadjuk a cancel gombot, és figyelünk rá
	        buttonPanel.add(cancelButton);
	        cancelButton.addActionListener(this);

	        return buttonPanel;
	    }
	 
	 private JPanel createDialogPanel(JPanel settingPanel, JPanel buttonPanel) {
	        JPanel dialogPanel = new JPanel();

	        // A panel elrendezése BorderLayout
	        dialogPanel.setLayout(new BorderLayout());

	        // Középen lesz a settingPanel
	        dialogPanel.add(settingPanel, BorderLayout.CENTER);

	        // Alul pedig a gombok
	        dialogPanel.add(buttonPanel, BorderLayout.SOUTH);

	        return dialogPanel;
	    }
	 
	 @Override
		public void actionPerformed(ActionEvent arg0) {
		 if (okButton == arg0.getSource()) {
			 if (cityNameTextfield.getText().isEmpty()) {
	                // Ha nem adtak meg nevet, akkor egy hibaüzenetet írunk ki egy
	                // error dialogra (JOptionPane.ERROR_MESSAGE)
	                JOptionPane.showMessageDialog(
	                		townCatalogGuiGUI.getWindow(),
	                        Labels.city_name_is_required,
	                        Labels.error,
	                        JOptionPane.ERROR_MESSAGE);
	                return;
	            }
			 
			 City city = new City();
			 city.setCityName(cityNameTextfield.getText());
			 city.setCreatedAt((Integer)createdAtSpinner.getValue());
			 
			 if (!townCatalogGuiGUI.getController().addCity(city)) {
	                // Ha az addCustomer false-t ad vissza akkor egy hibaüzenetet
	                // írunk ki egy error dialogra(JOptionPane.ERROR_MESSAGE)
	                JOptionPane.showMessageDialog(
	                		townCatalogGuiGUI.getWindow(),
	                        Labels.city_exists,
	                        Labels.error,
	                        JOptionPane.ERROR_MESSAGE);
	            } else {
	                // Ha az addCustomer true-t ad vissza akkor bezárjuk a dialógust
	                setVisible(false);
	            }
			 
			 
		 } else if (cancelButton == arg0.getSource()) {
			 setVisible(false);
		}
			
		}

}
