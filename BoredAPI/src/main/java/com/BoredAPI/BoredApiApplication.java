package com.BoredAPI;

import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Gets a random activity from the BoredAPI
 *
 * @author Khawaja Rohan Waheed csc 340
 *
 */
@SpringBootApplication
public class BoredApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BoredApiApplication.class, args);
    }

    @GetMapping("/activity")
    public Object getActivity() {
        try {
            String url = "https://www.boredapi.com/api/activity";
            RestTemplate restTemplate = new RestTemplate();
            ObjectMapper mapper = new ObjectMapper();

            String jsonResponse = restTemplate.getForObject(url, String.class);
            JsonNode root = mapper.readTree(jsonResponse);

            // Print the whole response to console.
            System.out.println(root);

            // Parse out the most important info from the response.
            String activity = root.get("activity").asText();
            System.out.println("Activity: " + activity);

            return root;

        } catch (JsonProcessingException ex) {
            Logger.getLogger(BoredApiApplication.class.getName()).log(Level.SEVERE, null, ex);
            return "error in activity";
        }
    }
}
