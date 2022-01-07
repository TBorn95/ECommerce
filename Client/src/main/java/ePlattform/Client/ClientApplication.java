package ePlattform.Client;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import ePlattform.Client.application.JavaFxApplication;
import javafx.application.Application;

@SpringBootApplication
public class ClientApplication {

	public static void main(String[] args) {
		Application.launch(JavaFxApplication.class, args);
	}
}
