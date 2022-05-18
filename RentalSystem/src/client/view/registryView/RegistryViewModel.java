package client.view.registryView;

import client.model.ModelProxy;
import javafx.beans.property.*;
import shared.objects.customer.Customer;


public class RegistryViewModel
{
    private StringProperty userNameProperty;
    private StringProperty passwordProperty;
    private StringProperty confirmPasswordProperty;
    private StringProperty phoneNumberProperty;

    public RegistryViewModel(ModelProxy modelProxy) {


        userNameProperty = new SimpleStringProperty();
        passwordProperty = new SimpleStringProperty();
        confirmPasswordProperty = new SimpleStringProperty();
        phoneNumberProperty = new SimpleStringProperty();
    }

    public Property<String> getUserName() {
        return userNameProperty;
    }

    public Property<String> getPassword() {
        return passwordProperty;
    }

    public Property<String> getConfirmedPassword() {
        return confirmPasswordProperty;
    }

    public Property<String> getPhoneNumber() {
        return phoneNumberProperty;
    }

    public void createAccount()
    {
        //TODO darko and tony finish it pls
    }
}
