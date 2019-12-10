import java.util.*;


public class AppLocale {
	private static final String strMsg = "Msg";
	private static Locale loc = Locale.getDefault();
	private static ResourceBundle res = 
			ResourceBundle.getBundle(AppLocale.strMsg, AppLocale.loc);
	
	static Locale get() {
		return AppLocale.loc;
	}
	
	static void set( Locale loc ) {
		AppLocale.loc = loc;
		res = ResourceBundle.getBundle( AppLocale.strMsg, AppLocale.loc );
	}
	
	static ResourceBundle getBundle() {
		return AppLocale.res;
	}
	
	static String getString( String key ) {
		return AppLocale.res.getString(key);
	}
    // Resource keys:
    public static final String product = "product";
    public static final String productName = "productName";
    public static final String isPaid = "isPaid";
    public static final String client = "client";
    public static final String blackList = "blackList";
    public static final String enthernetShop = "enthernetShop";
    public static final String catalogue = "catalogue";
    public static final String name = "name";
    public static final String mobileNumber = "mobileNumber";
    public static final String administrator = "administrator";
    public static final String testingRead = "testingRead";
    public static final String clientCatalogue = "clientCatalogue";
    public static final String price = "price";
}