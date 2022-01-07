package ePlattform.server.services;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import ePlattform.server.helperClasses.Tuple;
import ePlattform.server.views.ServerController;

@Service
@EnableAsync
public class BroadcastService {
	
	private ServerController serverController;	
	private Map<String,Tuple<WebSocketSession, Timestamp>> LoggedInUsers = new HashMap<>();	
	
	public BroadcastService(ServerController serverController) {
		super();
		this.serverController = serverController;
	}
	
	
	@Async
	public void sendPings() {
		while(true) {
			
			try {
				Thread.sleep(15000);
			}catch(Exception e) {
				e.printStackTrace();
			}
			List<String> deadConnections = new ArrayList<>();
			System.out.println("Sending pings");
			JSONObject jobject = new JSONObject();
			jobject.put("keyword", "ping");
			//https://stackoverflow.com/questions/6092642/how-to-remove-a-key-from-hashmap-while-iterating-over-it/6092681 ausprobieren
			LoggedInUsers.forEach((k,v) -> {
				try {
					if(v.item1.isOpen()) v.item1.sendMessage(new TextMessage(jobject.toString()));
				} catch (IOException e) {
					e.printStackTrace();
				}
			LocalDateTime deadline = LocalDateTime.now().minusSeconds(20);
			if(v.item2.before(Timestamp.valueOf(deadline))) {
				try {
					v.item1.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				deadConnections.add(k);
			}});
			
			for(String benutzer : deadConnections) {
				this.removeLoggedInUser(benutzer);
			}
		}
	}
	
	
	public void setPong(String benutzername,WebSocketSession session) {
		LoggedInUsers.put(benutzername, new Tuple<WebSocketSession, Timestamp>(session, new Timestamp(System.currentTimeMillis())));
	}
	
			
	public void addLoggedInUser(String benutzername, WebSocketSession session) {
		this.LoggedInUsers.put(benutzername, new Tuple<WebSocketSession, Timestamp>(session, new Timestamp(System.currentTimeMillis())));
		serverController.addLoggedUser(benutzername);
	}

	
	public Map<String, Tuple<WebSocketSession, Timestamp>> getLoggedInUsers() {
		return LoggedInUsers;
	}

	
	public void setLoggedInUsers(Map<String, Tuple<WebSocketSession, Timestamp>> loggedInUsers) {
		LoggedInUsers = loggedInUsers;
	}
	

	public void removeLoggedInUser(String benutzername) {
		this.LoggedInUsers.remove(benutzername);
		serverController.removeLoggedUser(benutzername);		
	}
	
}
