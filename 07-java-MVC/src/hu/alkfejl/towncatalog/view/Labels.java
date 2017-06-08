package hu.alkfejl.towncatalog.view;

/**
 * Ez az osztály tartalmazza a programban elõforduló {@link String} konstansokat.
 * Csak itt fordulhat elõ {@link String} konstans!
 * <p>
 * Általánosságban elfogadott és szokás, hogy a kód nem tartalmaz olyan
 * {@link String} konstansokat, melyek a felhasználói felületen jelennek meg.
 * Ezeket mindig egy közös helyre gyûjtjük, így késõbb lokalizálható az alkalmazás
 * és eleve átláthatóbb így.
 * </p>
 */
public class Labels {

    // General labels
    public final static String city_name = "Város neve";
    public final static String ok = "OK";
    public final static String cancel = "Cancel";
    public final static String error = "Error";
    public final static String empty = "";
    public final static String unknown = "Unknown";

    // Main window
    public static final String main_window_title_format = "Város nyilvántartó (%1$s)";
    public static final String main_window_title_unknown_user = "ismeretlen";

    // Customer labels
    public final static String city = "Város";
    public final static String add_city = "Város hozzáadása";
    public final static String list_city = "Városok listázása";
    public final static String city_created = "Város alapítása";

    public final static String qualification = "Qualification";
    public final static String city_name_is_required = "City name is required!";
    public final static String city_exists = "Customer already exists!";

    
}
