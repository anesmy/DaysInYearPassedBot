package com.nesmy.daysinyearpassedbot.component;

import com.nesmy.daysinyearpassedbot.config.BotProps;
import com.nesmy.daysinyearpassedbot.model.TelegramUser;
import com.nesmy.daysinyearpassedbot.service.BotService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class DaysInYearPassedBot extends TelegramLongPollingBot {

    private final BotProps config;
    private final BotService botService;

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
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            String greeting = "Hi, " + update.getMessage().getChat().getFirstName() + "!";

            switch (messageText) {
                case "/start" -> startCommandReceived(chatId, greeting);
                case "/stop" -> stopCommandReceived(chatId, greeting);
                default -> sendMessage(chatId, "Command was not recognized.");
            }
        }
    }

    @Scheduled(cron = "0 0 9 * * *")
    public void sendDailyMessages() {
        LocalDate currentDate = LocalDate.now();
        int totalDays = currentDate.getDayOfYear();
        int daysInYear = currentDate.isLeapYear() ? 366 : 365;

        List<TelegramUser> users = botService.findAll();
        for (TelegramUser u : users) {
            if(u.isSubscribed()) {
                long chatId = u.getChatId();
                float percentage = (totalDays * 100) / (float) daysInYear;
                String message = "Percentage of days in this year passed: " + percentage + "%";
                sendMessage(chatId, message);
            }
        }
    }

    private void startCommandReceived(long chatId, String greeting) {
        TelegramUser user = botService.findById(chatId);
        if (user == null) {
            botService.save(new TelegramUser(chatId, true));
        } else if (!user.isSubscribed()) {
            user.setSubscribed(true);
            botService.save(user);
        }

        String answer = greeting +
                "\nFrom now on, you will be notified of how many days have already passed this year (in percentage) every morning at 9:00. " +
                "\nYou can stop receiving messages by pressing /stop";
        sendMessage(chatId, answer);
    }

    private void stopCommandReceived(long chatId, String greeting) {
        String answer = greeting;

        TelegramUser user = botService.findById(chatId);
        if (user != null && user.isSubscribed()) {
            user.setSubscribed(false);
            botService.save(user);
            answer += "\nFrom now on, you will not be notified of how many days have already passed this year (in percentage) every morning at 9:00. ";
        }

        answer += "\nYou can start receiving messages by pressing /start";
        sendMessage(chatId, answer);
    }

    private void sendMessage(long chatId, String textToSend) {
        SendMessage message = new SendMessage(String.valueOf(chatId), textToSend);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }
}
