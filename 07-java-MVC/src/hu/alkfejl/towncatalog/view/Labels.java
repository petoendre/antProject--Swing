package hu.alkfejl.towncatalog.view;

/**
 * Ez az oszt�ly tartalmazza a programban el�fordul� {@link String} konstansokat.
 * Csak itt fordulhat el� {@link String} konstans!
 * <p>
 * �ltal�noss�gban elfogadott �s szok�s, hogy a k�d nem tartalmaz olyan
 * {@link String} konstansokat, melyek a felhaszn�l�i fel�leten jelennek meg.
 * Ezeket mindig egy k�z�s helyre gy�jtj�k, �gy k�s�bb lokaliz�lhat� az alkalmaz�s
 * �s eleve �tl�that�bb �gy.
 * </p>
 */
public class Labels {

    // General labels
    public final static String city_name = "V�ros neve";
    public final static String ok = "OK";
    public final static String cancel = "Cancel";
    public final static String error = "Error";
    public final static String empty = "";
    public final static String unknown = "Unknown";

    // Main window
    public static final String main_window_title_format = "V�ros nyilv�ntart� (%1$s)";
    public static final String main_window_title_unknown_user = "ismeretlen";

    // Customer labels
    public final static String city = "V�ros";
    public final static String add_city = "V�ros hozz�ad�sa";
    public final static String list_city = "V�rosok list�z�sa";
    public final static String city_created = "V�ros alap�t�sa";

    public final static String qualification = "Qualification";
    public final static String city_name_is_required = "City name is required!";
    public final static String city_exists = "Customer already exists!";

    
}
