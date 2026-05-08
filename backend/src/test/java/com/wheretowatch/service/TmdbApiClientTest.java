package com.wheretowatch.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestClient;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TmdbApiClientTest {

    private TmdbApiClient tmdbApiClient;

    @Mock
    private RestClient restClient;
    @Mock
    private RestClient.RequestHeadersUriSpec requestHeadersUriSpec;
    @Mock
    private RestClient.RequestHeadersSpec requestHeadersSpec;
    @Mock
    private RestClient.ResponseSpec responseSpec;

    @BeforeEach
    void setUp() {
        tmdbApiClient = new TmdbApiClient("http://dummy-url.com", "dummy-api-key");
        ReflectionTestUtils.setField(tmdbApiClient, "restClient", restClient);
    }

    @Test
    @SuppressWarnings("unchecked")
    void testGetWatchProviders() {
        // Arrange
        Integer movieId = 123;
        Map<String, Object> mockResponse = new HashMap<>();
        mockResponse.put("id", movieId);

        when(restClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(ArgumentMatchers.<Function<org.springframework.web.util.UriBuilder, java.net.URI>>any()))
                .thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.body(Map.class)).thenReturn(mockResponse);

        // Act
        Map<String, Object> result = tmdbApiClient.getWatchProviders(movieId);

        // Assert
        assertEquals(mockResponse, result);
    }

    @Test
    @SuppressWarnings("unchecked")
    void testSearchMovies() {
        // Arrange
        String query = "matrix";
        Map<String, Object> mockResponse = new HashMap<>();
        mockResponse.put("page", 1);

        when(restClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(ArgumentMatchers.<Function<org.springframework.web.util.UriBuilder, java.net.URI>>any()))
                .thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.body(Map.class)).thenReturn(mockResponse);

        // Act
        Map<String, Object> result = tmdbApiClient.searchMovies(query);

        // Assert
        assertEquals(mockResponse, result);
    }
}
