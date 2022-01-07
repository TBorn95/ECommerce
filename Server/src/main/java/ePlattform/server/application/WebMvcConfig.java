package ePlattform.server.application;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ePlattform.server.domainObjects.GewerbeNutzer;
import ePlattform.server.domainObjects.Nutzer;
import ePlattform.server.domainObjects.PrivatNutzer;
import ePlattform.server.helperClasses.CustomSerializer;

@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer{

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(createGsonHttpMessageConverter());
    }
    
 
    private GsonHttpMessageConverter createGsonHttpMessageConverter() {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
        		.registerTypeAdapter(Nutzer.class, new CustomSerializer()).create();

        GsonHttpMessageConverter gsonConverter = new GsonHttpMessageConverter();
        gsonConverter.setGson(gson);

        return gsonConverter;
    }
    
}
