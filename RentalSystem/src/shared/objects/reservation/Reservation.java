package shared.objects.reservation;

import shared.objects.product.ProductList;

import java.sql.Date;

public class Reservation
{
    private int id;
    private String userName;
    private Date createdAt;
    private Date returnedAt;
    private ProductList productList;
    private ReservationStatus status;

    public Reservation(int id, String userName, ProductList products)
    {
        this.id = id;
        this.userName = userName;
        productList = products;
        status = ReservationStatus.rented;
        createdAt = new Date(System.currentTimeMillis());
        returnedAt = null;

    }private Reservation( int id, String userName, ProductList products, ReservationStatus status, Date createdAt){
        this.id = id;
        this.userName = new String(userName);
        this.productList = products.copy();
        this.status = ReservationStatus.valueOf(status.toString());
        this.createdAt = new Date(createdAt.getTime());
    }

        public int getId () {
        return id;
    }

        public String getUserName () {
        return userName;
    }

        public Date getCreatedAt () {
        return createdAt;
    }

        public Date getCreatedAtTime () {
        return createdAt;
    }

        public Date getReturnedAt () {
        return returnedAt;
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
            && reservation.status.equals(status);
    }

	public Reservation copy () {
        return new Reservation(id, userName, productList, status, createdAt);
    }

	public String toString () {
        StringBuilder value = new StringBuilder();


		value.append(String.format(
			"id: %d  username: %s  created at: %s  status: %s\n%s", id,
			userName, createdAt, status, productList.toString()));

        return value.toString();

    }
    }

