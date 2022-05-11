package client.core;

import client.view.administratorView.AdministratorViewController;
import client.view.customerAllEquipment.CustomerAllEquipmentViewController;
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


    public ViewHandler( ViewModelFactory vmf, Stage stage){
        this.vmf = vmf;
        this.stage = stage;
    }

	/**
	 * Open selected view
	 * This is what is opened when we launch an application
	 */
    public void start(){
<<<<<<< HEAD
//        openAdministratorView();
=======
        //openAdministratorView();
>>>>>>> d0ef1e42fa9b7b70f7835467871ae2673027853b
        openCustomerAllEquipmentView();
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
<<<<<<< HEAD
            Parent root = getRootByPath("client/view/customerAllEquipment/CustomerAllEquipmentView.fxml", loader);
=======
            Parent root = getRootByPath("/client/view/customerAllEquipment/CustomerAllEquipmentView.fxml", loader);
>>>>>>> d0ef1e42fa9b7b70f7835467871ae2673027853b
            CustomerAllEquipmentViewController controller = loader.getController();
            controller.init(this,vmf);
            customerAllEquipmentScene = new Scene(root);
        }

        stage.setTitle("All equipment");
        stage.setScene(customerAllEquipmentScene);
    }

}
