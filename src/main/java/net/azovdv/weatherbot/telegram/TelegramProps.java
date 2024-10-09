package net.azovdv.weatherbot.telegram;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.management.Query;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "telegram")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TelegramProps {
    String url;
    String token;
    String name;
}
