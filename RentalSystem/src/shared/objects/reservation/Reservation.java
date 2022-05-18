package shared.objects.reservation;

import shared.objects.product.ProductList;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Formatter;

public class Reservation implements Serializable
{
    private int id;
    private String userName;
    private Timestamp createdAt;
    private Timestamp expiresAt;
    private ProductList productList;
    private ReservationStatus status;

    public Reservation(int id, String userName, ProductList products)
    {
        this.id = id;
        this.userName = userName;
        productList = products;
        status = ReservationStatus.rented;
        createdAt = new Timestamp(System.currentTimeMillis());
        expiresAt = new Timestamp(System.currentTimeMillis() + 86400000);

    }private Reservation( int id, String userName, ProductList products, ReservationStatus status, Timestamp createdAt, Timestamp expiresAt){
        this.id = id;
        this.userName = new String(userName);
        this.productList = products.copy();
        this.status = ReservationStatus.valueOf(status.toString());
        this.createdAt = new Timestamp(createdAt.getTime());
        this.expiresAt = new Timestamp(expiresAt.getTime());
    }

        public int getId () {
        return id;
    }

        public String getUserName () {
        return userName;
    }

        public Timestamp getCreatedAt () {
        return createdAt;
    }

        public Timestamp getExpiresAt() {
        return expiresAt;
    }

        public ReservationStatus getStatus () {
        return status;
    }

        public ProductList getProducts () {
        return productList;
    }

        public void setStatus (ReservationStatus status){
        this.status = status;
    }

        public boolean equals (Object obj){
        if (!(obj instanceof Reservation reservation))
            return false;

        return reservation.id == id && reservation.userName.equals(userName)
            && reservation.createdAt.equals(createdAt) && reservation.productList.equals(productList)
            && reservation.status.equals(status) && reservation.expiresAt.equals(expiresAt);
    }

	public Reservation copy () {
        return new Reservation(id, userName, productList, status, createdAt, expiresAt);
    }

	public String toString () {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM, yyyy K:mm a");
		return String.format("<%03d> %s [%s - %s] status: %s", id, userName, simpleDateFormat.format(createdAt), simpleDateFormat.format(expiresAt), status.toString());
    }

    public void setCreateAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public void setExpiresAt(Timestamp expiresAt) {
        this.expiresAt = expiresAt;
    }
}

