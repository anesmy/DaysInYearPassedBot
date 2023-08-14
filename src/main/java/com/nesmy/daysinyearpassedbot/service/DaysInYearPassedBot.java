package com.nesmy.daysinyearpassedbot.service;

import com.nesmy.daysinyearpassedbot.config.BotProps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
@Component
public class DaysInYearPassedBot extends TelegramLongPollingBot{

    private final BotProps config;

    public DaysInYearPassedBot(BotProps config){
        this.config = config;
    }

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public String getBotToken() {
        return config.getBotToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage() && update.getMessage().hasText()){
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            switch (messageText) {
                case "/start" -> startCommandReceived(chatId, update.getMessage().getChat().getFirstName());
                case "/stop" -> stopCommandReceived(chatId, update.getMessage().getChat().getFirstName());
                default -> sendMessage(chatId, "Command was not recognized.");
            }
        }
    }

    private void startCommandReceived(long chatId, String name){
        final String answer = "Hi, " + name + "!" +
                "\nFrom now on, you will be notified of how many days have already passed this year (in percentage) every morning at 9:00. " +
                "\nYou can stop receiving messages by pressing /stop";
        sendMessage(chatId, answer);
    }

    private void stopCommandReceived(long chatId, String name){
        final String answer = "Hi, " + name + "!" +
                "\nFrom now on, you will not be notified of how many days have already passed this year (in percentage) every morning at 9:00. " +
                "\nYou can start receiving messages by pressing /start";
        sendMessage(chatId, answer);
    }

    private void sendMessage(long chatId, String textToSend){
        SendMessage message = new SendMessage(String.valueOf(chatId), textToSend);

        try {
            execute(message);
        } catch (TelegramApiException e){
            log.error(e.getMessage());
        }
    }
}
