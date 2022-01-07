package ePlattform.server.application;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import ePlattform.server.ServerApplication;
import ePlattform.server.views.ServerController;
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
        Parent root = fxWeaver.loadView(ServerController.class);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();   
	}
	
	
	 @Override
	    public void init() {
	        String[] args = getParameters().getRaw().toArray(new String[0]);

	        this.applicationContext = new SpringApplicationBuilder()
	                .sources(ServerApplication.class)
	                .run(args);
	    }
	 
	 
	@Override
	public void stop() {
		applicationContext.close();
		Platform.exit();
			
	}
}
