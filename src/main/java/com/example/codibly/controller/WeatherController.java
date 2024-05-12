package com.example.codibly.controller;

import com.example.codibly.model.Dto.WeatherResponse;
import com.example.codibly.service.WeatherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/weather")
    public ResponseEntity<WeatherResponse> getWeatherData(@RequestParam double latitude, @RequestParam double longitude) {
        WeatherResponse weatherResponse = weatherService.getWeatherData(latitude, longitude);
        return ResponseEntity.ok(weatherResponse);
    }
}