package com.example.codibly.model.Dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class OpenMeteoResponse {

    private Daily daily;

    @Setter
    @Getter
    public static class Daily {
        private List<String> time;
        private List<String> weather_code;
        private List<Double> temperature_2m_max;
        private List<Double> temperature_2m_min;
        private List<Double> sunshine_duration;

    }
}