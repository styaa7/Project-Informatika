package craftflow.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("Product")
public class Product {
    
    private String productId;
    private String name;
    private String kategori;
    private double price;
    private int stock;
    private String status;

    public Product(String productId, String name, String kategori, double price, int stock){
        this.productId = productId;
        this.name = name;
        this.kategori = kategori;
        this.price = price;
        this.stock = stock;
        this.status = null;
    }

    public String getProductId() {
        return this.productId;
    }

    public String getName() {
        return this.name;
    }

    public String getKategori() {
        return this.kategori;
    }

    public double getPrice() {
        return this.price;
    }

    public int getStock() {
        return this.stock;
    }

    public String getStatus() {
        if(this.stock == 0 ) {
            this.status = "Out-of-Stock";
        } else if (this.stock <= 30) {
            this.status = "Low-Stock";
        } else {
            this.status = "Ready-Stock";
        }

        return this.status;
    }

    public void setProductId(String id) {
        this.productId = id;
    }

    public void setNama(String nama) {
        this.name = nama;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void addProduct() {}

    public void updateProduct() {}

    public void deleteProduct() {}

    public String getId() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getId'");
    }
}
