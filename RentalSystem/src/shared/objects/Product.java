package shared.objects;

public class Product {
    private int id;
    private double price;
    private Color color;
    private EquipmentType type;
    private Size size;

    public Product(int id, double price, Color color, EquipmentType type, Size size) {
        this.price = price;
        this.id = id;
        this.color = color;
        this.type = type;
        this.size = size;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public EquipmentType getType() {
        return type;
    }

    public Size getSize() {
        return size;
    }

    public String toString() {
        return String.format("Id: %d\nPrice: %s€\nColor: %s\nType: %s\nSize: %s", id, price, color, type, size);
    }

    public boolean equals(Object obj) {
        if(!(obj instanceof Product product))
            return false;

        return product.price == price &&
                product.id == id &&
                product.color.equals(color) &&
                product.type.equals(type) &&
                product.size.equals(size);
    };

    public Product copy() {
        return new Product(id, price, color, type, size);
    }
}
