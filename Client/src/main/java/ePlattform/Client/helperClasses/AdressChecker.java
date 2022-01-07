package ePlattform.Client.helperClasses;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONObject;

public class AdressChecker {
	
	private static JSONObject checkedAdress = new JSONObject();
	private static JSONArray responseArray;
	private static String hausnummer;

	public static boolean checkAdress(String straße, String hausnummer, String plz, String stadt, String land) throws IOException, InterruptedException {	
		AdressChecker.hausnummer = hausnummer;
		HttpClient httpClient = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().
				uri(URI.create("https://nominatim.openstreetmap.org/search/" + straße +"%20" +hausnummer +"%20" + plz +"%20" + 
						stadt +"%20" + land +"?format=json&addressdetails=1&limit=1&polygon_svg=1")).
				GET().build();
		System.out.println("https://nominatim.openstreetmap.org/search/" + straße +"%20" +hausnummer +"%20" + plz +"%20" + 
						stadt +"%20" + land +"?format=json&addressdetails=1&limit=1&polygon_svg=1");
		HttpResponse<String> response;
		response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
		responseArray = new JSONArray(response.body());
			if(responseArray.length() == 0) {
				return false;
			}else {
				return true;
			}
	}

	public static JSONObject getCheckedAdress() {
		JSONObject rObject = responseArray.getJSONObject(0);		
		checkedAdress = new JSONObject();
		checkedAdress.put("lat", rObject.get("lat"));
		checkedAdress.put("long", rObject.get("lon"));
		checkedAdress.put("Strasse", rObject.getJSONObject("address").get("road"));
		checkedAdress.put("Stadt", rObject.getJSONObject("address").get("city"));
		checkedAdress.put("PLZ", rObject.getJSONObject("address").get("postcode"));
		checkedAdress.put("Land", rObject.getJSONObject("address").get("country"));
		checkedAdress.put("Hausnummer", hausnummer);
		return checkedAdress;
	}
	

}
