package client.core;

import client.model.user.ManageUser;
import client.view.CustomerBasketView.CustomerBasketViewController;
import client.view.CustomerSingleOrderView.SingleOrderViewController;
import client.view.EmployeeOrderDetails.EmployeeOrderDetailsController;
import client.view.LoginView.LoginViewController;
import client.view.administratorView.AdministratorViewController;
import client.view.customerAllEquipment.CustomerAllEquipmentViewController;
import client.view.employeeAllOrders.EmployeeAllOrdersController;
import client.view.registryView.RegistryViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import shared.objects.user.User;
import shared.objects.user.UserRole;

import java.io.IOException;

/**
 * ViewHandler
 */
public class ViewHandler
{
    private Stage stage;
    private ViewModelFactory vmf;
    private Scene administratorScene;
    private Scene customerAllEquipmentScene;
    private Scene customerBasket;
	private Scene employeeAllOrdersScene;
	private Scene employeeOrderDetailsScene;
	private Scene singleOrderView;
	private Scene registryScene;
  private Scene loginScene;


    /**
     * Constructor assigning ViewModelFactory and Stage
     * @param vmf ViewModelFactory
     * @param stage Stage
     */
    public ViewHandler(ViewModelFactory vmf, Stage stage){
        this.vmf = vmf;
        this.stage = stage;
    }

	/**
	 * Open selected view
	 * This is what is opened when we launch an application
	 */
    public void start(ManageUser manageUser){
		//customer
//		manageUser.login("xoxo", "123456");
//		//admin
//		manageUser.login("admin", "123456");
//		//employee
//		manageUser.login("employee", "123456");
//		System.out.println(manageUser.getLoggedUser());
        openLoginView();

//		if (manageUser.getLoggedUser() == null)
//			openRegistryView();
//		else if (manageUser.getLoggedUser().getRole().equals(UserRole.customer))
//        	openCustomerAllEquipmentView();
//		else if (manageUser.getLoggedUser().getRole().equals(UserRole.employee))
			//openEmployeeView();
//		else if (manageUser.getLoggedUser().getRole().equals(UserRole.admin))
//        	openAdministratorView();

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



    /**
     * Open window with customer basket
     */
    public void openCustomerBasket(){
        FXMLLoader loader = new FXMLLoader();
        if(customerBasket == null){

            Parent root = getRootByPath("/client/view/CustomerBasketView/Basketview.fxml", loader);
            CustomerBasketViewController controller = loader.getController();
            controller.init(this,vmf);
            customerBasket = new Scene(root);
        }

        stage.setTitle("Customer Basket");
        stage.setScene(customerBasket);
    }

	/**
	 * open window with all reservations
	 */
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

	/**
	 * open reservation details window
	 */
	public void openEmployeeOrderDetailsView(int id){
		FXMLLoader loader = new FXMLLoader();

		if (employeeOrderDetailsScene == null) {
			Parent root = getRootByPath("/client/view/EmployeeOrderDetails/EmployeeOrderDetails.fxml", loader);

			EmployeeOrderDetailsController controller = loader.getController();
			controller.init(this, vmf, id);
			employeeOrderDetailsScene = new Scene(root);
		}

		stage.setTitle("All reservations");
		stage.setScene(employeeOrderDetailsScene);
	}

  public void openSingleOrderView(){
    FXMLLoader loader = new FXMLLoader();

    if (singleOrderView == null) {
      Parent root = getRootByPath("/client/view/CustomerSingleOrderView/SingleOrderView.fxml", loader);

      SingleOrderViewController controller = loader.getController();
      //TODO change the ID later
      controller.init(this, vmf, 0);
      singleOrderView = new Scene(root);
    }

    stage.setTitle("Single Order");
    stage.setScene(singleOrderView);
  }

    /**
     * open registry window
     */
    public void openRegistryView(){
        FXMLLoader loader = new FXMLLoader();

        if (registryScene == null) {
            Parent root = getRootByPath("/client/view/registryView/RegistryView.fxml", loader);

            RegistryViewController controller = loader.getController();
            controller.init(this, vmf);
            registryScene = new Scene(root);
        }

        stage.setTitle("Create Account");
        stage.setScene(registryScene);
    }

  public void openLoginView(){
    FXMLLoader loader = new FXMLLoader();

    if (loginScene == null) {
      Parent root = getRootByPath("/client/view/LoginView/LoginView.fxml", loader);

      LoginViewController controller = loader.getController();
      controller.init(this, vmf);
      loginScene = new Scene(root);
    }

    stage.setTitle("Login to Account");
    stage.setScene(loginScene);
  }
}
