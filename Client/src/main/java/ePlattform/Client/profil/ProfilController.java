package ePlattform.Client.profil;

import org.springframework.stereotype.Controller;

import ePlattform.Client.login.LoggedInUser;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;

@Controller
@FxmlView("ProfilView.fxml")
public class ProfilController {	

	@FXML
	private Button changeBtn;
	@FXML
	private Button closeBtn;
	@FXML
	private Button picChangeBtn;
	@FXML
	private ImageView imageView;
	@FXML
	private TextField tfVorname;
	@FXML
	private TextField tfBenutzername;
	@FXML
	private TextField tfNachname;
	@FXML
	private TextField tfEmail;
	
	
	@FXML
	public void changeData() {
		if(!changeBtn.getText().equals("Speichern")) {
			changeBtn.setText("Speichern");
			tfVorname.setEditable(true);
			tfNachname.setEditable(true);
			tfEmail.setEditable(true);
		}else {
			changeBtn.setText("Daten ändern...");
			tfVorname.setEditable(false);
			tfNachname.setEditable(false);
			tfEmail.setEditable(false);
			LoggedInUser.getNutzer().setVorname(tfVorname.getText());
			LoggedInUser.getNutzer().setNachname(tfNachname.getText());
			LoggedInUser.getNutzer().setEmail(tfEmail.getText());
		}		
	}
	
	@FXML
	public void changePic() {
		
	}
	
	@FXML
	public void close() {
		Stage currentStage = (Stage) this.closeBtn.getScene().getWindow();
		currentStage.close();
	}
}
