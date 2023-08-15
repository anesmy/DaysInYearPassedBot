package com.nesmy.daysinyearpassedbot.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "bot")
@Data
public class BotProps {

    @Value("${bot.name}")
    private String botName;
    @Value("${bot.token}")
    private String botToken;
}