package client.view.registryView;

import client.model.ModelProxy;
import javafx.beans.property.*;
import shared.objects.errors.AlertHandler;
import shared.objects.user.User;
import shared.objects.user.UserRole;

/**
 * RegistryViewModel class for RegistryViewController
 */
public class RegistryViewModel
{
    private StringProperty userNameProperty;
    private StringProperty passwordProperty;
    private StringProperty confirmPasswordProperty;
    private StringProperty phoneNumberProperty;
	private ModelProxy modelProxy;

  /**
   * constructor with user fields as String
   * @param modelProxy
   */
    public RegistryViewModel(ModelProxy modelProxy) {
		this.modelProxy = modelProxy;

        userNameProperty = new SimpleStringProperty();
        passwordProperty = new SimpleStringProperty();
        confirmPasswordProperty = new SimpleStringProperty();
        phoneNumberProperty = new SimpleStringProperty();
    }

  /**
   * Getter for username
   @return
   */
    public Property<String> getUserName() {
        return userNameProperty;
    }

  /**
   * Getter for password
   @return
   */
    public Property<String> getPassword() {
        return passwordProperty;
    }

  /**
   * Getter for confirmed password
   @return
   */
    public Property<String> getConfirmedPassword() {
        return confirmPasswordProperty;
    }

  /**
   * Getter for phone number
   @return
   */
    public Property<String> getPhoneNumber() {
        return phoneNumberProperty;
    }

  /**
   * creates account for user,if all conditions are met
   * @return
   */
    public boolean createAccount()
    {

		if (modelProxy.getManageUser().get(userNameProperty.getValue()) != null) {
			AlertHandler.getInstance().userExists();
			return false;
		}

		// TODO validation here or in db?
		if (passwordProperty.getValue().equals(confirmPasswordProperty.getValue())) {
			boolean isAdmin = modelProxy.getManageUser().getLoggedUser() != null && modelProxy.getManageUser().getLoggedUser().getRole().equals(UserRole.admin);

			if (isAdmin) {
				modelProxy.getManageUser().add(new User(userNameProperty.getValue(), passwordProperty.getValue(), phoneNumberProperty.getValue(), UserRole.employee));
			}
			else {
				modelProxy.getManageUser().add(new User(userNameProperty.getValue(), passwordProperty.getValue(), phoneNumberProperty.getValue()));
			}

			modelProxy.getManageUser().login(userNameProperty.getValue(), passwordProperty.getValue());
      System.out.println("KURWA\n");
			return true;
		}
    	else {
			AlertHandler.getInstance().passwordsDoNotMatch();
			return false;
		}

      /**
       * Clear all fields
       */
	}

    public void clearFields() {
        userNameProperty.set("");
        passwordProperty.set("");
        confirmPasswordProperty.set("");
        phoneNumberProperty.set("");
    }
}
