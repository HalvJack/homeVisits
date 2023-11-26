package com.example.wizytydomowe.HereApi;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/maptiles")
public class MapTileController {
    private final MapTileService mapTileService;
    private final TokenRequester tokenRequester;

    public MapTileController(MapTileService mapTileService, TokenRequester tokenRequester) {
        this.mapTileService = mapTileService;
        this.tokenRequester = tokenRequester;
    }

    @GetMapping
    public Mono<ResponseEntity<byte[]>> getMapTile() {
        return mapTileService.getMapTile(tokenRequester.getAccessToken())
                .map(body -> ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(body));
    }
}
