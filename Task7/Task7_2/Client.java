import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;

public class Client implements Serializable {
    private static final long serialVersionUID = 1L;

    protected String name;
    protected int mobileNumber;
    protected double money;
    protected Boolean isPaid;
    protected Catalogue clientProducts = new Catalogue();
    
 public final Date creation_date = new Date();
    
    public String getCreationDate() {
    	DateFormat dateFormatter = DateFormat.getDateTimeInstance(
    			DateFormat.DEFAULT, DateFormat.DEFAULT, AppLocale.get());
    String dateOut = dateFormatter.format(creation_date);
    return dateOut;
    }
    
    public Client() {
        this.name = null;
        this.mobileNumber = 0;
    }


    public Client(String name, int mobileNumber, double money, Boolean isPaid) {
    	this.money = money;
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.isPaid = isPaid;
    }

    public void addProduct(Product product) {
        clientProducts.addProductToCatalogue(product);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
    
    public Boolean getPaid() {
        return isPaid;
    }

    public void setPaid(Boolean paid) {
        this.isPaid = paid;
    }

    public int getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(int mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String toString() {
        return AppLocale.getString(AppLocale.client) + '{' +
                AppLocale.getString(AppLocale.name) + "=" + '\'' + name + '\'' +
                ", " + AppLocale.getString(AppLocale.mobileNumber) + "=" + mobileNumber + '}' +
                '\n' + getCreationDate() + '\n';
    }
    
    public Catalogue getClientCatalogue() {
    	return this.clientProducts;
    }

    public void showClientProducts() {
        System.out.println(this.toString());
    }
    
    double Cost() {
    	double sum = 0;
    	for (Product i : clientProducts.getProductCatalogue()) {
    		sum += i.getPrice();
    	}
    	return sum;
    }
}