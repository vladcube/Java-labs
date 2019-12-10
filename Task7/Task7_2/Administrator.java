import java.io.Serializable;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;


public class Administrator implements Serializable {
    private static final long serialVersionUID = 1L;
    private static ArrayList<Client> blackList = new ArrayList<Client>();
    protected Catalogue catalogue = new Catalogue();
    
    
    public final Date creation_date = new Date();
    
    public String getCreationDate() {
    	DateFormat dateFormatter = DateFormat.getDateTimeInstance(
    			DateFormat.DEFAULT, DateFormat.DEFAULT, AppLocale.get());
    String dateOut = dateFormatter.format(creation_date);
    return dateOut;
    }
    
    

    
    @SuppressWarnings("static-access")
	public Administrator(Catalogue catalogue, ArrayList<Client> blackList) {
        this.catalogue = catalogue;
        this.blackList = blackList;
    }
    
    public Administrator(String fileName) {
        this.catalogue = new Catalogue(fileName);
    }
    
    public ArrayList<Client> getBlackList() {
        return blackList;
    }

    public void addToBlackList(Client client) {
        if (!isClientInBlackList(client))
            Administrator.blackList.add(client);
    }

    public static boolean isClientInBlackList(Client client) {
        return (blackList.indexOf(client) != -1);
    }

    public void removeFromBlackList(Client client) {
        Administrator.blackList.remove(client);
    }
    

    public Catalogue getCatalogue() {
        return catalogue;
    }

    public void setCatalogue(Catalogue catalogue) {
        this.catalogue = catalogue;
    }
    
    public void ServeClient(Client client) {
    	if (client.Cost() <= client.getMoney()) {
    		client.setPaid(true);
    	}
    	else {
    		this.addToBlackList(client);
    		client.setPaid(false);
    	}
    }

    public void showBlackList() {
        System.out.println("++++++++++++" + AppLocale.getString(AppLocale.blackList) + "+++++++++++");
        for (Client client : this.getBlackList()) {
            System.out.println(client.toString());
        }
        System.out.println("++++++++++++++++++++++++++++++++++++++");

    }

    
    public String toString() {
        return "******" + AppLocale.getString(AppLocale.administrator) + "******\n" +
                AppLocale.getString(AppLocale.catalogue) + ": " + catalogue.toString() + '\n' +
                getCreationDate() + '\n' +
                "\n**************************\n";
    }

}