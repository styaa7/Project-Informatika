package craftflow.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;


import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import craftflow.model.Order;
import craftflow.model.Product;
import javafx.collections.ObservableList;

public class XMLService {
    
    public void loadUsers() {}

    public void saveProducts(ObservableList<Product> products) {
        try {
            XStream xstream = new XStream(new DomDriver());

            xstream.allowTypes(new Class[] {Product.class});
            
            xstream.alias("Product", Product.class);
            List<Product> productList = new ArrayList<>(products);

            FileOutputStream fos = new FileOutputStream("products.xml");

            xstream.toXML(productList, fos);

            fos.close();

            System.out.println("Product saved!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Product> loadProducts() {
        try {
            XStream xstream = new XStream(new DomDriver());

            xstream.allowTypes(new Class[] {Product.class});

            xstream.alias("Product", Product.class);
            File file = new File("products.xml");

            if (!file.exists()) {
                return new ArrayList<>();
            }

            FileInputStream fis = new FileInputStream(file);

            List<Product> products = (List<Product>) xstream.fromXML(fis);

            fis.close();

            return products;
        }catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void saveOrders(ObservableList<Order> orders) {
        try {
            XStream xstream = new XStream(new DomDriver());

            xstream.allowTypes(new Class[] {Order.class});
            
            xstream.alias("Order", Order.class);
            List<Order> orderList = new ArrayList<>(orders);

            FileOutputStream fos = new FileOutputStream("orders.xml");

            xstream.toXML(orderList, fos);

            fos.close();

            System.out.println("Product saved!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Order> loadOrders() {
        try {
            XStream xstream = new XStream(new DomDriver());

            xstream.allowTypes(new Class[] {Order.class});

            xstream.alias("Order", Order.class);
            File file = new File("orders.xml");

            if (!file.exists()) {
                return new ArrayList<>();
            }

            FileInputStream fis = new FileInputStream(file);

            List<Order> orders = (List<Order>) xstream.fromXML(fis);

            fis.close();

            return orders;
        }catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

}
