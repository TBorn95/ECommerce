package ePlattform.Client.connection;
import java.io.IOException;
import java.net.URI;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketAdapter;
import org.eclipse.jetty.websocket.client.WebSocketClient;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ePlattform.Client.domainObjects.GewerbeNutzer;
import ePlattform.Client.domainObjects.PrivatNutzer;
import ePlattform.Client.helperClasses.CustomSerializer;
import ePlattform.Client.messageHandlers.MessageHandler;


@Component
public class SocketEndpoint extends WebSocketAdapter{
	
	private CountDownLatch closureLatch = new CountDownLatch(1);
	private MessageHandler messageHandler;
	WebSocketClient client;
	Session session;
	Gson gson = new GsonBuilder().registerTypeAdapter(PrivatNutzer.class, new CustomSerializer()).
			  registerTypeAdapter(GewerbeNutzer.class, new CustomSerializer()).create();
	

	
	public SocketEndpoint() {
		connectToServer("ws://localhost:8080/server");
	}
	
	public void stop() {
		this.session.close();
		try {
			this.client.stop();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void connectToServer(String targetUri) {
		URI uri = URI.create(targetUri);
		client = new WebSocketClient();
      try {
			client.start();
			 Future<Session> fut = client.connect(this,uri);
			 session = fut.get();          
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
    @Override
    public void onWebSocketConnect(Session sess)
    {
        super.onWebSocketConnect(sess);
        System.out.println("ClientWebSocket Connected: " + sess);
    }

    @Override
    public void onWebSocketText(String message)
    {
        super.onWebSocketText(message);
        System.out.println("Received TEXT message from Sever: " + message);
        String response = messageHandler.handle(message);
        if(response != null) {
        	try {
				this.session.getRemote().sendString(response);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }  
    
	public void messageToServer(String keyword,String content,String absender) {
	JSONObject messageToServer = new JSONObject();
	messageToServer.put("keyword",keyword);
	messageToServer.put("content", content);
	messageToServer.put("absender", absender);
	try {
		System.out.println(messageToServer.toString());
		this.getSession().getRemote().sendString(messageToServer.toString());
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}		
}

    @Override
    public void onWebSocketClose(int statusCode, String reason)
    {
        super.onWebSocketClose(statusCode, reason);
        System.out.println("Socket Closed: [" + statusCode + "] " + reason);
        closureLatch.countDown();
    }

    @Override
    public void onWebSocketError(Throwable cause)
    {
        super.onWebSocketError(cause);
        cause.printStackTrace(System.err);
    }

    public void awaitClosure() throws InterruptedException
    {
        System.out.println("Awaiting closure from remote");
        closureLatch.await();
    }

	public MessageHandler getMessageHandler() {
		return messageHandler;
	}
	@Autowired
	public void setMessageHandler(MessageHandler messageHandler) {
		this.messageHandler = messageHandler;
	}
    
}
