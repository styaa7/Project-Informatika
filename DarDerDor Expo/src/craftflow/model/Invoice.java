package craftflow.model;

public class Invoice {
    private String invoiceId;
    private String orderId;
    private double total;
    private String paymentStatus;


    public Invoice(String invoiceId, String orderId, double total, String paymentStatus) {
        this.invoiceId = invoiceId;
        this.orderId = orderId;
        this.total = total;
        this.paymentStatus = paymentStatus;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getInvoiceId() {
        return this.invoiceId;
    }

    public String getOrderId() {
        return this.orderId;
    }

    public double getTotal() {
        return this.total;
    }

    public String getPaymentStatus() {
        return this.paymentStatus;
    }

    public void generateInvoice() {}
}
