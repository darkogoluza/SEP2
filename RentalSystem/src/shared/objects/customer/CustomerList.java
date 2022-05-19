package shared.objects.customer;

import java.util.ArrayList;

public class CustomerList {
    private ArrayList<Customer> customers;

    public CustomerList() {
        customers = new ArrayList<>();
    }
    public void add(Customer customer)
    {
        customers.add(customer);
    }
    public void add(String username, String phoneNo, String password)
    {
        customers.add(new Customer(username, phoneNo, password));
    }

    public ArrayList<Customer> getCustomers()
    {
        return customers;
    }
}
