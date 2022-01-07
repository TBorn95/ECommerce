package ePlattform.server.websocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.google.gson.JsonObject;



@Component
public class SocketHandler extends TextWebSocketHandler{
	
	private List<WebSocketSession> sessions = new ArrayList<>();
	private MessageHandler msgHandler;
	
	private SocketHandler(){}
	
	
	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
		System.out.println(message.getPayload());
		JsonObject response = msgHandler.handleMessage(message.getPayload(), session);
		session.sendMessage(new TextMessage(response.toString()));
	}
	
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) {
		sessions.add(session);
	}
	
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception{
		session.close();
		sessions.remove(session);
	}
	

	public List<WebSocketSession> getSessions() {
		return sessions;
	}

	
	public void setSessions(List<WebSocketSession> sessions) {
		this.sessions = sessions;
	}

	
	public MessageHandler getMsgHandler() {
		return msgHandler;
	}
	
	
	@Autowired
	public void setMsgHandler(MessageHandler msgHandler) {
		this.msgHandler = msgHandler;
	}	
	
}
