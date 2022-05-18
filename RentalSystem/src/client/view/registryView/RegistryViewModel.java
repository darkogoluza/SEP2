package client.view.registryView;

import client.model.ModelProxy;
import javafx.beans.property.*;
import shared.objects.customer.Customer;
import shared.objects.user.User;


public class RegistryViewModel
{
    private StringProperty userNameProperty;
    private StringProperty passwordProperty;
    private StringProperty confirmPasswordProperty;
    private StringProperty phoneNumberProperty;
	private ModelProxy modelProxy;

    public RegistryViewModel(ModelProxy modelProxy) {
		this.modelProxy = modelProxy;

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
		if (passwordProperty.getValue().equals(confirmPasswordProperty.getValue()))
			modelProxy.getManageUser().add(new User(userNameProperty.getValue(), passwordProperty.getValue(), phoneNumberProperty.getValue()));
    	else {
			// TODO handle when passwords dont match
			System.out.println("Passwords don't match");
		}
	}
}
