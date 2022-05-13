package client.core;

import client.model.ModelProxy;
import client.view.CustomerBasketView.CustomerBasketViewModel;
import client.view.administratorView.AdministratorViewModel;
import client.view.customerAllEquipment.CustomerAllEquipmentViewModel;

public class ViewModelFactory {
	private AdministratorViewModel administratorViewModel;
	private ModelProxy modelProxy;

	private CustomerAllEquipmentViewModel customerAllEquipmentViewModel;
	private CustomerBasketViewModel customerBasketViewModel;


	public ViewModelFactory(ModelProxy modelProxy)
	{
		this.modelProxy = modelProxy;
	}

	/**
	 * Lazy initiation of view model for administrator
	 * @return AdministratorViewModel object
	 */
	public AdministratorViewModel getAdministratorViewModel() {
		if (administratorViewModel == null) {
			administratorViewModel = new AdministratorViewModel(modelProxy);
		}

		return administratorViewModel;
	}

	/**
	 * Lazy initiation of view model for all equipment
	 * @return CustomerAllEquipmentViewModel object
	 */
	public CustomerAllEquipmentViewModel getCustomerAllEquipmentView() {
		if (customerAllEquipmentViewModel == null) {
			customerAllEquipmentViewModel = new CustomerAllEquipmentViewModel(modelProxy);
		}

		return customerAllEquipmentViewModel;
	}

	/**
	 * Lazy initiation of view model for basket view
	 * @return CustomerBasketViewModel object
	 */
	public CustomerBasketViewModel getCustomerBasketViewModel() {
		if(customerBasketViewModel == null) {
			customerBasketViewModel = new CustomerBasketViewModel(modelProxy);
		}

		return customerBasketViewModel;
	}
}
