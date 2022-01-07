package ePlattform.server.websocket;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import ePlattform.server.domainObjects.Nutzer;
import ePlattform.server.helperClasses.CustomSerializer;
import ePlattform.server.services.BroadcastService;
import ePlattform.server.services.NutzerService;


@Service
public class MessageHandler {
	
	private NutzerService nutzerService;
	private BroadcastService broadcastService;
	Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
			.registerTypeAdapter(Nutzer.class, new CustomSerializer()).create();
	
	
	@Autowired
	public MessageHandler(NutzerService nutzerService,BroadcastService broadcastService) {
		this.nutzerService = nutzerService;
		this.broadcastService = broadcastService;
		this.broadcastService.sendPings();
	}
		
	
	public JsonObject handleMessage(String message, WebSocketSession session) throws JsonSyntaxException, IOException {
		JsonObject clientMessage =  JsonParser.parseString(message).getAsJsonObject();
		JsonObject response = new JsonObject();
		String keyword = clientMessage.get("keyword").getAsString();
		
		switch(keyword) {	
		
			case "connect":
				broadcastService.addLoggedInUser(clientMessage.get("absender").getAsString(), session);
				return this.msgToClient("OK", "Successfully connected", "connect");	
				
			case "logout":
				broadcastService.removeLoggedInUser(clientMessage.get("absender").getAsString());
				return this.msgToClient("OK", "Successfully disconnected", "logout");
				
			case "profilAendern":
				if(nutzerService.updateNutzer(gson.fromJson(clientMessage.get("nutzer"), Nutzer.class))){
					return this.msgToClient("OK", "Profil erfolgreich geändert!", "profilAendern");			
				}else {
					return this.msgToClient("ERROR","Fehler! Profil wurde nicht aktualisiert", "profilAendern");
				}	
				
			case "pong":
				broadcastService.setPong(clientMessage.get("absender").getAsString(), session);
				return this.msgToClient("OK", "Pong recieved", "pong");
				
		}		
		return response;
	}		

	
	public JsonObject msgToClient(String status, String content,String keyword) {
		JsonObject message = new JsonObject();
		message.addProperty("keyword", "login");
		message.addProperty("status", status);
		message.addProperty("content", content);
		return message;
	}

	
	public BroadcastService getBroadcastService() {
		return broadcastService;
	}

	
	public void setBroadcastService(BroadcastService broadcastService) {
		this.broadcastService = broadcastService;
	}	
	
}
