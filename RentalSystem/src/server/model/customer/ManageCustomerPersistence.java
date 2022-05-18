package server.model.customer;

import shared.objects.customer.Customer;

import java.sql.SQLException;

public interface ManageCustomerPersistence
{
    void save(Customer customer) throws SQLException;
}
