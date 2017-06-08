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

        // A dial�gus c�m�nek be�ll�t�sa
        this.setTitle(Labels.add_city);

        // A be�ll�t�sokat tartalmaz� panel gy�rt�sa
        JPanel settingPanel = createSettingPanel();

        // A gombokat tartalmaz� panel gy�rt�sa
        JPanel buttonPanel = createButtonPanel();

        // Az el�z� k�t panelt egy panelre rakjuk
        JPanel dialogPanel = createDialogPanel(settingPanel, buttonPanel); 

        // A dialogPanelt r�rakjuk a dial�gusra
        getContentPane().add(dialogPanel);

        // A dial�gus megfelel� m�ret�nek be�ll�t�sa (a tartalmazott elemek alapj�n)
        pack();

        // A dial�gust a BookShopGUI-hoz k�pest rajzolja ki
        setLocationRelativeTo(townCatalogGuiGUI.getWindow());

        // Dialogus megjelen�t�se
        setVisible(true);
    }
	
	private JPanel createSettingPanel() {
        JPanel settingPanel = new JPanel();

        // A panel elrendez�se m�trix, 2 sor �s 2 oszlop, a cell�k egyforma m�ret�ek
        settingPanel.setLayout(new GridLayout(2,2));

        // Az els� sorban egy n�v c�mke �s egy sz�vegmez� lesz
        settingPanel.add(new JLabel(Labels.city_name));
        settingPanel.add(this.cityNameTextfield);

        // A m�sodik sorban egy kor c�mke �s egy spinner lesz
        settingPanel.add(new JLabel(Labels.city_created));
        settingPanel.add(this.createdAtSpinner);


        return settingPanel;
    }
	
	 private JPanel createButtonPanel() {
	        JPanel buttonPanel = new JPanel();

	        // A panel elrendez�se folytonos, k�z�pre igaz�tva
	        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

	        // Hozz�adjuk az ok gombot, �s figyel�nk r�
	        buttonPanel.add(okButton);
	        okButton.addActionListener(this);

	        // Hozz�adjuk a cancel gombot, �s figyel�nk r�
	        buttonPanel.add(cancelButton);
	        cancelButton.addActionListener(this);

	        return buttonPanel;
	    }
	 
	 private JPanel createDialogPanel(JPanel settingPanel, JPanel buttonPanel) {
	        JPanel dialogPanel = new JPanel();

	        // A panel elrendez�se BorderLayout
	        dialogPanel.setLayout(new BorderLayout());

	        // K�z�pen lesz a settingPanel
	        dialogPanel.add(settingPanel, BorderLayout.CENTER);

	        // Alul pedig a gombok
	        dialogPanel.add(buttonPanel, BorderLayout.SOUTH);

	        return dialogPanel;
	    }
	 
	 @Override
		public void actionPerformed(ActionEvent arg0) {
		 if (okButton == arg0.getSource()) {
			 if (cityNameTextfield.getText().isEmpty()) {
	                // Ha nem adtak meg nevet, akkor egy hiba�zenetet �runk ki egy
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
	                // Ha az addCustomer false-t ad vissza akkor egy hiba�zenetet
	                // �runk ki egy error dialogra(JOptionPane.ERROR_MESSAGE)
	                JOptionPane.showMessageDialog(
	                		townCatalogGuiGUI.getWindow(),
	                        Labels.city_exists,
	                        Labels.error,
	                        JOptionPane.ERROR_MESSAGE);
	            } else {
	                // Ha az addCustomer true-t ad vissza akkor bez�rjuk a dial�gust
	                setVisible(false);
	            }
			 
			 
		 } else if (cancelButton == arg0.getSource()) {
			 setVisible(false);
		}
			
		}

}
