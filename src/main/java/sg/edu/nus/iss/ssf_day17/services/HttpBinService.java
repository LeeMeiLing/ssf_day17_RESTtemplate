package sg.edu.nus.iss.ssf_day17.services;

import java.io.StringReader;

import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
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
        
        // Make the request, the payload of the response will be a string
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


    public void post(String name, String email){

        MultiValueMap<String, String> form  = new LinkedMultiValueMap<>();
        form.set("name",name);
        form.set("email",email);

        RequestEntity< MultiValueMap<String, String> > req = RequestEntity
            .post("http://httpbin.org/post")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .accept(MediaType.APPLICATION_JSON)
            //.header("Accept", "application/json")
            //.header("Content-Type", "application/x-www-form-urlencoded")
            .body(form, MultiValueMap.class);

        RestTemplate template = new RestTemplate();

        ResponseEntity<String> resp = template.exchange(req, String.class);

        // Check the status code
        System.out.printf("Status code: %s\n",resp.getStatusCode());

        // Read the payload 
        String payload = resp.getBody();
        System.out.printf("Payload: %s\n",payload);

    }

    public void postAsJson(String name, String email){

        JsonObject json= Json.createObjectBuilder()
            .add("name",name)
            .add("email",email)
            .build();

        RequestEntity< String > req = RequestEntity
        .post("http://httpbin.org/post")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .body(json.toString(), String.class);


        // Make the request
        RestTemplate template = new RestTemplate();

        ResponseEntity<String> resp = template.exchange(req, String.class);

        // Check the status code
        System.out.printf("Status code: %s\n",resp.getStatusCode());

        // Read the payload 
        String payload = resp.getBody();
        System.out.printf("Payload: %s\n",payload);

        
    }
}
