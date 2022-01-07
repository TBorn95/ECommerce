package ePlattform.server;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import ePlattform.server.application.JavaFxApplication;
import javafx.application.Application;

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
		Application.launch(JavaFxApplication.class, args);
	}

}
