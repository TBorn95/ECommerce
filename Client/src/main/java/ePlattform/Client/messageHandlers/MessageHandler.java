package ePlattform.Client.messageHandlers;


import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ePlattform.Client.login.LoggedInUser;

@Component
public class MessageHandler {

	List<Handler> msgHandlers;
		
	
	public String handle(String msg) {
		if(!msg.contains("keyword")) {
			return null;	
		}
		
		String response;	
		for(Handler handler : msgHandlers) {
			response = handler.handle(msg);
			
			if(response != null) {
				JSONObject object = new JSONObject(response);
				object.put("absender", LoggedInUser.getNutzer().getBenutzername());
				return object.toString();
			}
		}
		return null;	
	}
	
	
	public List<Handler> getMsgHandlers() {
		return msgHandlers;
	}
	
	
	@Autowired
	public void setMsgHandlers(List<Handler> msgHandlers) {
		this.msgHandlers = msgHandlers;
	}

	
}
		
