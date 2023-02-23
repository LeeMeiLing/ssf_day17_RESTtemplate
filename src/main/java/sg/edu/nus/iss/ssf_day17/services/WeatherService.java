package sg.edu.nus.iss.ssf_day17.services;


import java.io.StringReader;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import sg.edu.nus.iss.ssf_day17.models.Weather;

@Service
public class WeatherService {
    
    public final String URL = "https://api.openweathermap.org/data/2.5/weather";
        //private static String API_KEY = "NEVER PUT SENSITIVE INFO IN SOURCE CODE";

    @Value("${weathermap.key}") 
    private String apiKey;

    // this is used for POST getWeather() method
    public String getWeather(String city){

        //https://api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}
        String url = UriComponentsBuilder.fromUriString(URL)
            .queryParam("q", city)
            .queryParam("appid", apiKey)
            .toUriString();

        System.out.printf("NEVER DO THIS!! URL: %s\n",url); // DO NOT PRINT SENSITIVE INFO

        RequestEntity<Void> req = RequestEntity.get(url).accept(MediaType.APPLICATION_JSON).build();

        RestTemplate template = new RestTemplate();

        ResponseEntity<String> resp = null;
        String payload;
        Integer statusCode;

        try{
            resp = template.exchange(req, String.class);
            payload = resp.getBody();
            statusCode = resp.getStatusCode().value();
        }catch(HttpClientErrorException ex){
            payload = ex.getResponseBodyAsString();
            statusCode = ex.getStatusCode().value();
        }

         System.out.printf("Status code: %d\n",statusCode);
         System.out.printf("Payload: %s\n",payload);

         return payload;
    }

    
}
