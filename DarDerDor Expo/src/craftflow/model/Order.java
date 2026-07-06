package craftflow.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("Order")
public class Order {
    private String orderId;
    private String customerName;
    private String productId;
    // private int quantity;
    private double totalPrice;
    // private String tipePesanan;
    private String status;
    private String tanggalPesanan;
    private double ratioProgress;

    public Order(String orderId, String customerName, String productId, String tanggalPesanan, double totalPrice) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.productId = productId;
        this.status = "Waiting for Payment";
        this.totalPrice = totalPrice;
        // this.tipePesanan = tipePesanan;
        this.tanggalPesanan = tanggalPesanan;
        this.ratioProgress = 0;
    }

    public String getOrderId() {
        return this.orderId;
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public String getProductId() {
        return this.productId;
    }

    public String getStatus() {
        return this.status;
    }

    public double getTotal() {
        return this.totalPrice;
    }

    // public String getTipePesanan() {
    //     return this.tipePesanan;
    // }

    public String getTanggalPesanan() {
        return this.tanggalPesanan;
    }

    public void updateRatioProgress() {
        if (this.status == null) {
            this.ratioProgress = 0.0;
            return;
        }

        String statusBersih = this.status.trim().toLowerCase();

        switch (statusBersih) {
            case "completed":
                this.ratioProgress = 1.0;
                break;
            case "cancelled":
                this.ratioProgress = 0.0;
                break;
            case "shipped":
                this.ratioProgress = 0.7;
                break;
            case "processing":
                this.ratioProgress = 0.5;
                break;
            case "waiting for payment":
                this.ratioProgress = 0.1;
                break;
            default:
                this.ratioProgress = 0.0;
                break;
            }
    }

    public double getRatio() {
        updateRatioProgress();
        return this.ratioProgress;
    }

    public void setOrderId(String id) {
        this.orderId = id;
    }

    public void setCustomerName(String name) {
        this.customerName = name;
    }

    public void setProductId(String product) {
        this.productId = product;
    }

    public void setStatus(String status) {
        this.status = status;
        getRatio();
    }

    public void setTotal(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    // public void setTipePesanan(String tipePesanan) {
    //     this.tipePesanan = tipePesanan;
    // }

    public void setTanggalPesanan(String tanggalPesanan) {
        this.tanggalPesanan = tanggalPesanan;
    }

    public void createOrder() {}

    public void updateOrder() {}
}
