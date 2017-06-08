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

        // Adatbázis kapcsolatot reprezentáló objektum
        Connection conn = null;
        PreparedStatement pst = null;

        try {
            // Az adatbázis kapcsolatunkat a DriverManager segítségével hozzuk létre
            // Megadjuk hogy a JDBC milyen driveren keresztül milyen fájlt keressen
            conn = DriverManager.getConnection("jdbc:sqlite:" + DATABASE_FILE);

            // Új vásárló felvétele esetén egy PreparedStatement objektumot
            // kérünk a kapcsolat objektumtól
            // Ez egy paraméterezhetõ SQL utasitást vár, a paraméterek ?-ként
            // jelennek meg
            pst = conn.prepareStatement(SQL_INSERT_CITY);

            // Az egyes parametéreket sorban kell megadni, pozíció alapján, ami
            // 1-tõl indul
            // Célszerû egy indexet inkrementálni, mivel ha az egyik paraméter
            // kiesik, akkor nem kell az utána következõeket újra számozni...
            int index = 1;
            pst.setString(index++, city.getName());
            pst.setInt(index++, city.getAge());


            // Az ExecuteUpdate paranccsal végrehajtjuk az utasítást
            // Az executeUpdate visszaadja, hogy hány sort érintett az SQL ha 
            // DML-t hajtunk végre (DDL esetén 0-t ad vissza)
            int rowsAffected = pst.executeUpdate();

            // csak akkor sikeres, ha valóban volt érintett sor
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

        // Bejárjuk a ResultSet-et (ami a vásárlókat tartalmazza)
        while (resultSet.next()) {
            // Új vásárlót hozunk létre
        	City city = new City();

            // A vásárló id-jét a ResultSet aktuális sorából olvassuk (id column)
        	city.setId(resultSet.getInt("id"));

            // És így tovább...
        	city.setCityName(resultSet.getString("VarosNev"));
        	city.setCreatedAt(resultSet.getInt("AlapitasiEvszam"));

            rvCustomersList.add(city);
        }

        return rvCustomersList;
    }
}
