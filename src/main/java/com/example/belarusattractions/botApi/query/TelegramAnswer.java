package com.example.belarusattractions.botApi.query;

import com.example.belarusattractions.botApi.BotState;
import com.example.belarusattractions.botApi.BotStateContext;
import com.example.belarusattractions.cache.UserDataCache;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
@Data
@Slf4j
public class TelegramAnswer {
    private final static String START = "/start";
    private final static String HELP = "help";
    private final BotStateContext botStateContext;
    private final UserDataCache userDataCache;

    @Autowired
    public TelegramAnswer(BotStateContext botStateContext,
                          UserDataCache userDataCache) {
        this.botStateContext = botStateContext;
        this.userDataCache = userDataCache;
    }
    private BotState getBotState(String inputMsg, String chatId) {
        return switch (inputMsg) {
            case START -> BotState.ASK_REGION;
            case HELP -> BotState.SHOW_HELP_MENU;
            default -> userDataCache.getUsersCurrentBotState(chatId);
        };
    }
    public SendMessage handleInputMessage(Message message) {

        String inputMsg = message.getText();
        String userId = message.getFrom().getId().toString();
        String chatId = message.getChatId().toString();

        BotState botState;
        SendMessage replyMessage;

        botState = getBotState(inputMsg, chatId);

        userDataCache.setUsersCurrentBotState(userId, botState);
        replyMessage = botStateContext.processInputMessage(botState, message);

        return replyMessage;

    }

}
