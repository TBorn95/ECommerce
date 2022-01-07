package ePlattform.Client.helperClasses;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FileEncoder {
	
	public static byte[] byteFile;
	public static String base64String;
	public static File selectedFile;


	
	public static void encode(String title) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle(title);
		selectedFile = fileChooser.showOpenDialog(new Stage());
		if(selectedFile == null) return;		
		try {
			byteFile = Files.readAllBytes(selectedFile.toPath());
			Base64.Encoder encoder = Base64.getEncoder().withoutPadding();
			base64String = encoder.encodeToString(byteFile);
	}
		catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
	}
	public byte[] getByteFile() {
		return byteFile;
	}
	public String getBase64String() {
		return base64String;
	}
	
}