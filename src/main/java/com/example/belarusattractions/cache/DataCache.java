package com.example.belarusattractions.cache;

import com.example.belarusattractions.botApi.BotState;

public interface DataCache {
    void setUsersCurrentBotState(String chatId, BotState botState);
    BotState getUsersCurrentBotState(String chatId);

}
