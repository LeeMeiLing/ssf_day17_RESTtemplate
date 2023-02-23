package sg.edu.nus.iss.ssf_day17.controllers;

import java.io.StringReader;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import sg.edu.nus.iss.ssf_day17.models.Weather;
import sg.edu.nus.iss.ssf_day17.services.WeatherService;

@Controller
@RequestMapping(path = {"/", "/weather"})
public class WeatherController {

    @Autowired
    private WeatherService weatherSvc;
    
    @GetMapping
    public String showForm(){
        return "index";
    }

    // POST is for creating data, use GET
    @PostMapping("/search")
    public String getWeather(@RequestBody MultiValueMap<String, String> form, Model model){

        String city = form.getFirst("city");
        String payload = weatherSvc.getWeather(city);
        
        JsonReader reader = Json.createReader(new StringReader(payload));
        JsonObject json = reader.readObject();
        JsonArray array = json.getJsonArray("weather");
        json = array.getJsonObject(0);
        String main = json.getString("main");
        String description = json.getString("description");

        // System.out.println("Main: " + main);
        // System.out.println("Description: " + description);

        model.addAttribute("city", city);
        model.addAttribute("main", main);
        model.addAttribute("description", description);

        return "index";
    }

}
