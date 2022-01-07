package ePlattform.Client.application;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import ePlattform.Client.ClientApplication;
import ePlattform.Client.login.LoginController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;


public class JavaFxApplication extends Application{

	private ConfigurableApplicationContext applicationContext;
	
	@Override
	public void start(Stage stage) throws Exception {
		FxWeaver fxWeaver = applicationContext.getBean(FxWeaver.class);
        Parent root = fxWeaver.loadView(LoginController.class);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();   
	}
	
	 @Override
	    public void init() {
	        String[] args = getParameters().getRaw().toArray(new String[0]);

	        this.applicationContext = new SpringApplicationBuilder()
	                .sources(ClientApplication.class)
	                .run(args);
	    }
	 
	@Override
	public void stop() {
		applicationContext.close();
		Platform.exit();
			
	}
}
