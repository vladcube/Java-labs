public class Test {
    public static void main(String[] args) {
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * Inthernet - shop * * * * * * * * * * * * * * * * * * * * * * \n");

        try {
            Catalogue myCatalogue = new Catalogue("src/catalogue.txt");
            myCatalogue.showCatalogue();

            //Connector.write
            Connector myConnector = new Connector("output.dat");
            myConnector.write(myCatalogue);

            //Connector.read
            System.out.println("TESTING READ:");
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


        } catch (Exception e) {
            System.err.println("ERROR! " + e);
        }
    }


}