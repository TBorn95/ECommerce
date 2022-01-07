package ePlattform.Client.messageHandlers;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class PongHandler implements Handler{
	
	@Override
	public String handle(String befehl) {		
		JSONObject jobject = new JSONObject(befehl);
		JSONObject response = new JSONObject();
		
		if(jobject.getString("keyword").equals("ping")) {
			response.put("keyword", "pong");
			response.put("content", "ping recieved");
			return response.toString();
		}
		return null;
	}

	public PongHandler () {
		super();
	}
		
}
