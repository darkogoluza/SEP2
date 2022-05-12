package client.core;

import client.view.administratorView.AdministratorViewController;
import client.view.customerAllEquipment.CustomerAllEquipmentViewController;
import client.view.employeeAllOrders.EmployeeAllOrdersController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewHandler
{
    private Stage stage;
    private ViewModelFactory vmf;
    private Scene administratorScene;
    private Scene customerAllEquipmentScene;
    private Scene employeeAllOrdersScene;


    public ViewHandler( ViewModelFactory vmf, Stage stage){
        this.vmf = vmf;
        this.stage = stage;
    }

	/**
	 * Open selected view
	 * This is what is opened when we launch an application
	 */
    public void start(){
		openEmployeeView();
        stage.show();
    }

	/**
	 * Get root with provided path
	 * @param path
	 * @param loader
	 * @return Parent root
	 */
    private Parent getRootByPath(String path, FXMLLoader loader){
        loader.setLocation(getClass().getResource(path));
        Parent root = null;

        try {
            root = loader.load();
        } catch (IOException e){
            e.printStackTrace();
        }

        return root;
    }

	/**
	 * Open administrator window
	 */
    public void openAdministratorView(){
        FXMLLoader loader = new FXMLLoader();
        if(administratorScene == null){
            Parent root = getRootByPath("/client/view/administratorView/AdministratorView.fxml", loader);
            AdministratorViewController controller = loader.getController();
            controller.init(this,vmf);
            administratorScene = new Scene(root);
        }

        stage.setTitle("Login");
        stage.setScene(administratorScene);
    }

	/**
	 * Open window with all equipment
	 */
    public void openCustomerAllEquipmentView(){
        FXMLLoader loader = new FXMLLoader();
        if(customerAllEquipmentScene == null){

            Parent root = getRootByPath("/client/view/customerAllEquipment/CustomerAllEquipmentView.fxml", loader);
            CustomerAllEquipmentViewController controller = loader.getController();
            controller.init(this,vmf);
            customerAllEquipmentScene = new Scene(root);
        }

        stage.setTitle("All equipment");
        stage.setScene(customerAllEquipmentScene);
    }

	public void openEmployeeView(){
		FXMLLoader loader = new FXMLLoader();

		if (employeeAllOrdersScene == null) {
			Parent root = getRootByPath("/client/view/employeeAllOrders/EmployeeAllOrders.fxml", loader);

			EmployeeAllOrdersController controller = loader.getController();
			controller.init(this, vmf);
			employeeAllOrdersScene = new Scene(root);
		}

		stage.setTitle("All reservations");
		stage.setScene(employeeAllOrdersScene);
	}


}
