package server.model.basket;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shared.objects.product.Color;
import shared.objects.product.EquipmentType;
import shared.objects.product.LabelFormat;
import shared.objects.product.Product;

import static org.junit.jupiter.api.Assertions.*;

class ProductsInBasketTest {

    private ProductsInBasket productsInBasket;

    @BeforeEach
    void setUp() {
        productsInBasket = new ProductsInBasket(
			new Product(0,
						15,
							Color.black,
							EquipmentType.helmet,
							new LabelFormat("X"),
						10),
						5);
    }

	/**
	 * Methods
	 */
    @Test
    void getName() {
        assertEquals(Color.black.toString() + " " + EquipmentType.helmet.toString(), productsInBasket.getName());
    }

    @Test
    void getPricePerUnit() {
        assertEquals("15.00€", productsInBasket.getPricePerUnit());
    }

    @Test
    void getQuantity() {
        assertEquals(5 + "", productsInBasket.getQuantity());
    }

    @Test
    void getSize() {
        assertEquals("X", productsInBasket.getSize());
    }

    @Test
    void getTotalPrice() {
        assertEquals("75.00€", productsInBasket.getTotalPrice());
    }

    @Test
    void getProduct() {
        assertEquals(new Product(0, 15, Color.black, EquipmentType.helmet, new LabelFormat("X"),10), productsInBasket.getProduct());
    }
}
