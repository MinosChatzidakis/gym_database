package Gym_project;
import java.time.LocalDateTime;

public class PendingPayment {
    private int paymentId;
    private PaymentMethods paymentMethod;
    private PaymentStatus paymentStatus;
    private String customerFullName;
    private LocalDateTime dateOfReservation;
    private LocalDateTime dateOfSession;
    private float amount;


    // Empty default constructor
    public PendingPayment() {
    }

    public PendingPayment(int paymentId, PaymentMethods paymentMethod, PaymentStatus paymentStatus, String customerFullName, LocalDateTime dateOfReservation,LocalDateTime dateOfSession, float amount) {
        this.paymentId = paymentId;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
        this.customerFullName = customerFullName;
        this.dateOfReservation = dateOfReservation;
        this.dateOfSession = dateOfSession;
        this.amount = amount;
    }


    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public PaymentMethods getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethods paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getCustomerFullName() {
        return customerFullName;
    }

    public void setCustomerFullName(String customerFullName) {
        this.customerFullName = customerFullName;
    }

    public LocalDateTime getDateOfReservation() {
        return dateOfReservation;
    }

    public void setDateOfReservation(LocalDateTime dateOfReservation) {
        this.dateOfReservation = dateOfReservation;
    }

    public LocalDateTime getDateOfSession() {
        return dateOfSession;
    }

    public void setDateOfSession(LocalDateTime dateOfSession) {
        this.dateOfSession = dateOfSession;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}