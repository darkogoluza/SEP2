package client.core;

import client.model.ModelProxy;
import client.view.CustomerBasketView.CustomerBasketViewModel;
import client.view.EmployeeOrderDetails.EmployeeOrderDetailsViewModel;
import client.view.administratorView.AdministratorViewModel;
import client.view.customerAllEquipment.CustomerAllEquipmentViewModel;
import client.view.employeeAllOrders.EmployeeAllOrdersViewModel;
import client.view.CustomerSingleOrderView.SingleOrderViewModel;

public class ViewModelFactory {
	private AdministratorViewModel administratorViewModel;
	private CustomerAllEquipmentViewModel customerAllEquipmentViewModel;
	private CustomerBasketViewModel customerBasketViewModel;
	private EmployeeOrderDetailsViewModel employeeOrderDetailsViewModel;
	private ModelProxy modelProxy;
	private EmployeeAllOrdersViewModel employeeAllOrdersViewModel;
	private SingleOrderViewModel singleOrderViewModel;


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

	public EmployeeAllOrdersViewModel getEmployeeViewModel() {
		if (employeeAllOrdersViewModel == null) {
			employeeAllOrdersViewModel = new EmployeeAllOrdersViewModel(modelProxy.getManageReservations());
		}

		return employeeAllOrdersViewModel;
	}

	public SingleOrderViewModel getSingleOrderViewModel(int id) {
		if (singleOrderViewModel == null) {
			singleOrderViewModel = new SingleOrderViewModel(modelProxy, id);
		}

		return singleOrderViewModel;
	}

	public EmployeeOrderDetailsViewModel getEmployeeOrderDetailsViewModel(int id)
	{
		if(employeeOrderDetailsViewModel == null) {
			employeeOrderDetailsViewModel = new EmployeeOrderDetailsViewModel(modelProxy, id);
		}

		return employeeOrderDetailsViewModel;
	}

}
