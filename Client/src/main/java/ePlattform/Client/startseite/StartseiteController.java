package ePlattform.Client.startseite;

import ePlattform.Client.produktverkauf.PVController;
import org.springframework.stereotype.Controller;

import ePlattform.Client.connection.SocketEndpoint;
import ePlattform.Client.helperClasses.WindowOpener;
import ePlattform.Client.login.LoggedInUser;
import ePlattform.Client.login.LoginController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;

@Controller
@FxmlView("StartseiteView.fxml")
public class StartseiteController {
	
	@FXML
	private Button logoutBtn;
	@FXML
	private Button verkaufBtn;
	@FXML
	private Button profilBtn;
	private FxWeaver weaver;
	private SocketEndpoint socket;
		
	
	public StartseiteController(FxWeaver weaver, SocketEndpoint socket) {
		super();
		this.weaver = weaver;
		this.socket = socket;
	}

	
	@FXML
	public void logout() {
		Stage currentStage = (Stage) logoutBtn.getScene().getWindow();
		currentStage.close();
		WindowOpener.openWindow(LoginController.class);
		socket.messageToServer("logout", null, LoggedInUser.getNutzer().getBenutzername());
	}

	@FXML
	public void openVerkauf(){
		Stage currentStage = (Stage) logoutBtn.getScene().getWindow();
		currentStage.close();
		WindowOpener.openWindow(PVController.class);
	}
	
	@FXML
	public void openProfil() {
	//	WindowOpener.openWindow(ProfilController.class);
	}

	
}
