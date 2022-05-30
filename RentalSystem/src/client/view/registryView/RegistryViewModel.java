package client.view.registryView;

import client.model.ModelProxy;
import javafx.beans.property.*;
import shared.objects.errors.AlertHandler;
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

    public boolean createAccount()
    {

		if	(userNameProperty.getValue() == "") {
			AlertHandler.getInstance().emptyCredentials("Username");
			return false;
		}
		else if (passwordProperty.getValue() == "") {
			AlertHandler.getInstance().emptyCredentials("Password");
			return false;
		}
		else if (phoneNumberProperty.getValue() == "") {
			AlertHandler.getInstance().emptyCredentials("Phone");
			return false;
		}

		if (modelProxy.getManageUser().get(userNameProperty.getValue()) != null) {
			AlertHandler.getInstance().userExists();
			return false;
		}

		if (passwordProperty.getValue().equals(confirmPasswordProperty.getValue())) {
			boolean isAdmin = modelProxy.getManageUser().getLoggedUser() != null && modelProxy.getManageUser().getLoggedUser().getRole().equals(UserRole.admin);

			if (isAdmin) {
				modelProxy.getManageUser().add(new User(userNameProperty.getValue(), passwordProperty.getValue(), phoneNumberProperty.getValue(), UserRole.employee));
			}
			else {
				modelProxy.getManageUser().add(new User(userNameProperty.getValue(), passwordProperty.getValue(), phoneNumberProperty.getValue()));
			}

			modelProxy.getManageUser().login(userNameProperty.getValue(), passwordProperty.getValue());
			return true;
		}
    	else {
			AlertHandler.getInstance().passwordsDoNotMatch();
			return false;
		}


	}

    public void clearFields() {
        userNameProperty.set("");
        passwordProperty.set("");
        confirmPasswordProperty.set("");
        phoneNumberProperty.set("");
    }
}
