package client.core;

import client.model.ManageProducts;
import client.model.ManageProductsManager;
import client.view.administratorView.AdministratorViewModel;

public class ModelFactory {
    private ManageProducts manageProducts;

    public ModelFactory(){

    }

	/**
	 * Lazy initiation of administratorModel
	 * @return ManageProducts object
	 */
    public ManageProducts getAdministratorModel(){
        if(manageProducts == null){
            manageProducts = new ManageProductsManager();
        }

        return manageProducts;
    }
	/**
	 * Lazy initiation of customerModel
	 * @return ManageProducts object
	 */
    public ManageProducts getCustomerModel(){
        if(manageProducts == null){
            manageProducts = new ManageProductsManager();
        }

        return manageProducts;
    }
}
