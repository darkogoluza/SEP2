package client.view.registryView;

import client.model.ModelProxy;
import javafx.beans.property.*;
import shared.objects.user.User;
import shared.objects.user.UserRole;


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
		// TODO validation here or in db?
		if (passwordProperty.getValue().equals(confirmPasswordProperty.getValue())) {
			boolean isAdmin = modelProxy.getManageUser().getLoggedUser() != null && modelProxy.getManageUser().getLoggedUser().getRole().equals(UserRole.admin);

			if (isAdmin) {
				modelProxy.getManageUser().add(new User(userNameProperty.getValue(), passwordProperty.getValue(), phoneNumberProperty.getValue(), UserRole.employee));
			}
			else {
				modelProxy.getManageUser().add(new User(userNameProperty.getValue(), passwordProperty.getValue(), phoneNumberProperty.getValue()));
			}
		}
    	else {
			// TODO handle when passwords dont match
			System.out.println("Passwords don't match");
		}
	}

    public void clearFields() {
        userNameProperty.set("");
        passwordProperty.set("");
        confirmPasswordProperty.set("");
        phoneNumberProperty.set("");
    }
}
