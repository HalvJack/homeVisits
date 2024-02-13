package com.example.wizytydomowe.HereApi;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
@RestController
@Slf4j
@Getter
public class TripSummary {
    @Value("${here.api.key}") private String apiKey;
    @GetMapping("/getRouteSummary")
    public int getRouteSummary(@RequestParam String origin, @RequestParam String destination) {
        String url = String.format(
                "https://router.hereapi.com/v8/routes?origin=%s&destination=%s&transportMode=car&return=summary&apiKey=%s",
                origin, destination, this.apiKey);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<RouteResponse> response = restTemplate.getForEntity(url, RouteResponse.class);

        return response.getBody().getRoutes()[0].getSections()[0].getTravelSummary().getDuration();
    }
}
