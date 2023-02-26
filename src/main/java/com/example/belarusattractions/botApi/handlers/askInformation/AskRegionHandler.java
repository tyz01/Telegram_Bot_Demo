package com.example.belarusattractions.botApi.handlers.askInformation;

import com.example.belarusattractions.botApi.BotState;
import com.example.belarusattractions.botApi.InputMessageHandler;
import com.example.belarusattractions.service.ReplyMessagesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class AskRegionHandler implements InputMessageHandler {
    private final ReplyMessagesService messagesService;

    @Autowired
    public AskRegionHandler(ReplyMessagesService messagesService) {
        this.messagesService = messagesService;
    }

    @Override
    public SendMessage handle(Message message) {
        return processUsersInput(message);
    }

    @Override
    public BotState getHandlerName() {
        return BotState.ASK_REGION;
    }

    private SendMessage processUsersInput(Message inputMsg) {
        String chatId = inputMsg.getChatId().toString();

        SendMessage replyToUser = messagesService.getReplyMessage(chatId, "reply.askRegion");
        replyToUser.setReplyMarkup(getInlineMessageButtons());

        return replyToUser;
    }

    private InlineKeyboardMarkup getInlineMessageButtons() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton buttonMinsk = new InlineKeyboardButton();
        buttonMinsk.setText("Misnk");
        InlineKeyboardButton buttonGrodno = new InlineKeyboardButton();
        buttonGrodno.setText("Grodno");
        InlineKeyboardButton buttonGomel = new InlineKeyboardButton();
        buttonGomel.setText("Gomel");
        InlineKeyboardButton buttonIdontKnow = new InlineKeyboardButton();
        buttonIdontKnow.setText("all cities");

        buttonMinsk.setCallbackData("Minsk");
        buttonGrodno.setCallbackData("Grodno");
        buttonGomel.setCallbackData("Gomel");
        buttonIdontKnow.setCallbackData("other");

        List<List<InlineKeyboardButton>> rowList = getLists(buttonMinsk,
                buttonGrodno, buttonGomel, buttonIdontKnow);

        inlineKeyboardMarkup.setKeyboard(rowList);

        return inlineKeyboardMarkup;
    }

    private static List<List<InlineKeyboardButton>> getLists(InlineKeyboardButton buttonMinsk,
                                                             InlineKeyboardButton buttonGrodno,
                                                             InlineKeyboardButton buttonGomel,
                                                             InlineKeyboardButton buttonIdontKnow) {
        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(buttonMinsk);
        keyboardButtonsRow1.add(buttonGrodno);

        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        keyboardButtonsRow2.add(buttonGomel);
        keyboardButtonsRow2.add(buttonIdontKnow);

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);
        rowList.add(keyboardButtonsRow2);

        return rowList;
    }
}