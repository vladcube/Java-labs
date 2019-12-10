import java.io.*;

public class Connector {
    private String fileName;


    public Connector(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void write(Catalogue catalogue) throws IOException {
        FileOutputStream fos = new FileOutputStream(fileName);
        try (ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeInt(catalogue.getCatalogueSize());
            for (Product product : catalogue.getProductCatalogue()) {
                oos.writeObject(product);
            }
            oos.flush();
        }
    }

    public Catalogue read() throws IOException, ClassNotFoundException {
        FileInputStream fin = new FileInputStream(fileName);
        try (ObjectInputStream ois = new ObjectInputStream(fin)) {
            int size = ois.readInt();
            Catalogue result = new Catalogue();

            int counter = 1;
            while (counter <= size) {
                result.addProductToCatalogue((Product) ois.readObject());
                counter++;
            }
            return result;
        }
    }


}