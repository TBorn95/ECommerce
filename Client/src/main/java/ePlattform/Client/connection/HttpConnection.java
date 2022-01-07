package ePlattform.Client.connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeUnit;

import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.eclipse.jetty.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ePlattform.Client.domainObjects.Nutzer;
import ePlattform.Client.helperClasses.CustomSerializer;

@Service
public class HttpConnection {
			
	private static CloseableHttpClient client;
	
	private PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
	
	private IdleConnectionMonitorThread monitorThread = new IdleConnectionMonitorThread(cm);
	
	
	private HttpConnection() throws InterruptedException {
		//cm.setMaxTotal(max);
		//cm.setDefaultMaxPerRoute(max);
		client = HttpClients.custom().setConnectionManager(cm)
				.setKeepAliveStrategy(strategy)
				.build();
		monitorThread.start();
	}
	
	
	ConnectionKeepAliveStrategy strategy = new ConnectionKeepAliveStrategy() {
		@Override
		public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
			HeaderElementIterator iterator = new BasicHeaderElementIterator(response.headerIterator(HTTP.CONN_KEEP_ALIVE));
			while(iterator.hasNext()) {
				HeaderElement element = iterator.nextElement();
				String param = element.getName();
				String value = element.getValue();
				if(value != null && param.equalsIgnoreCase("timeout")) {
					return Long.parseLong(value) * 1000;
				}
			}
			return 8000;
		}
	};
	
	
	public static <A> A request(Type type, String url, String content, ContentType contentType, Class<A> a) throws ClientProtocolException, IOException {		
		HttpRequestBase baseRequest = null;		
		switch(type) {
		
		case POST:			
			HttpPost postRequest = new HttpPost(url); 
			try {
				StringEntity entity = new StringEntity(content);
				entity.setContentType(contentType.getString());
				postRequest.setEntity(entity);
				baseRequest = postRequest;
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case GET:
			baseRequest = new HttpGet(url);
			break;
		}
		CloseableHttpResponse response = client.execute(baseRequest);
	    return handleResponse(response, a);
	}
	
	
	public static <A> A handleResponse(CloseableHttpResponse response, Class<A> a) throws IOException {
		A result = null;
		try {
			if(response.getStatusLine().getStatusCode() == HttpStatus.OK_200) {
				HttpEntity entity = response.getEntity();
				if(entity != null) {
				InputStream instream = entity.getContent();
				try {
					Gson gson = new GsonBuilder().registerTypeAdapter(Nutzer.class, new CustomSerializer()).create();
					BufferedReader reader = new BufferedReader(new InputStreamReader(instream));
					result = gson.fromJson(reader, a);
				}finally {
					instream.close();
				}
				}
			}else {
				throw new IOException("Http Error:" + response.getStatusLine().getStatusCode());
			}
			
		}finally {
			response.close();
		}	
		return result;
	}

	
	public void shutdown() throws IOException {
		client.close();
		cm.close();
	}
	
	
	private class IdleConnectionMonitorThread extends Thread {		
	    private final HttpClientConnectionManager connMgr;
	    private volatile boolean shutdown;
	    private IdleConnectionMonitorThread(
	      PoolingHttpClientConnectionManager connMgr) {
	        super();
	        this.connMgr = connMgr;
	    }
	    @Override
	    public void run() {
	        try {
	            while (!shutdown) {
	                synchronized (this) {
	                    wait(1000);
	                    connMgr.closeExpiredConnections();
	                    connMgr.closeIdleConnections(30, TimeUnit.SECONDS);
	                }
	            }
	        } catch (InterruptedException ex) {
	            shutdown();
	        }
	    }
	    public void shutdown() {
	        shutdown = true;
	        synchronized (this) {
	            notifyAll();
	        }
	    }
	}
	
	
	public enum Type{
		POST,
		GET;		
	}
	
	
	public enum ContentType{
		JSON("application/json"),
		TEXT("text/plain");
		
		private final String contentType;
		
		ContentType(String contentType){
			this.contentType = contentType;
		}
		
		public String getString() {
			return this.contentType;
		}
	}
	
	
}
