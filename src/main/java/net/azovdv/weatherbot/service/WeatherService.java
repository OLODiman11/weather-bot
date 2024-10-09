package net.azovdv.weatherbot.service;

import lombok.Setter;
import net.azovdv.weatherbot.model.WeatherResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@Setter
public class WeatherService {

    @Value("${weather.api-key}")
    private String API_KEY;

    @Value("${weather.now}")
    private String NOW;

    @Value("${weather.geo}")
    private String GEO;

    private final RestTemplate restTemplate = new RestTemplate();

    public WeatherResponse getWeatherNow(String cityName) {
        String url = UriComponentsBuilder.fromHttpUrl(NOW)
                .queryParam("q", cityName)
                .queryParam("appid", API_KEY)
                .queryParam("units", "metric")
                .queryParam("lang", "ru")
                .toUriString();
        return restTemplate.getForObject(url, WeatherResponse.class);
    }

}
