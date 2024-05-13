package com.example.codibly.controller;

import com.example.codibly.model.Dto.WeatherResponse;
import com.example.codibly.service.WeatherService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/weather")
    public ResponseEntity<List<WeatherResponse>> getWeatherData(@RequestParam double latitude, @RequestParam double longitude) {
        List<WeatherResponse> weatherData = weatherService.getWeatherData(latitude, longitude);
        return ResponseEntity.ok()
                .header(HttpHeaders.CACHE_CONTROL, "no-cache, no-store, must-revalidate")
                .body(weatherData);
    }
}