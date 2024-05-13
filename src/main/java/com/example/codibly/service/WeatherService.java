package com.example.codibly.service;

import com.example.codibly.model.Dto.OpenMeteoResponse;
import com.example.codibly.model.Dto.WeatherResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherService {

    private static final String OPEN_METEO_API_URL = "https://api.open-meteo.com/v1/forecast?latitude={latitude}&longitude={longitude}&daily=weather_code,temperature_2m_max,temperature_2m_min,sunshine_duration";
    private static final double INSTALLATION_POWER = 2.5;
    private static final double PANEL_EFFICIENCY = 0.2;

    private final RestTemplate restTemplate;

    public WeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<WeatherResponse> getWeatherData(double latitude, double longitude) {
        OpenMeteoResponse response = restTemplate.getForObject(OPEN_METEO_API_URL, OpenMeteoResponse.class, latitude, longitude);

        List<WeatherResponse> weatherResponses = new ArrayList<>();

        for (int i = 0; i < response.getDaily().getTime().size(); i++) {
            WeatherResponse weatherResponse = new WeatherResponse();
            weatherResponse.setDate(response.getDaily().getTime().get(i));
            weatherResponse.setWeatherCode(response.getDaily().getWeather_code().get(i));
            weatherResponse.setMinTemp(response.getDaily().getTemperature_2m_min().get(i));
            weatherResponse.setMaxTemp(response.getDaily().getTemperature_2m_max().get(i));
            weatherResponse.setEnergyProduced(calculateEnergy(response.getDaily().getSunshine_duration().get(i)));

            weatherResponses.add(weatherResponse);
        }

        return weatherResponses;
    }

    private double calculateEnergy(double sunshineDuration) {
        double energy = INSTALLATION_POWER * (sunshineDuration / 3600) * PANEL_EFFICIENCY;
        energy = Math.round(energy * 100.0) / 100.0; // round to 2 decimal places
        return energy;
    }
}