package com.nesmy.daysinyearpassedbot.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "bot")
@Data
public class BotProps {
    private String botName;
    private String botToken;
}