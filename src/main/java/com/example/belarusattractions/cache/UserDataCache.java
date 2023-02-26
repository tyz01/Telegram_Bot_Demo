package com.example.belarusattractions.cache;

import com.example.belarusattractions.botApi.BotState;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserDataCache implements DataCache {
    private final Map<String, BotState> usersBotStates = new HashMap<>();
    @Override
    public void setUsersCurrentBotState(String chatId, BotState botState) {
        usersBotStates.put(chatId, botState);
    }
    @Override
    public BotState getUsersCurrentBotState(String chatId) {
        BotState botState = usersBotStates.get(chatId);
        if (botState == null) {
            botState = BotState.ASK_REGION;
        }

        return botState;
    }


}
