package ePlattform.Client.register;

import java.io.ByteArrayInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ePlattform.Client.domainObjects.GewerbeNutzer;
import ePlattform.Client.domainObjects.Nutzer;
import ePlattform.Client.domainObjects.PrivatNutzer;
import ePlattform.Client.helperClasses.FileEncoder;
import ePlattform.Client.helperClasses.WindowOpener;
import ePlattform.Client.login.LoginController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;

@Controller
@FxmlView("RegisterView.fxml")
public class RegisterController {
	
	private RegisterModel model;
	
	@Autowired
	public RegisterController(RegisterModel model) {
		this.model = model;
	}		
	
	@FXML
	public void register() {

		if(!model.checkPassword(this.pwField.getText(), this.pwRepeatField.getText())){
			this.kontoLabel.setText("Passwörter erfüllen nicht alle Kriterien oder sind nicht identisch");
			return;
		}
		if(!model.validateAdress(this.strField.getText(), this.stadtField.getText(), this.plzField.getText(), this.hausNrField.getText(), this.landField.getText())) {
			this.adressLabel.setText("Ungültige Adresse");
			return;
		}
		if(!model.getPersonalData(this.vornameField.getText(), this.nachnameField.getText(), this.emailField.getText())) {
			this.personLabel.setText("Ungültige Email Adresse oder Name");
			return;
		}
		Nutzer newNutzer;
		if(gewerbeRadio.isSelected()) {
			if(this.gewerbenameField.getText().equals("")) {
				this.fail("Bitte geben Sie einen Gewerbenamen an");
				return;
			}
			newNutzer = new GewerbeNutzer(this.vornameField.getText(), this.nachnameField.getText(), this.benutzernameField.getText(), this.emailField.getText(),
					this.pwField.getText(), this.gewerbenameField.getText());
		}else {
			newNutzer = new PrivatNutzer(this.benutzernameField.getText(), this.vornameField.getText(), this.nachnameField.getText(), this.emailField.getText(), this.pwField.getText());
		}
		if(model.register(newNutzer) == true) {
			this.success();
		}else {
			this.fail("Registrierung nicht möglich");
		}
	}
	
	@FXML
	public void selectPic() {
		FileEncoder.encode("Bitte wählen Sie ihr Profilbild aus");
		System.out.println(FileEncoder.selectedFile.toString());
		Image image = new Image(new ByteArrayInputStream(FileEncoder.byteFile));
		this.profilbildView.setImage(image);
		this.model.setBase64Bild(FileEncoder.base64String);
	}
	
	@FXML
	public void back() {
		Stage currentStage = (Stage) kontoLabel.getScene().getWindow();
		currentStage.close();
		WindowOpener.openWindow(LoginController.class);
	}
	
	@FXML
	public void toggle(ActionEvent e) {
		RadioButton clickedButton = (RadioButton)e.getSource();
			if(clickedButton.equals(this.privatRadio)) {
				gewerbeRadio.setSelected(false);
				model.setGewerbe(false);
				this.gewerbenameField.setVisible(false);
			}else {
				privatRadio.setSelected(false);
				this.gewerbenameField.setVisible(true);
				model.setGewerbe(true);
			}
	}
	
	public void success() {
		Dialog dialog = new Dialog();
		dialog.getDialogPane().getButtonTypes().add(new ButtonType("OK", ButtonData.CANCEL_CLOSE));
		dialog.setTitle("Registrierung erfolgreich");
		dialog.setContentText("Glückwunsch, Sie haben sich erfolgreich registriert und können sich nun einloggen");
		dialog.showAndWait();
		back();
	}
	
	public void fail(String reason) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Registrierung fehlgeschlagen");
		alert.setContentText(reason);
		alert.showAndWait();
	}
	
	@FXML
	private Label kontoLabel;
	@FXML
	private Label personLabel;
	@FXML
	private Label adressLabel;
	@FXML
	private ImageView profilbildView;
	@FXML
	private RadioButton gewerbeRadio;
	@FXML
	private RadioButton privatRadio;
	@FXML
	private Button registerBtn;
	@FXML
	private Button backBtn;
	@FXML
	private TextField gewerbenameField;
	@FXML
	private TextField vornameField;
	@FXML
	private TextField nachnameField;
	@FXML
	private TextField emailField;
	@FXML
	private TextField strField;
	@FXML
	private TextField stadtField;
	@FXML
	private TextField plzField;
	@FXML
	private TextField hausNrField;
	@FXML
	private TextField landField;
	@FXML
	private TextField benutzernameField;
	@FXML
	private PasswordField pwField;
	@FXML
	private PasswordField pwRepeatField;	
		
	
}
