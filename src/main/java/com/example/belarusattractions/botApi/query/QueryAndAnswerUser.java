package com.example.belarusattractions.botApi.query;

import com.example.belarusattractions.model.City;
import com.example.belarusattractions.repository.CityMongoRepository;
import com.example.belarusattractions.service.MainMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@Slf4j
public class QueryAndAnswerUser {
    private final TelegramAnswer telegramAnswer;
    private final MainMenuService mainMenuService;
    private final CityMongoRepository cityMongoRepository;

    public QueryAndAnswerUser(TelegramAnswer telegramAnswer,
                              MainMenuService mainMenuService,
                              CityMongoRepository cityMongoRepository) {
        this.telegramAnswer = telegramAnswer;
        this.mainMenuService = mainMenuService;
        this.cityMongoRepository = cityMongoRepository;
    }
    public BotApiMethod<?> handleUpdate(Update update) {

        SendMessage replyMessage = null;

        if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            log.info("New callbackQuery from User: {}, userId: {}, with data: {}",
                    update.getCallbackQuery().getFrom().getUserName(),
                    callbackQuery.getFrom().getId(), update.getCallbackQuery().getData());
            return processCallbackQuery(callbackQuery);
        }

        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            log.info("New message from User:{}, userId: {}, chatId: {},  with text: {}",
                    message.getFrom().getUserName(), message.getFrom().getId(), message.getChatId(), message.getText());
            replyMessage = telegramAnswer.handleInputMessage(message);
        }

        return replyMessage;
    }

    private BotApiMethod<?> processCallbackQuery(CallbackQuery buttonQuery) {

        final String chatId = buttonQuery.getMessage().getChatId().toString();

        BotApiMethod<?> callBackAnswer = mainMenuService.getMainMenuMessage(chatId,
                "use main menu");

        if (buttonQuery.getData().equals("Minsk")) {
            City minsk = cityMongoRepository.findByName("Minsk");
            callBackAnswer = new SendMessage(chatId, minsk.getAttraction().toString());

        } else if (buttonQuery.getData().equals("Grodno")) {
            City grodno = cityMongoRepository.findByName("Grodno");
            callBackAnswer = new SendMessage(chatId, grodno.getAttraction().toString());
        } else if (buttonQuery.getData().equals("Gomel")) {
            City gomel = cityMongoRepository.findByName("Gomel");
            callBackAnswer = new SendMessage(chatId, gomel.getAttraction().toString());
        }

        return callBackAnswer;
    }
}
