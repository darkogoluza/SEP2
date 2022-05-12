package client;

import client.core.ModelFactory;
import client.core.ViewHandler;
import client.core.ViewModelFactory;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        ModelFactory modelFactory = new ModelFactory();
        ViewModelFactory vmf = new ViewModelFactory(modelFactory.getModel());
        ViewHandler vh = new ViewHandler(vmf, stage);
        vh.start();
    }
}
