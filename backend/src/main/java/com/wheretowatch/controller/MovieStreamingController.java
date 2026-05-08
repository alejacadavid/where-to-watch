package com.wheretowatch.controller;

import com.wheretowatch.service.TmdbApiClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/movies")
@CrossOrigin(origins = "http://localhost:5173") // Allow React to connect
public class MovieStreamingController {

    private final TmdbApiClient tmdbApiClient;

    public MovieStreamingController(TmdbApiClient tmdbApiClient) {
        this.tmdbApiClient = tmdbApiClient;
    }

    @GetMapping("/search")
    public Map<String, Object> searchMovies(@RequestParam String query) {
        return tmdbApiClient.searchMovies(query);
    }

    @GetMapping("/{id}/providers")
    public Map<String, Object> getProviders(@PathVariable Integer id) {
        return tmdbApiClient.getWatchProviders(id);
    }
}