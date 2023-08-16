package com.nesmy.daysinyearpassedbot.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TelegramUser {

    @Id
    private long chatId;
    private boolean isSubscribed;
}
