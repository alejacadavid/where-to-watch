package com.wheretowatch.controller;

import com.wheretowatch.service.TmdbApiClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MovieStreamingController.class)
class MovieStreamingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TmdbApiClient tmdbApiClient;

    @Test
    void searchMovies_ReturnsResults() throws Exception {
        String query = "matrix";
        Map<String, Object> mockResponse = Map.of("results", "some data");

        when(tmdbApiClient.searchMovies(query)).thenReturn(mockResponse);

        mockMvc.perform(get("/api/movies/search")
                .param("query", query))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.results").value("some data"));
    }

    @Test
    void getProviders_ReturnsProviders() throws Exception {
        int movieId = 123;
        Map<String, Object> mockResponse = Map.of("results", "some provider data");

        when(tmdbApiClient.getWatchProviders(movieId)).thenReturn(mockResponse);

        mockMvc.perform(get("/api/movies/{id}/providers", movieId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.results").value("some provider data"));
    }
}
