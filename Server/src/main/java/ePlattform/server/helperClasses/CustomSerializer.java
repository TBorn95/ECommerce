package ePlattform.server.helperClasses;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

//Quelle: https://stackoverflow.com/questions/3629596/deserializing-an-abstract-class-in-gson
public class CustomSerializer implements JsonSerializer<Object>, JsonDeserializer<Object> {
	
	private static final String CLASS_KEY = "CLASS_KEY";

	@Override
	public Object deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		JsonObject object = json.getAsJsonObject();
		String className = object.get(CLASS_KEY).getAsString();
		
		try {
			Class<?> clazz = Class.forName(className);
			System.out.println(clazz.getSimpleName());
			return new Gson().fromJson(json, clazz);
			
		}catch (ClassNotFoundException e) {
			throw new JsonParseException(e);
		}
	}

	@Override
	public JsonElement serialize(Object src, Type typeOfSrc, JsonSerializationContext context) {
		JsonObject object = new JsonParser().parse(new Gson().toJson(src, src.getClass())).getAsJsonObject();
		object.addProperty(CLASS_KEY, "ePlattform.Client.domainObjects." + src.getClass().getSimpleName());
		return object;
	}

}
