package net.azovdv.weatherbot.service.handler;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import net.azovdv.weatherbot.model.WeatherResponse;
import net.azovdv.weatherbot.service.WeatherService;
import net.azovdv.weatherbot.telegram.Bot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MessageHandler {

    @Autowired
    WeatherService weatherService;

    public BotApiMethod<?> answer(Message message, Bot bot) {
        String text = message.getText();
        WeatherResponse weatherResponse = weatherService.getWeatherNow(text);
        return SendMessage.builder()
                .chatId(message.getChatId())
                .text("Город: " + text
                        + "\nТемпература: " + weatherResponse.main().temp()
                        + "\nПо ощущениям: " + weatherResponse.main().feels_like()
                        + "\nДавление: " + weatherResponse.main().pressure()
                        + "\nВлажность: " + weatherResponse.main().humidity())
                .build();
    }
}
