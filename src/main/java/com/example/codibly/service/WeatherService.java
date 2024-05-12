package com.example.codibly.service;

import com.example.codibly.model.Dto.OpenMeteoResponse;
import com.example.codibly.model.Dto.WeatherResponse;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class WeatherService {

    private static final String OPEN_METEO_API_URL = "https://api.open-meteo.com/v1/forecast?latitude={latitude}&longitude={longitude}&daily=weather_code,temperature_2m_max,temperature_2m_min,sunshine_duration";
    private static final double INSTALLATION_POWER = 2.5;
    private static final double PANEL_EFFICIENCY = 0.2;

    private final RestTemplate restTemplate;

    public WeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public WeatherResponse getWeatherData(double latitude, double longitude) {
        OpenMeteoResponse response = restTemplate.getForObject(OPEN_METEO_API_URL, OpenMeteoResponse.class, latitude, longitude);


        WeatherResponse weatherResponse = new WeatherResponse();
        weatherResponse.setDate(response.getDaily().getTime().get(0)); // get the first daily time
        weatherResponse.setWeatherCode(response.getDaily().getWeather_code().get(0)); // get the first daily weather code
        weatherResponse.setMinTemp(response.getDaily().getTemperature_2m_min().get(0)); // get the first daily min temperature
        weatherResponse.setMaxTemp(response.getDaily().getTemperature_2m_max().get(0)); // get the first daily max temperature
        weatherResponse.setEnergyProduced(calculateEnergy(response.getDaily().getSunshine_duration().get(0))); // calculate energy produced


        return weatherResponse;
    }

    private double calculateEnergy(double sunshineDuration) {
        return INSTALLATION_POWER * (sunshineDuration / 3600) * PANEL_EFFICIENCY; // convert sunshineDuration from seconds to hours
    }
}