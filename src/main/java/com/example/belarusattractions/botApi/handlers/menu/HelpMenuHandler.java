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
public class HelpMenuHandler implements InputMessageHandler {
    private final MainMenuService mainMenuService;
    private final ReplyMessagesService messagesService;
    @Autowired
    public HelpMenuHandler(MainMenuService mainMenuService, ReplyMessagesService messagesService) {
        this.mainMenuService = mainMenuService;
        this.messagesService = messagesService;
    }
    @Override
    public SendMessage handle(Message message) {
        return mainMenuService.getMainMenuMessage(message.getChatId().toString(),
                messagesService.getReplyText("reply.showHelpMenu"));
    }
    @Override
    public BotState getHandlerName() {
        return BotState.SHOW_HELP_MENU;
    }

}
