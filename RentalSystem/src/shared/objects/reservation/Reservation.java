package shared.objects.reservation;

import shared.objects.product.ProductList;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Reservation implements Serializable
{
    private int id;
    private String userName;
    private Timestamp createdAt;
    private Timestamp expiresAt;
    private ProductList productList;
    private ReservationStatus status;

    /**
     * Constructor
     * @param id ID of the Reservation.
     * @param userName User who created the Reservation.
     * @param products All the products in the Reservation.
     */
    public Reservation(int id, String userName, ProductList products)
    {
        this.id = id;
        this.userName = userName;
        productList = products;
        status = ReservationStatus.rented;
        createdAt = new Timestamp(System.currentTimeMillis());
        expiresAt = new Timestamp(System.currentTimeMillis() + 86400000);

    }

    /**
     * Private constructor that is used for creating a copy of Reservation.
     * @param id ID of the Reservation.
     * @param userName User who created the Reservation.
     * @param products All the products in the Reservation.
     * @param status Status of the Reservation saying if the Reservation is ("rented", "notReturned", "Returned").
     * @param createdAt Date and Time of the creation of this Reservation.
     * @param expiresAt Date and Time when the Reservation expires and user must return it.
     */
    private Reservation( int id, String userName, ProductList products, ReservationStatus status, Timestamp createdAt, Timestamp expiresAt){
        this.id = id;
        this.userName = new String(userName);
        this.productList = products.copy();
        this.status = ReservationStatus.valueOf(status.toString());
        this.createdAt = new Timestamp(createdAt.getTime());
        this.expiresAt = new Timestamp(expiresAt.getTime());
    }

    /**
     * Getter for ID.
     * @return Returns ID.
     */
    public int getId () {
        return id;
    }

    /**
     * Getter for userName.
     * @return Returns userName.
     */
    public String getUserName () {
        return userName;
    }

    /**
     * Getter for creation TimeStamp.
     * @return Returns TimeStamp of when the product is created.
     */
    public Timestamp getCreatedAt () {
        return createdAt;
    }

    /**
     * Getter for expiration TimeStamp.
     * @return Returns TimeStamp of when the product will expire.
     */
    public Timestamp getExpiresAt() {
        return expiresAt;
    }

    /**
     * Getter for Status of the Reservation.
     * @return Returns the status of the Reservation which can be ("rented", "notReturned", "Returned").
     */
    public ReservationStatus getStatus () {
        return status;
    }

    /**
     * Getter for list of Products.
     * @return Returns the full list of products this Reservation contains.
     */
    public ProductList getProducts () {
        return productList;
    }

    /**
     * Setter for Reservation status.
     * @param status New value for Reservation status which can be ("rented", "notReturned", "Returned").
     */
    public void setStatus (ReservationStatus status){
        this.status = status;
    }

    /**
     * Equals method.
     * @param obj Object we are checking if the Reservation is equal to.
     * @return Returns true if the passed object equals this class, returns false if not.
     */
    public boolean equals (Object obj){
        if (!(obj instanceof Reservation reservation))
            return false;

        return reservation.id == id && reservation.userName.equals(userName)
            && reservation.createdAt.equals(createdAt) && reservation.productList.equals(productList)
            && reservation.status.equals(status) && reservation.expiresAt.equals(expiresAt);
    }

    /**
     * Copy method.
     * @return Returns exact copy of Reservation.
     */
	public Reservation copy () {
        return new Reservation(id, userName, productList, status, createdAt, expiresAt);
    }

    /**
     * Converts Reservation to string.
     * @return Returns Product as string format is "<001> TestUser [19 Mar, 2022 9:17 AM - 20 Mar, 2022 9:17 AM] status rented".
     */
	public String toString () {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM, yyyy K:mm a");
		return String.format("<%03d> %s [%s - %s] status: %s", id, userName, simpleDateFormat.format(createdAt), simpleDateFormat.format(expiresAt), status.toString());
    }

    /**
     * Setter for Date and Time when Reservation was created.
     * @param createdAt New value for creation Date and Time.
     */
    public void setCreateAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Setter for Date and Time when Reservation expires.
     * @param expiresAt New value for expiration Date nad Time.
     */
    public void setExpiresAt(Timestamp expiresAt) {
        this.expiresAt = expiresAt;
    }
}

