package com.test.javatelegrambot.JavaBot.Command;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardRow;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import java.util.ArrayList;
import java.util.List;

@Service
public class StartCom {

    private final TelegramClient telegramClient;

    public StartCom(TelegramClient telegramClient){
        this.telegramClient = telegramClient;
    }

    public void StartCommand(Update update){
        Long chatId = update.getMessage().getChat().getId();
        String welcomeMessage1 = """
                Привет, ..!""";
        String welcomeMessage2 = """
                
                Мы рады, что ты теперь работаешь в нашей команде!
                Давай познакомимся поближе! Выбирай нужный раздел.""";
        SendMessage sendMessage1 =  SendMessage.builder()
                .chatId(chatId)
                .text(welcomeMessage1)
                .replyMarkup(createMainMenu())
                .build();
        SendMessage sendMessage2 =  SendMessage.builder()
                .chatId(chatId)
                .text(welcomeMessage2)
                .replyMarkup(createInfoBot())
                .build();
        try {
            telegramClient.execute(sendMessage1);
            telegramClient.execute(sendMessage2);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }

    }

    public InlineKeyboardMarkup createInfoBot(){
        new InlineKeyboardRow();

        var Infobot = InlineKeyboardButton.builder()
                .text("О чат-боте")
                .callbackData("InfoBot")
                .build();

        List<InlineKeyboardRow> keyboardRows = List.of(
                new InlineKeyboardRow(Infobot)
        );

        return new InlineKeyboardMarkup(keyboardRows);
    }

    public ReplyKeyboardMarkup createMainMenu() {

        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow row1 = new KeyboardRow();
        row1.add(new KeyboardButton("Адаптация"));
        keyboard.add(row1);

        KeyboardRow row2 = new KeyboardRow();
        row2.add(new KeyboardButton("Чаты"));
        row2.add(new KeyboardButton("HR"));
        keyboard.add(row2);

        KeyboardRow row3 = new KeyboardRow();
        row3.add(new KeyboardButton("Справочник"));
        row3.add(new KeyboardButton("Компания"));
        keyboard.add(row3);

        KeyboardRow row4 = new KeyboardRow();
        row4.add(new KeyboardButton("Техподдержка"));
        keyboard.add(row4);

        KeyboardRow row5 = new KeyboardRow();
        row5.add(new KeyboardButton("Нет"));
        keyboard.add(row5);

        final ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(keyboard);
        replyKeyboardMarkup.setSelective(false);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);
    return replyKeyboardMarkup;
    }

}
