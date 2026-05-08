package com.wheretowatch.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;

@Service
public class TmdbApiClient {

    private final RestClient restClient;
    private final String apiKey;

    public TmdbApiClient(@Value("${tmdb.api.base-url}") String baseUrl,
                         @Value("${tmdb.api.key}") String apiKey) {
        this.apiKey = apiKey;
        this.restClient = RestClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    public Map<String, Object> searchMovies(String query) {
        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/search/movie")
                        .queryParam("query", query)
                        .queryParam("api_key", apiKey)
                        .build())
                .retrieve()
                .body(Map.class);
    }

    public Map<String, Object> getWatchProviders(Integer movieId) {
        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/movie/{movie_id}/watch/providers")
                        .queryParam("api_key", apiKey)
                        .build(movieId))
                .retrieve()
                .body(Map.class);
    }
}
