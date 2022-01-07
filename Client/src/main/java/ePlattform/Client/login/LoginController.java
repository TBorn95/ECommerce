package ePlattform.Client.login;

import org.springframework.stereotype.Controller;

import com.sun.jdi.connect.Connector;

import ePlattform.Client.connection.SocketEndpoint;
import ePlattform.Client.domainObjects.Nutzer;
import ePlattform.Client.helperClasses.WindowOpener;
import ePlattform.Client.register.RegisterController;
import ePlattform.Client.startseite.StartseiteController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;

@Controller
@FxmlView("LoginView.fxml")
public class LoginController {

	private final FxWeaver fxWeaver;
	private SocketEndpoint socket;
	private LoginModel model;
	
	@FXML
	private TextField usernameField;
	@FXML
	private PasswordField pwField;
	@FXML
	private Button registerBtn;
	@FXML
	private Button passwordBtn;
	@FXML
	private Button loginBtn;
	
	public LoginController(FxWeaver fxWeaver, SocketEndpoint socket,LoginModel model) {
		super();
		this.fxWeaver = fxWeaver;
		this.socket = socket;
		this.model = model;
	}
	
	@FXML
	public void register() {
		Stage currentStage = (Stage) this.loginBtn.getScene().getWindow();
		currentStage.close();
		WindowOpener.openWindow(RegisterController.class);
	}
	
	@FXML
	public void pwVergessen() {
		
	}
	
	@FXML
	public void login() throws Exception {
		Nutzer nutzer = model.login(this.usernameField.getText(), this.pwField.getText());
		if(nutzer != null) {
			WindowOpener.openWindow(StartseiteController.class);
			Stage currentStage = (Stage) this.pwField.getScene().getWindow();
			LoggedInUser.setNutzer(nutzer);
			currentStage.hide();
			socket.messageToServer("connect", null, usernameField.getText());
		}else {
			Dialog dialog = new Dialog();
			dialog.getDialogPane().getButtonTypes().add(new ButtonType("OK", ButtonData.CANCEL_CLOSE));
			dialog.setTitle("Login fehlgeschlagen");
			dialog.setContentText("Benutzername oder Passwort falsch");
			dialog.showAndWait();
		}
	}
	
	
	
	
	
	
}
