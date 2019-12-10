import java.util.Locale;
import java.util.Scanner;

public class Test { 
	
    public static void main(String[] args) {
    	Locale by = new Locale("be", "BY");
        Locale ru = new Locale("ru", "RU");
        Locale en = new Locale("en", "GB");
        System.out.println("Enter your region(RU, BY, GB):");
        System.out.println(Locale.getDefault());
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        
        switch (s) {
            case "BY":
            	AppLocale.set(by);
                break;
            case "RU":
            	AppLocale.set(ru);
                break;
            case "GB":
            	AppLocale.set(en);
                break;
            default:
                System.out.println("Incorrect input! Locale is set as default(en_GB)!");
                AppLocale.set(en);
                break;
        }

        System.out.println("* * * * * * * * * * * * * * * * * * * * * *");
        System.out.println(AppLocale.getString(AppLocale.enthernetShop));
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * \n");


        try {
        	Catalogue myCatalogue = new Catalogue("src/catalogue.txt");
            myCatalogue.showCatalogue();

            //Connector.write
            Connector myConnector = new Connector("output.dat");
            myConnector.write(myCatalogue);

            //Connector.read
            //System.out.println("TESTING READ:");
            Catalogue readedCatalogue = myConnector.read();
            readedCatalogue.showCatalogue();

            //Testing
            Administrator admin = new Administrator("src/productCatalogue.txt");
            System.out.println(admin.toString());


            Client client = new Client("Vlad", 6015850, 1000, false);
            System.out.println(client.toString());
            for (Product i : myCatalogue.getProductCatalogue()) {
            	client.addProduct(i);
            }
            client.showClientProducts();


            System.out.println(admin.getCatalogue().toString());
            admin.ServeClient(client);
            admin.showBlackList();
            
            client.setMoney(100);
            admin.ServeClient(client);
            admin.showBlackList();
            in.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }


}