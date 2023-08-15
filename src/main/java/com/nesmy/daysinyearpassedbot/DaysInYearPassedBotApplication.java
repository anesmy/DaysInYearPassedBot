package com.nesmy.daysinyearpassedbot;

import com.nesmy.daysinyearpassedbot.config.BotProps;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(BotProps.class)
public class DaysInYearPassedBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(DaysInYearPassedBotApplication.class, args);
    }

}
