package client.core;

import client.model.ManageProducts;
import client.view.administratorView.AdministratorViewModel;
import client.view.customerAllEquipment.CustomerAllEquipmentViewModel;

public class ViewModelFactory {
	private AdministratorViewModel administratorViewModel;
	private ManageProducts manageProducts;

	private CustomerAllEquipmentViewModel customerAllEquipmentViewModel;


	public ViewModelFactory(ManageProducts manageProducts)
	{
		this.manageProducts = manageProducts;
	}

	/**
	 * Lazy initiation of view model for administrator
	 * @return AdministratorViewModel object
	 */
	public AdministratorViewModel getAdministratorViewModel() {
		if (administratorViewModel == null) {
			administratorViewModel = new AdministratorViewModel(manageProducts);
		}

		return administratorViewModel;
	}

	/**
	 * Lazy initiation of view model for all equipment
	 * @return CustomerAllEquipmentViewModel object
	 */
	public CustomerAllEquipmentViewModel getCustomerAllEquipmentView() {
		if (customerAllEquipmentViewModel == null) {
			customerAllEquipmentViewModel = new CustomerAllEquipmentViewModel(manageProducts);
		}

		return customerAllEquipmentViewModel;
	}
}
