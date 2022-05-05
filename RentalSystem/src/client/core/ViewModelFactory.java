package client.core;

import client.model.ManageProducts;
import client.view.administratorView.AdministratorViewModel;

public class ViewModelFactory {
	private AdministratorViewModel administratorViewModel;
	private ManageProducts manageProducts;


	public ViewModelFactory(ManageProducts manageProducts)
	{
		this.manageProducts = manageProducts;
	}

	public AdministratorViewModel getAdministratorViewModel() {
		if (administratorViewModel == null) {
			administratorViewModel = new AdministratorViewModel(manageProducts);
		}

		return administratorViewModel;
	}
}
