package com.test.javatelegrambot.JavaBot.service;

import com.test.javatelegrambot.JavaBot.config.TelegramClientConfig;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.longpolling.interfaces.LongPollingUpdateConsumer;
import org.telegram.telegrambots.longpolling.starter.SpringLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@Service
public class Bot implements SpringLongPollingBot, LongPollingUpdateConsumer {

    private final TelegramClientConfig telegramClientConfig;
    private final UpdateDispatcher updateDispatcher;

    public Bot(TelegramClientConfig telegramClientConfig, UpdateDispatcher updateDispatcher){
        this.telegramClientConfig = telegramClientConfig;
        this.updateDispatcher = updateDispatcher;
    }

    @Override
    public void consume(List<Update> list) {
        for (Update update : list) {

            if (update.hasMessage() && update.getMessage().hasText()) {
                updateDispatcher.CommandCheck(update);
            } else {
                updateDispatcher.logMessage(update);
            }
        }

    }

    @Override
    public String getBotToken() {
        return telegramClientConfig.getToken();
    }

    @Override
    public LongPollingUpdateConsumer getUpdatesConsumer() {
        return this;
    }
}
