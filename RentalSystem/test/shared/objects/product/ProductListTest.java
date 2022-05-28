package shared.objects.product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ProductListTest {
    private ProductList productList;

    @BeforeEach
    void setUp() {
        productList = new ProductList();
        productList.add(new Product(0, 14.99, Color.black, EquipmentType.ski, new MetricFormat(150), 5));
        productList.add(new Product(1, 24.99, Color.black, EquipmentType.helmet, new LabelFormat("XL"), 5));
        productList.add(new Product(2, 6.99, Color.black, EquipmentType.skiPoles, new LabelFormat("40"), 5));
        productList.add(new Product(13, 4.99, Color.black, EquipmentType.ski, new MetricFormat(150), 5));
        productList.add(new Product(4, 10.99, Color.black, EquipmentType.ski, new MetricFormat(150), 5));
        productList.add(new Product(5, 12.99, Color.black, EquipmentType.ski, new MetricFormat(150), 5));
    }

    @Test
    void addElement() {
        Product product = new Product(6, 12.99, Color.black, EquipmentType.ski, new MetricFormat(150),5);
        productList.add(product);

        assertEquals(6, productList.get(6).getId());
    }

    @Test
    void add() {
        productList.add(14.99, Color.black, EquipmentType.ski, new MetricFormat(150),5);

        assertEquals(14, productList.getByIndex(6).getId());
    }

    @Test
    void changePrice() {
        productList.change(5, 11.99, Color.black, new MetricFormat(150));
        assertEquals(11.99, productList.getByIndex(5).getPrice());
    }

    @Test
    void changeColor() {
        productList.change(5, 10.99, Color.red, new MetricFormat(150));
        assertEquals(Color.red, productList.getByIndex(5).getColor());
    }

    @Test
    void changeSize() {
        productList.change(5, 10.99, Color.black, new MetricFormat(160));
        assertEquals("160.00cm", productList.getByIndex(5).getSize().getSize());
    }

    @Test
    void get() {
        Product product = new Product(13, 4.99, Color.black, EquipmentType.ski, new MetricFormat(150), 5);
        assertEquals(product, productList.get(13));
    }

    @Test
    void getByIndex() {
        Product product = new Product(2, 6.99, Color.black, EquipmentType.skiPoles, new LabelFormat("40"),5);
        assertEquals(product, productList.getByIndex(2));
    }

    @Test
    void remove() {
        productList.remove(13);
        assertEquals(4, productList.getByIndex(3).getId());
    }

    @Test
    void removeByIndex() {
        productList.removeByIndex(4);
        assertEquals(5, productList.getByIndex(4).getId());
    }

    @Test
    void size() {
        assertEquals(6, productList.size());
    }

    @Test
    void getUniqueId() {
        assertEquals(14, productList.getUniqueId());
    }

    @Test
    void testEqualsTrue() {
        ProductList productListDuplicate = new ProductList();
        productListDuplicate.add(new Product(0, 14.99, Color.black, EquipmentType.ski, new MetricFormat(150), 5));
        productListDuplicate.add(new Product(1, 24.99, Color.black, EquipmentType.helmet, new LabelFormat("XL"), 5));
        productListDuplicate.add(new Product(2, 6.99, Color.black, EquipmentType.skiPoles, new LabelFormat("40"), 5));
        productListDuplicate.add(new Product(13, 4.99, Color.black, EquipmentType.ski, new MetricFormat(150), 5));
        productListDuplicate.add(new Product(4, 10.99, Color.black, EquipmentType.ski, new MetricFormat(150), 5));
        productListDuplicate.add(new Product(5, 12.99, Color.black, EquipmentType.ski, new MetricFormat(150), 5));

        assertEquals(productListDuplicate, productList);
    }

    @Test
    void testEqualsFalse1() {
        ProductList productListDuplicate = new ProductList();
        productListDuplicate.add(new Product(1, 14.99, Color.black, EquipmentType.ski, new MetricFormat(150), 5));
        productListDuplicate.add(new Product(1, 24.99, Color.black, EquipmentType.helmet, new LabelFormat("XL"), 5));
        productListDuplicate.add(new Product(2, 6.99, Color.black, EquipmentType.skiPoles, new LabelFormat("40"), 5));
        productListDuplicate.add(new Product(13, 4.99, Color.black, EquipmentType.ski, new MetricFormat(150), 5));
        productListDuplicate.add(new Product(4, 10.99, Color.black, EquipmentType.ski, new MetricFormat(150), 5));
        productListDuplicate.add(new Product(5, 12.99, Color.black, EquipmentType.ski, new MetricFormat(150), 5));

        assertNotEquals(productListDuplicate, productList);
    }

    @Test
    void testEqualsFalse2() {
        ProductList productListDuplicate = new ProductList();
        productListDuplicate.add(new Product(0, 14.99, Color.black, EquipmentType.ski, new MetricFormat(150),5));
        productListDuplicate.add(new Product(1, 24.99, Color.black, EquipmentType.helmet, new LabelFormat("XL"), 5));
        productListDuplicate.add(new Product(2, 6.99, Color.black, EquipmentType.skiPoles, new LabelFormat("40"), 5));
        productListDuplicate.add(new Product(13, 4.98, Color.black, EquipmentType.ski, new MetricFormat(150), 5));
        productListDuplicate.add(new Product(4, 10.99, Color.black, EquipmentType.ski, new MetricFormat(150), 5));
        productListDuplicate.add(new Product(5, 12.99, Color.black, EquipmentType.ski, new MetricFormat(150), 5));

        assertNotEquals(productListDuplicate, productList);
    }

    @Test
    void copy() {
        ProductList productListDuplicate = productList.copy();
        assertEquals(productListDuplicate, productList);
    }

    @Test
    void convertToStringArrayList() {
        productList = new ProductList();
        Product p1 = new Product(1,1, Color.black, EquipmentType.helmet, new LabelFormat("S"), 5);
        Product p2 = new Product(2,5, Color.red, EquipmentType.helmet, new LabelFormat("M"), 5);
        productList.add(p1);
        productList.add(p2);

        ArrayList<String> temp = new ArrayList<>();
        temp.add(p1.toString());
        temp.add(p2.toString());

        assertEquals(temp, productList.convertToStringArrayList());
    }

    @Test
    void testToString() {
        productList = new ProductList();
        Product p1 = new Product(1,1, Color.black, EquipmentType.helmet, new LabelFormat("S"), 5);
        Product p2 = new Product(2,5, Color.red, EquipmentType.helmet, new LabelFormat("M"), 5);
        productList.add(p1);
        productList.add(p2);

        String temp = p1 + "\n" + p2 + "\n";

        assertEquals(temp, productList.toString());
    }

    @Test
    void getAllProductsByQuantitySize() {
        productList = new ProductList();
        Product p1 = new Product(1,1, Color.black, EquipmentType.helmet, new LabelFormat("S"), 5);
        Product p2 = new Product(2,5, Color.red, EquipmentType.helmet, new LabelFormat("M"), 5);
        Product p3 = new Product(3,10, Color.blue, EquipmentType.helmet, new LabelFormat("L"), 10);

        productList.add(p1);
        productList.add(p1);
        productList.add(p1);
        productList.add(p2);
        productList.add(p2);
        productList.add(p3);
        productList.add(p3);

        assertEquals(3, productList.getAllProductsByQuantity().size());
    }

    @Test
    void getAllProductsByQuantity1() {
        productList = new ProductList();
        Product p1 = new Product(1,1, Color.black, EquipmentType.helmet, new LabelFormat("S"), 5);
        Product p2 = new Product(2,5, Color.red, EquipmentType.helmet, new LabelFormat("M"), 5);
        Product p3 = new Product(3,10, Color.blue, EquipmentType.helmet, new LabelFormat("L"), 10);

        productList.add(p1);
        productList.add(p1);
        productList.add(p1);
        productList.add(p2);
        productList.add(p2);
        productList.add(p3);
        productList.add(p3);

        assertEquals(3, productList.getAllProductsByQuantity().get(p1));
    }

    @Test
    void getAllProductsByQuantity2() {
        productList = new ProductList();
        Product p1 = new Product(1,1, Color.black, EquipmentType.helmet, new LabelFormat("S"), 5);
        Product p2 = new Product(2,5, Color.red, EquipmentType.helmet, new LabelFormat("M"), 5);
        Product p3 = new Product(3,10, Color.blue, EquipmentType.helmet, new LabelFormat("L"), 10);

        productList.add(p1);
        productList.add(p1);
        productList.add(p1);
        productList.add(p2);
        productList.add(p2);
        productList.add(p3);
        productList.add(p3);

        assertEquals(2, productList.getAllProductsByQuantity().get(p3));
    }
}
