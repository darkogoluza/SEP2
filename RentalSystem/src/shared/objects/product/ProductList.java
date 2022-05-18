package shared.objects.product;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

public class ProductList implements Serializable
{
    private ArrayList<Product> list;

    public ProductList() {
        list = new ArrayList<>();
    }

    public Product add(Product element) {
        list.add(element);
        return element;
    }

    public Product add(double price, Color color, EquipmentType equipmentType, Size size) {
        Product product = new Product(getUniqueId(), price, color, equipmentType, size);
        list.add(product);
        return product;
    }

    public void change(int index, double price, Color color, Size size) {
        Product product = list.get(index);
        product.setPrice(price);
        product.setColor(color);
        product.setSize(size);
    }

    public Product get(int id) {
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getId() == id)
                return list.get(i);
        }

        return null;
    }

    public Product getByIndex(int index) {
        return list.get(index);
    }

    public Product remove(int id) {
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getId() == id)
                return list.remove(i);
        }

        return null;
    }

    public Product removeByIndex(int index) {
        return list.remove(index);
    }

    public Map<Product, Integer> getAllProductsByQuantity() {
        Map<Product, Integer> map = new Hashtable<>();
        for (int i = 0; i < list.size(); i++) {
            Product product = list.get(i);
            map.merge(product, 1, Integer::sum);
        }

        return map;
    }


    public int size() {
        return list.size();
    }

    public int getUniqueId() {
        int maxId = -1;
        for (Product product : list) {
            if(product.getId() > maxId) {
                maxId = product.getId();
            }
        }

        return ++maxId;
    }

    public boolean equals(Object obj) {
        if(!(obj instanceof ProductList productList))
            return false;

        return productList.list.equals(list);
    }

    public ProductList copy() {
        ProductList temp = new ProductList();

        for (Product product : list) {
            temp.add(product);
        }

        return temp;
    }

    public ArrayList<String> convertToStringArrayList() {
        ArrayList<String> temp = new ArrayList<>();

        for (Product p : list) {
            temp.add(p.toString());
        }

        return temp;
    }

    public String toString() {
        StringBuilder values = new StringBuilder();

        for (Product p : list) {
            values.append(p.toString()).append("\n");
        }

        return values.toString();
    }

    public double getTotalPrice() {
        double sum = 0;

        for (int i = 0; i < list.size(); i++) {
            sum += list.get(i).getPrice();
        }

        return sum;
    }

    public boolean isEmpty() {
        return list.size() == 0;
    }
}