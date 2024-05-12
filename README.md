# Weather-App

## Description
This application provides an endpoint that accepts latitude and longitude as parameters. These parameters are used to fetch current weather information for the specified location. The application integrates with the external API [Open-Meteo](https://open-meteo.com) to fetch the weather data.

Upon receiving the weather data, the application calculates the energy that can be generated using a photovoltaic installation. The formula used for this calculation is:

`Generated energy[kWh] = Installation power[kW] x Exposure time[h] x Panel efficiency`

- Exposure time for sunlight (s) - this information is fetched from the external API
- Photovoltaic installation power = 2.5 kW
- Panel efficiency = 0.2

This formula provides an approximate amount of energy that a photovoltaic installation can produce in a specified time, considering the power of the photovoltaic installation, its efficiency, and the time it is exposed to sunlight. This is an approximate formula and does not provide exact results. In reality, forecasting production is a bit more complicated, but for our experiment, this is sufficient.

The endpoint created returns data such as:
- Date
- Weather code
- Minimum and maximum temperature during the day
- Estimated value of generated energy in kWh - which needs to be calculated

## Requirements
- Language: Java 
- Integration with external API
    - [Open-Meteo API Documentation](https://open-meteo.com/en/docs)
- Validation of data received from the client
    - Provision of required parameters
    - Correctness of data type
- Handling of errors from the external API
- Calculation of energy production in kWh for each of the seven days
- Data should be returned in JSON format

If you encounter something you are unable to understand, rely on your intuition and possibly leave a comment in the code.