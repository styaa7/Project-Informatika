package craftflow.controller.admin;

import java.net.URL;
import java.util.ResourceBundle;

import craftflow.model.Order;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FormTambahOrderController implements Initializable {

    @FXML
    private TextField orderIdField;
    @FXML
    private TextField customerField;
    @FXML
    private TextField productField;
    @FXML
    private DatePicker dbDate;
    @FXML 
    private TextField priceField;

    private Order orderBaru;
    
    @FXML
    public void simpanButton(ActionEvent event) {
        try {
            String tempId = orderIdField.getText();
            String tempNama = customerField.getText();
            String tempProduct = productField.getText();
            String tempDate = dbDate.getValue().toString();
            double tempPrice = Double.parseDouble(priceField.getText());

            orderBaru = new Order(tempId, tempNama, tempProduct, tempDate, tempPrice);

            Node source = (Node) event.getSource();

            Stage stage = (Stage) source.getScene().getWindow();

            stage.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Tidak Boleh Kosong");
        }
    }

    public Order getOrderBaru() {
        return orderBaru;
    }

    @FXML 
    public void batalButton(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    
    }
    
}
