package shared.objects.product;

import java.io.Serializable;

/**
 * Defines a single product that contains ID, Price, Color, EquipmentType and Size.
 */
public class Product implements Serializable
{
    private int id;
    private double price;
    private Color color;
    private EquipmentType type;
    private Size size;
    private int amount;

    /**
     * Constructor
     * @param id ID of the product.
     * @param price Price of the product in euros.
     * @param color Color of the product values can be: red, blue, green, black, white, pink.
     * @param type Type of equipment values can be: helmet, ski, skiPoles, snowboard, skiShoes, snowboardShoes.
     * @param size Size of equipment values can be LabelFormat or MetricFormat.
     * @param amount Quantity of product available in inventory.
     */
    public Product(int id, double price, Color color, EquipmentType type, Size size, int amount) {
        this.price = price;
        this.id = id;
        this.color = color;
        this.type = type;
        this.size = size;
        this.amount = amount;
    }

    /**
     * Getter for price.
     * @return Returns price.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Setter for price.
     * @param price New value for price.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Getter for ID.
     * @return Returns ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Getter for color of product.
     * @return Returns Color.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Setter for the color of equipment.
     * @param color New value for Color.
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Getter for equipment type
     * @return Returns equipment type
     */
    public EquipmentType getType() {
        return type;
    }

    /**
     * Getter for size
     * @return Returns size.
     */
    public Size getSize() {
        return size;
    }

    /**
     * Getter for amount.
     * @return Returns amount.
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Setter for amount
     * @param amount New value for amount.
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * Setter for the size of equipment.
     * @param size New value for Size.
     */
    public void setSize(Size size) {
        this.size = size;
    }

    /**
     * Converts Product to string.
     * @return Returns Product as string format is "Id: (id)\nPrice: (price)€\nColor: (color)\nType: (type)\nSize: (size)".
     */
    public String toString() {
        return String.format("Id: %03d  %s  %s  %s  %.02f€ Amount: %d", id, color, size, type, price, amount);
    }

    /**
     * Equals method.
     * @param obj Object we are checking if the Product is equal to.
     * @return Returns true if the passed object equals this class, returns false if not.
     */
    public boolean equals(Object obj) {
        if(!(obj instanceof Product product))
            return false;

        return product.price == price &&
                product.id == id &&
                product.color.equals(color) &&
                product.type.equals(type) &&
                product.size.equals(size) &&
                product.amount == amount;
    };

    /**
     * Copy method.
     * @return Returns exact copy of Product.
     */
    public Product copy() {
        return new Product(id, price, color, type, size, amount);
    }
}
