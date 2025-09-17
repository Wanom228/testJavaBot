package com.test.javatelegrambot.JavaBot.service;

import com.test.javatelegrambot.JavaBot.Command.StartCom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

@Service
public class UpdateDispatcher {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateDispatcher.class);

    private final TelegramClient telegramClient;
    private final StartCom startCom;

    public UpdateDispatcher(TelegramClient telegramClient, StartCom startCom) {
        this.telegramClient = telegramClient;
        this.startCom = startCom;
    }

    public void CommandCheck(Update update) {
            //Команды текста
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText();

            switch (message) {
                case "/start":
                    startCom.StartCommand(update);
                    break;

                case "Чаты":
                    break;

                case "Адаптация":
                    break;

                case "HR":
                    break;

                case "Справочник":
                    break;

                case "Компания":
                    break;

                case "Техподдержка":
                    break;

                case "Отзыв":
                    break;

                default:
                    break;
            }
        }
        else if (update.hasCallbackQuery()){
            String callbackData = update.getCallbackQuery().getData();
            switch (callbackData) {
                case "InfoBot":

                    break;
            }
        }
    }

    public void logMessage(Update update) {LOGGER.info("Update: {}", update);}
}
