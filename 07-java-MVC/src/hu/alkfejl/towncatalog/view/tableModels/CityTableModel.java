package hu.alkfejl.towncatalog.view.tableModels;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.table.AbstractTableModel;

import hu.alkfejl.towncatalog.model.bean.City;
import hu.alkfejl.towncatalog.view.Labels;

/**
 * Egyedi table modelt az AbstractTableModelbõl tudunk származtatni.
 */
public class CityTableModel extends AbstractTableModel {

    private static final long serialVersionUID = 2093252961204461838L;

    // Az egyes oszlop fejlécek nevei
    private String[] columnNames = new String[] {
            Labels.city_name, Labels.city_created};

    // A vásárlókat tartalmazó objektum (a DAO-tól kapott átstruktúrálva)
    Map<Integer, City> cityMap = new HashMap<Integer, City>();

    public CityTableModel(List<City> city) {
        super();

        prepareDataStructure(city);
    }

    /**
     * A controlleren keresztül megkapott customers struktúráját (lista) a view
     * szükségleteinek megfelelõen átalakítja.
     * <p>
     * Egy map-be tesszük õket, kulcsként a sorszámot használjuk, így a
     * lenti {@ #getValueAt(int,int)} függvényt tudjuk implementálni.
     * </p>
     *
     * @param customers A {@link Customers}-ek listája.
     */
    private void prepareDataStructure(List<City> citys) {
        int row = 0;

        for (City city : citys) {
            this.cityMap.put(row, city);
            row++;
        }
    }

    /* A table model megvalósításához felül kell írni néhány fontos metódust!
     */

    /**
     * Megadja, hogy hány oszlopa van a táblázatnak.
     */
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    /**
     * Megadja, hogy hány sora van a táblázatnak.
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
     * Megadja, hogy adott sorban és oszlopban milyen érték szerepel.
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
    // Így viszont, ami boolean, az checkboxkent jelenik meg.
    @Override
    public Class<?> getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    // Nagyon fontos! Eredetileg egy JTable minden mezõje szerkeszthetõ
    // Jelenleg ezt letiltjuk, a szerkesztéshez a kontrolleren keresztül az
    // adatbázis kommunikációt is implementalni kell!
    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

}

