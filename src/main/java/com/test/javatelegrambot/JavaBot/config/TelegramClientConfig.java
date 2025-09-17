package com.test.javatelegrambot.JavaBot.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.meta.generics.TelegramClient;

@Configuration
@ConfigurationProperties(prefix = "telegram")
public class TelegramClientConfig {

    private  String token;

    @Bean
    public TelegramClient telegramClient(){
        return new OkHttpTelegramClient(token);
    }
    public void setToken(String token){this.token = token;
    }
    public String getToken(){
        return token;
    }
}
