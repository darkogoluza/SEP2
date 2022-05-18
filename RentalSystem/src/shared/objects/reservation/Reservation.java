package shared.objects.reservation;

import shared.objects.product.ProductList;

import java.sql.Date;
import java.sql.Timestamp;

public class Reservation
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
        StringBuilder value = new StringBuilder();

		value.append(String.format(
			"id: %d  username: %s  created at: %s   expires at: %s  status: %s\n%s", id,
			userName, createdAt, expiresAt, status, productList.toString()));

        return value.toString();
    }

    public void setCreateAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public void setExpiresAt(Timestamp expiresAt) {
        this.expiresAt = expiresAt;
    }
}

