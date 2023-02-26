package com.example.belarusattractions;

import com.example.belarusattractions.botApi.query.QueryAndAnswerUser;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
public class SettingsTelegramBot extends TelegramWebhookBot {
    private String webHookPath;
    private String botUserName;
    private String botToken;
    private final QueryAndAnswerUser queryAndAnswerUser;
    public SettingsTelegramBot(DefaultBotOptions botOptions, QueryAndAnswerUser queryAndAnswerUser) {
        super(botOptions);
        this.queryAndAnswerUser = queryAndAnswerUser;
    }
    @Override
    public String getBotToken() {
        return botToken;
    }
    @Override
    public String getBotUsername() {
        return botUserName;
    }
    @Override
    public String getBotPath() {
        return webHookPath;
    }
    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        return queryAndAnswerUser.handleUpdate(update);
    }
    public void setWebHookPath(String webHookPath) {
        this.webHookPath = webHookPath;
    }
    public void setBotUserName(String botUserName) {
        this.botUserName = botUserName;
    }
    public void setBotToken(String botToken) {
        this.botToken = botToken;
    }
}