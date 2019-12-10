import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Catalogue implements Serializable {
    private static final long serialVersionUID = 1L;
    private ArrayList<Product> productCatalogue = new ArrayList<Product>();

    public Catalogue() {
    }

    public Catalogue(ArrayList<Product> Catalogue) {
        this.productCatalogue = Catalogue;
    }

    public Catalogue(String fileName) {
        try {
            Scanner in = new Scanner(new File(fileName));

            while (in.hasNextLine()) {
            	String[] parts = in.nextLine().split(" ");
                String name = parts[0];
                double price = Double.parseDouble(parts[1]);

                productCatalogue.add(new Product(name, price, null));
            }

            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Product> getProductCatalogue() {
        return productCatalogue;
    }

    public void addProductToCatalogue(Product product) {
        productCatalogue.add(product);
    }

    public int getCatalogueSize() {
        return productCatalogue.size();
    }

    public void showCatalogue() {
        System.out.println("_______________" + AppLocale.getString(AppLocale.catalogue) + "_______________");
        for (Product product : productCatalogue) {
            System.out.println(product.toString());
        }
        System.out.println("_____________________________________________\n");
    }

    public String toString() {
        StringBuilder result = new StringBuilder(AppLocale.getString(AppLocale.catalogue) + ":\n");
        for (Product product : productCatalogue) {
            result.append(product);
            result.append('\n');
        }
        return result.toString();
    }
}