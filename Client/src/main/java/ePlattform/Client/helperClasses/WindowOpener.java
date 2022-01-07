package ePlattform.Client.helperClasses;


import ePlattform.Client.application.AppContext;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;




public class WindowOpener {

	static FxWeaver weaver = (FxWeaver)AppContext.getApplicationContext().getBean(FxWeaver.class);
	
	public static void openWindow(Class<?> clazz) {
		Parent root = weaver.loadView(clazz);		
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
	}
}
