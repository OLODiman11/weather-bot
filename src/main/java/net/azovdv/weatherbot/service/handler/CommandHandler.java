package net.azovdv.weatherbot.service.handler;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import net.azovdv.weatherbot.telegram.Bot;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommandHandler {
    public BotApiMethod<?> answer(Message message, Bot bot) {
        String text = message.getText();
        return switch(text) {
            case "/start" -> SendMessage.builder()
                        .chatId(message.getChat().getId())
                        .text("Чтобы узнать погоду напишите название города.")
                        .build();
            default -> SendMessage.builder()
                    .chatId(message.getChat().getId())
                    .text("Неизвестная команда.")
                    .build();
        };
    }
}
