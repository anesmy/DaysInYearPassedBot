package com.nesmy.daysinyearpassedbot.service;

import com.nesmy.daysinyearpassedbot.model.TelegramUser;

import java.util.List;

public interface BotService {
    TelegramUser findById(Long id);

    TelegramUser save(TelegramUser user);

    List<TelegramUser> findAll();
}
