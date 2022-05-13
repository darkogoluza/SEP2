package client.model.basket;

import shared.objects.product.Product;
import shared.util.PropertyChangeSubject;

public interface ManageBasket extends PropertyChangeSubject {
    void add (Product product);
    Product remove (Product product);
    void clear();
    double getTotalPrice();
    int size();
}
