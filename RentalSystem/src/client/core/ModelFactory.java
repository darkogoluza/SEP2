package client.core;

import client.model.product.ManageProducts;
import client.model.product.ManageProductsManager;

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
