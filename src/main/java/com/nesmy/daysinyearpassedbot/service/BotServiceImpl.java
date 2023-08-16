package com.nesmy.daysinyearpassedbot.service;

import com.nesmy.daysinyearpassedbot.data.BotRepository;
import com.nesmy.daysinyearpassedbot.model.TelegramUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BotServiceImpl implements BotService{

    private BotRepository botRepository;

    @Override
    public TelegramUser findById(Long id){
        Optional<TelegramUser> user = botRepository.findById(id);
        return user.orElse(null);
    }

    @Override
    public TelegramUser save(TelegramUser user){
        if (user == null) return null;
        return botRepository.save(user);
    }

    @Override
    public List<TelegramUser> findAll(){
        return botRepository.findAll();
    }
}
