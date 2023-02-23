package sg.edu.nus.iss.ssf_day17.services;

import java.io.StringReader;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

@Service
public class HttpBinService {

    public void get(String name, String email){

        // Create the URL with the properly encoded query string
        // must use UriComponentsBuilder to construct the URL
        String url = UriComponentsBuilder.fromUriString("http://httpbin.org/get")
            .queryParam("name", name)
            .queryParam("email", email)
            .toUriString();

        System.out.printf("URL: %s\n",url);

        // GET /get?name=<name>&email=<email>
        // !!! always type in the full URL manually , dont use "/name=%s".formatted(name)
        RequestEntity<Void> req = RequestEntity.get(url).build();

        RestTemplate template = new RestTemplate();

        ResponseEntity<String> resp = template.exchange(req, String.class);

         // Check the status code
         System.out.printf("Status code: %s\n",resp.getStatusCode());

         // Read the payload 
         String payload = resp.getBody();
         System.out.printf("Payload: %s\n",payload);

    }
    
    public void get(){

        // Creating a GET /get request //requestentity<void> always void
        RequestEntity<Void> req = RequestEntity.get("http://httpbin.org/get").build();

        // Create a REST template
        RestTemplate template = new RestTemplate();
        
        // Make therequest, the payload of the response will be a string
        ResponseEntity<String> resp = template.exchange(req, String.class);

        // Check the status code
        System.out.printf("Status code: %s\n",resp.getStatusCode());

        // Read the payload 
        String payload = resp.getBody();
        System.out.printf("Payload: %s\n",payload);

        JsonReader reader = Json.createReader(new StringReader(payload));
        JsonObject json = reader.readObject();
        JsonObject headers = json.getJsonObject("headers");
        String traceId = headers.getString("X-Amzn-Trace-Id");
        System.out.printf("X-Amzn-Trace-Id: %s\n",traceId);

    }
}
