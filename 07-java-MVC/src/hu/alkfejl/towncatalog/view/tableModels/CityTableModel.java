package hu.alkfejl.towncatalog.view.tableModels;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.table.AbstractTableModel;

import hu.alkfejl.towncatalog.model.bean.City;
import hu.alkfejl.towncatalog.view.Labels;

/**
 * Egyedi table modelt az AbstractTableModelb�l tudunk sz�rmaztatni.
 */
public class CityTableModel extends AbstractTableModel {

    private static final long serialVersionUID = 2093252961204461838L;

    // Az egyes oszlop fejl�cek nevei
    private String[] columnNames = new String[] {
            Labels.city_name, Labels.city_created};

    // A v�s�rl�kat tartalmaz� objektum (a DAO-t�l kapott �tstrukt�r�lva)
    Map<Integer, City> cityMap = new HashMap<Integer, City>();

    public CityTableModel(List<City> city) {
        super();

        prepareDataStructure(city);
    }

    /**
     * A controlleren kereszt�l megkapott customers strukt�r�j�t (lista) a view
     * sz�ks�gleteinek megfelel�en �talak�tja.
     * <p>
     * Egy map-be tessz�k �ket, kulcsk�nt a sorsz�mot haszn�ljuk, �gy a
     * lenti {@ #getValueAt(int,int)} f�ggv�nyt tudjuk implement�lni.
     * </p>
     *
     * @param customers A {@link Customers}-ek list�ja.
     */
    private void prepareDataStructure(List<City> citys) {
        int row = 0;

        for (City city : citys) {
            this.cityMap.put(row, city);
            row++;
        }
    }

    /* A table model megval�s�t�s�hoz fel�l kell �rni n�h�ny fontos met�dust!
     */

    /**
     * Megadja, hogy h�ny oszlopa van a t�bl�zatnak.
     */
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    /**
     * Megadja, hogy h�ny sora van a t�bl�zatnak.
     */
    @Override
    public int getRowCount() {
        return cityMap.entrySet().size();
    }

    /**
     * Megadja, hogy adott oszlopnak mi a neve.
     */
    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    /**
     * Megadja, hogy adott sorban �s oszlopban milyen �rt�k szerepel.
     */
    @Override
    public Object getValueAt(int row, int col) {
    	City city = cityMap.get(row);
        String askedColumnName = columnNames[col];

        if (askedColumnName.equals(Labels.city_name)) {
            return city.getName();
        } else if (askedColumnName.equals(Labels.city_created)) {
            return city.getAge();
        } 
        return Labels.unknown;
    }

    // Nagyon fontos! Eredetileg egy JTable-ben minden String
    // �gy viszont, ami boolean, az checkboxkent jelenik meg.
    @Override
    public Class<?> getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    // Nagyon fontos! Eredetileg egy JTable minden mez�je szerkeszthet�
    // Jelenleg ezt letiltjuk, a szerkeszt�shez a kontrolleren kereszt�l az
    // adatb�zis kommunik�ci�t is implementalni kell!
    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

}

