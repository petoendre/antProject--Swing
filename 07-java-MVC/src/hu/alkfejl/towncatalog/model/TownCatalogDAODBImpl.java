package hu.alkfejl.towncatalog.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import hu.alkfejl.towncatalog.model.bean.City;


public class TownCatalogDAODBImpl implements TownCatalogDAO {
	
	List<City> citys = new ArrayList<City>();
	
	private static final String DATABASE_FILE = System.getenv("BookShopDB");
	
	private static final String SQL_SELECT_ALL_CITY = "SELECT * FROM Town";
	
	private static final String SQL_INSERT_CITY =
	        "INSERT INTO Town " +
	        "(VarosNev, AlapitasiEvszam) " +
	        "VALUES (?, ?)";

	
	public TownCatalogDAODBImpl() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.out.println("Failed to load SQLite JDBC driver.");
            e.printStackTrace();
        }
    }
	
	@Override
    public boolean addCustomer(City city) {
        boolean rvSucceeded = false;

        // Adatb�zis kapcsolatot reprezent�l� objektum
        Connection conn = null;
        PreparedStatement pst = null;

        try {
            // Az adatb�zis kapcsolatunkat a DriverManager seg�ts�g�vel hozzuk l�tre
            // Megadjuk hogy a JDBC milyen driveren kereszt�l milyen f�jlt keressen
            conn = DriverManager.getConnection("jdbc:sqlite:" + DATABASE_FILE);

            // �j v�s�rl� felv�tele eset�n egy PreparedStatement objektumot
            // k�r�nk a kapcsolat objektumt�l
            // Ez egy param�terezhet� SQL utasit�st v�r, a param�terek ?-k�nt
            // jelennek meg
            pst = conn.prepareStatement(SQL_INSERT_CITY);

            // Az egyes paramet�reket sorban kell megadni, poz�ci� alapj�n, ami
            // 1-t�l indul
            // C�lszer� egy indexet inkrement�lni, mivel ha az egyik param�ter
            // kiesik, akkor nem kell az ut�na k�vetkez�eket �jra sz�mozni...
            int index = 1;
            pst.setString(index++, city.getName());
            pst.setInt(index++, city.getAge());


            // Az ExecuteUpdate paranccsal v�grehajtjuk az utas�t�st
            // Az executeUpdate visszaadja, hogy h�ny sort �rintett az SQL ha 
            // DML-t hajtunk v�gre (DDL eset�n 0-t ad vissza)
            int rowsAffected = pst.executeUpdate();

            // csak akkor sikeres, ha val�ban volt �rintett sor
            if (rowsAffected == 1) {
                rvSucceeded = true;
            }
        } catch (SQLException e) {
            System.out.println("Failed to execute adding customer.");
            e.printStackTrace();
        } finally {
        	try {
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException e) {
                System.out.println("Failed to close statement when adding customer.");
                e.printStackTrace();
            }

            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println("Failed to close connection when adding customer.");
                e.printStackTrace();
            }
        }

        return rvSucceeded;
    }
	
	@Override
	public List<City> getCitys(){
        Connection conn = null;
        Statement st = null;
        citys.clear();

        try {
        	
        	conn = DriverManager.getConnection("jdbc:sqlite:" + DATABASE_FILE);
        	
        	st = conn.createStatement();
           
            ResultSet rs = st.executeQuery(SQL_SELECT_ALL_CITY);

            citys = getCitysFromResultSet(rs);
        } catch (SQLException e) {
            System.out.println("Failed to execute listing customers.");
            e.printStackTrace();
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException e) {
                System.out.println("Failed to close statement when listing customers.");
                e.printStackTrace();
            }

            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println("Failed to close connection when listing customers.");
                e.printStackTrace();
            }
        }

        return citys;
    }
	
	private List<City> getCitysFromResultSet(ResultSet resultSet) throws SQLException {
        List<City> rvCustomersList = new ArrayList<City>();

        // Bej�rjuk a ResultSet-et (ami a v�s�rl�kat tartalmazza)
        while (resultSet.next()) {
            // �j v�s�rl�t hozunk l�tre
        	City city = new City();

            // A v�s�rl� id-j�t a ResultSet aktu�lis sor�b�l olvassuk (id column)
        	city.setId(resultSet.getInt("id"));

            // �s �gy tov�bb...
        	city.setCityName(resultSet.getString("VarosNev"));
        	city.setCreatedAt(resultSet.getInt("AlapitasiEvszam"));

            rvCustomersList.add(city);
        }

        return rvCustomersList;
    }
}
