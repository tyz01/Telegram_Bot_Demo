package com.example.belarusattractions.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Service
public class ReplyMessagesService {
    private final LocaleMessageService localeMessageService;
    @Autowired
    public ReplyMessagesService(LocaleMessageService messageService) {
        this.localeMessageService = messageService;
    }
    public SendMessage getReplyMessage(String chatId, String replyMessage) {
        return new SendMessage(chatId, localeMessageService.getMessage(replyMessage));
    }
    public String getReplyText(String replyText) {
        return localeMessageService.getMessage(replyText);
    }
}