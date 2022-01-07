package ePlattform.server.views;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;

@Controller
@FxmlView("ServerView.fxml")
public class ServerController {

	@FXML
	private Button stopBtn;
	@FXML
	private Circle onlineCircle;
	@FXML
	private Label connectionsLbl;
	@FXML
	private Label loggedLbl;
	@FXML
	private ObservableList<String> LoggedInUsers = FXCollections.observableArrayList();
	private ApplicationContext applicationContext;	
	private FxWeaver fxWeaver;	

	
	public ServerController(FxWeaver fxWeaver, ApplicationContext applicationContext) {
		super();
		this.fxWeaver = fxWeaver;
		this.applicationContext = applicationContext;
	}
	
	
	@FXML
	public void initialize() {
		loggedLbl.textProperty().bind(Bindings.size(LoggedInUsers).asString());		
	}
	
	
	@FXML
	public void stop() {
		SpringApplication.exit(applicationContext,() ->0);
	}
	
	
	public void addLoggedUser(String benutzername){
		Platform.runLater(new Runnable() {
			  @Override public void run() {
				LoggedInUsers.add(benutzername);
			  }
			});
	}

	
	public void removeLoggedUser(String benutzername) {
		Platform.runLater(new Runnable() {
			  @Override public void run() {
				LoggedInUsers.remove(benutzername);
			  }
			});// TODO Auto-generated method stub	
	}
		
}
