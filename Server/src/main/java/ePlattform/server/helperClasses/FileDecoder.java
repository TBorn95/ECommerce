package ePlattform.server.helperClasses;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

//import javafx.stage.Stage;
@Service
public class FileDecoder {
	
	byte[] encodedByteFile;
	byte[] decodedByteFile;
	String base64String;
	String title;
	String  path;
	static String username;
	@Value("${picture.path}")
	private String filePath;

	public FileDecoder() {	
		
	}
	
//	public void encode(String title) {
//		FileChooser fileChooser = new FileChooser();
//		fileChooser.setTitle(title);
//		File file = fileChooser.showOpenDialog(new Stage());
//		if(file == null) return;		
//		try {
//			encodedByteFile = Files.readAllBytes(file.toPath());
//			Base64.Encoder encoder = Base64.getEncoder().withoutPadding();
//			base64String = encoder.encodeToString(encodedByteFile);
//	}
//		catch (IOException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//}
	public void decode(String base64string, String path) throws IOException {
		this.path = path;
		File file = new File(path);
		file.getParentFile().mkdirs();
		decodedByteFile = Base64.getMimeDecoder().decode(base64string);
		Files.write(new File(path).toPath(), decodedByteFile);
	}
	
	
	public String ReadFromLocation(String location) throws IOException {
		encodedByteFile = Files.readAllBytes(Paths.get(location));
		Base64.Encoder encoder = Base64.getEncoder().withoutPadding();
		String base64String = encoder.encodeToString(encodedByteFile);
		return base64String;
	}
	
	
	public  String storePicToDisk(String bildString,String username) {
		FileDecoder.username = username;
		FileDecoder decoder = new FileDecoder();
		if(bildString !=null) {
			try {
				filePath = filePath + username  + "/Profilbild.jpg";
				decoder.decode(bildString, filePath);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				return null;
			}
			return filePath;
		}
		return null;
	}
	
	
	public byte[] getEncodedByteFile() {
		return encodedByteFile;
	}
	

	public void setEncodedByteFile(byte[] encodedByteFile) {
		this.encodedByteFile = encodedByteFile;
	}
	

	public byte[] getDecodedByteFile() {
		return decodedByteFile;
	}

	
	public void setDecodedByteFile(byte[] decodedByteFile) {
		this.decodedByteFile = decodedByteFile;
	}

	
	public String getBase64String() {
		return base64String;
	}
	

	public void setBase64String(String base64String) {
		this.base64String = base64String;
	}

	
	public String getTitle() {
		return title;
	}
	

	public void setTitle(String title) {
		this.title = title;
	}
	

	public String getPath() {
		return path;
	}

	
	public void setPath(String path) {
		this.path = path;
	}
	

	public String getFilePath() {
		return filePath;
	}
	

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

}