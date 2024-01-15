package com.example.wizytydomowe.HereApi;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class MapTileService {
    private final WebClient webClient;

    public MapTileService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://1.base.maps.ls.hereapi.com").build();
    }

    public Mono<byte[]> getMapTile(String accessToken) {
        return webClient.get()
                .uri("https://1.base.maps.ls.hereapi.com/maptile/2.1/maptile/newest/normal.day/13/4400/2686/256/png8")
                .header("Authorization", "Bearer " + accessToken)
                .header("Cache-Control", "no-cache")
                .retrieve()
                .bodyToMono(byte[].class);
    }
}
