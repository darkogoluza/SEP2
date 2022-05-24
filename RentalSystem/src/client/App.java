package client;

import client.core.ClientFactory;
import client.core.ModelFactory;
import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.model.CustomerNotification;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        ClientFactory clientFactory = new ClientFactory();
        ModelFactory modelFactory = new ModelFactory(clientFactory);

        CustomerNotification notification = new CustomerNotification(modelFactory.getModel());
        Thread notificationThread = new Thread(notification);
        notificationThread.start();

        ViewModelFactory vmf = new ViewModelFactory(modelFactory.getModel());
        ViewHandler vh = new ViewHandler(vmf, stage);
        vh.start(modelFactory.getModel().getManageUser());
    }
}
