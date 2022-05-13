package client.core;

import client.model.ModelProxy;
import client.view.administratorView.AdministratorViewModel;
import client.view.customerAllEquipment.CustomerAllEquipmentViewModel;
import client.view.employeeAllOrders.EmployeeAllOrdersViewModel;

public class ViewModelFactory {
	private AdministratorViewModel administratorViewModel;
	private ModelProxy modelProxy;

	private CustomerAllEquipmentViewModel customerAllEquipmentViewModel;
	private EmployeeAllOrdersViewModel employeeAllOrdersViewModel;

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

	public EmployeeAllOrdersViewModel getEmployeeViewModel() {
		if (employeeAllOrdersViewModel == null) {
			employeeAllOrdersViewModel = new EmployeeAllOrdersViewModel(modelProxy);
		}

		return employeeAllOrdersViewModel;
	}
}
