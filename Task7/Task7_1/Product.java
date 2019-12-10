import java.io.Serializable;

public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    protected String name;
    protected double price;
    protected Client client;
    private int ID;
    private static int counter = 0;



    public Product(String Name, double price, Client client) {
        this.name = Name;
        this.client = client;
        this.ID = counter++;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBookID() {
        return ID;
    }

    
    public void setPrice(double price) {
    	this.price = price;
    }
    
    public double getPrice() {
    	return this.price;
    }

    public Client get—lient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String toString() {
        return "Product{ ID = " + ID +
                ", Name = '" + name + '\'' +
                ", price = " + price +
                ", client =" + client +
                '}';
    }

}