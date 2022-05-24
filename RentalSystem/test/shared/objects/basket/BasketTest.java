package shared.objects.basket;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shared.objects.product.Color;
import shared.objects.product.EquipmentType;
import shared.objects.product.LabelFormat;
import shared.objects.product.Product;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BasketTest {
    private Basket basket;

    @BeforeEach
    void setUp() {
        Product p1 = new Product(0, 10, Color.black, EquipmentType.helmet, new LabelFormat("XL"));
        Product p2 = new Product(1, 5, Color.black, EquipmentType.helmet, new LabelFormat("XL"));
        Product p3 = new Product(2, 10, Color.red, EquipmentType.helmet, new LabelFormat("XL"));
        Product p4 = new Product(3, 10, Color.black, EquipmentType.helmet, new LabelFormat("L"));
        basket = new Basket("testUser");

        basket.getProducts().add(p1);
        basket.getProducts().add(p1);
        basket.getProducts().add(p1);
        basket.getProducts().add(p1);
        basket.getProducts().add(p2);
        basket.getProducts().add(p2);
        basket.getProducts().add(p2);
        basket.getProducts().add(p3);
        basket.getProducts().add(p4);
    }

    @Test
    void getProducts() {
        assertEquals(9, basket.getProducts().size());
    }

    @Test
    void getCustomerUsername() {
        assertEquals("testUser", basket.getCustomerUsername());
    }

    @Test
    void getAllProductsByQuantity1() {
        Map<Product, Integer> map = basket.getAllProductsByQuantity();
        for (Map.Entry<Product, Integer> entry : map.entrySet()) {
            if(entry.getKey().getId() == 0){
                assertEquals(4, entry.getValue());
                return;
            }
        }

        fail();
    }

    @Test
    void getAllProductsByQuantity2() {
        Map<Product, Integer> map = basket.getAllProductsByQuantity();
        for (Map.Entry<Product, Integer> entry : map.entrySet()) {
            if(entry.getKey().getId() == 1){
                assertEquals(3, entry.getValue());
                return;
            }
        }

        fail();
    }

    @Test
    void getTotalPrice() {
        assertEquals(75, basket.getTotalPrice());
    }

    @Test
    void clear() {
        basket.clear();
        assertEquals(0, basket.getProducts().size());
    }
}
