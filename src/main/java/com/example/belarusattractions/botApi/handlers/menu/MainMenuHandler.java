package com.example.belarusattractions.botApi.handlers.menu;

import com.example.belarusattractions.botApi.BotState;
import com.example.belarusattractions.botApi.InputMessageHandler;
import com.example.belarusattractions.service.MainMenuService;
import com.example.belarusattractions.service.ReplyMessagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
@Component
public class MainMenuHandler implements InputMessageHandler {
    private final ReplyMessagesService messagesService;
    private final MainMenuService mainMenuService;
    @Autowired
    public MainMenuHandler(ReplyMessagesService messagesService, MainMenuService mainMenuService) {
        this.messagesService = messagesService;
        this.mainMenuService = mainMenuService;
    }
    @Override
    public SendMessage handle(Message message) {
        return mainMenuService.getMainMenuMessage(message.getChatId().toString(),
                messagesService.getReplyText("reply.showMainMenu"));
    }
    @Override
    public BotState getHandlerName() {
        return BotState.SHOW_MAIN_MENU;
    }

}
