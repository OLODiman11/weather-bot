package net.azovdv.weatherbot.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import net.azovdv.weatherbot.service.handler.CallbackQueryHandler;
import net.azovdv.weatherbot.service.handler.CommandHandler;
import net.azovdv.weatherbot.service.handler.MessageHandler;
import net.azovdv.weatherbot.telegram.Bot;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UpdateDispatcher {
    CommandHandler commandHandler;
    MessageHandler messageHandler;
    CallbackQueryHandler queryHandler;

    public BotApiMethod<?> distribute(Update update, Bot bot) {
        if (update.hasCallbackQuery()) {
            return queryHandler.answer(update.getCallbackQuery(), bot);
        }
        if(update.hasMessage()) {
            Message message = update.getMessage();
            if(message.hasText()) {
                String text = message.getText();
                if (text.charAt(0) == '/') {
                    return commandHandler.answer(update.getMessage(), bot);
                }
                return messageHandler.answer(update.getMessage(), bot);
            }
        }
        log.warn("Unsupported update type {" + update + "}");
        return null;
    }
}
