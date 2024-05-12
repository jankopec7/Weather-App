package com.example.codibly.model.Dto;

import lombok.Data;

@Data
public class WeatherResponse {

    private String date;
    private String weatherCode;
    private Double minTemp;
    private Double maxTemp;
    private Double energyProduced;
}