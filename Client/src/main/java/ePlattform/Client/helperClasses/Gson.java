package ePlattform.Client.helperClasses;

import com.google.gson.GsonBuilder;
import ePlattform.Client.domainObjects.Nutzer;

public class Gson {

    public static final com.google.gson.Gson gson = new GsonBuilder()
            .registerTypeAdapter(Nutzer.class, new CustomSerializer()).create();


    private Gson(){}
}
