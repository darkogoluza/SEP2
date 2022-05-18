package shared.objects.reservation;

import shared.objects.product.ProductList;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Reservation {
    private int id;
    private String userName;
    private LocalDateTime createdAt;
    private ProductList productList;
    private ReservationStatus status;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");


    public Reservation(int id, String userName, ProductList products) {
        this.id = id;
        this.userName = userName;
        productList = products;
        status = ReservationStatus.rented;
        LocalDateTime createdAt = LocalDateTime.now();
    }

    private Reservation(int id, String userName, ProductList products, ReservationStatus status, LocalDateTime createdAt) {
        this.id = id;
        this.userName = new String(userName);
        this.productList = products.copy();
        this.status = ReservationStatus.valueOf(status.toString());
        this.createdAt = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public ProductList getProducts() {
        return productList;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    public boolean equals(Object obj) {
        if(!(obj instanceof Reservation reservation))
            return false;

        return reservation.id == id &&
                reservation.userName.equals(userName) &&
                reservation.createdAt.equals(createdAt) &&
                reservation.productList.equals(productList) &&
                reservation.status.equals(status);
    }

    public Reservation copy() {
        return new Reservation(id, userName, productList, status, createdAt);
    }

    public String toString() {
        StringBuilder value = new StringBuilder();

        for (int i = 0; i < productList.size(); i++) {
            value.append(String.format("id: %d\nusername: %s\ncreated at: %s\nstatus: %s\n%s", id, userName, createdAt, status, productList.toString()));
        }

        return value.toString();

    }
}
