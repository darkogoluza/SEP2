package shared.objects;

import java.util.ArrayList;

public class ProductArrayList {
    private ArrayList<Product> list;

    public ProductArrayList() {
        list = new ArrayList<>();
    }

    public void add(Product element) {
        list.add(element);
    }

    public void add(double price, Color color, EquipmentType equipmentType, Size size) {
        Product product = new Product(getUniqueId(), price, color, equipmentType, size);
        list.add(product);
    }

    public Product get(int id) {
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getId() == id)
                return list.get(id);
        }

        return null;
    }

    public Product getByIndex(int index) {
        return list.get(index);
    }

    public Product remove(int id) {
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getId() == id)
                return list.remove(id);
        }

        return null;
    }

    public Product removeByIndex(int index) {
        return list.remove(index);
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
        if(!(obj instanceof ProductArrayList productArrayList))
            return false;

        return productArrayList.list.equals(list);
    }

    public ProductArrayList copy() {
        ProductArrayList temp = new ProductArrayList();

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
}