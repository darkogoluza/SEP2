package server.model.customer;

import shared.objects.customer.Customer;
import shared.objects.customer.CustomerList;

import java.beans.PropertyChangeSupport;
import java.sql.SQLException;

public class ManageCustomerManager implements ManageCustomer{
    private CustomerList customers;
    private PropertyChangeSupport changeSupport;
    private ManageCustomerDatabase manageCustomerDatabase;

    public ManageCustomerManager() {
        customers = new CustomerList();
        changeSupport = new PropertyChangeSupport(this);

        try
        {
            manageCustomerDatabase = new ManageCustomerDatabase();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void add(Customer customer)
    {
        customers.add(customer);
    }

}
