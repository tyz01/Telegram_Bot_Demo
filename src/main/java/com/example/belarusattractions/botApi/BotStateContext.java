package com.example.belarusattractions.botApi;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
public class BotStateContext {
    private final Map<BotState, InputMessageHandler> messageHandlers = new HashMap<>();
    public BotStateContext(List<InputMessageHandler> messageHandlers) {
        messageHandlers.forEach(handler -> this.messageHandlers.put(handler.getHandlerName(), handler));
    }
    public SendMessage processInputMessage(BotState currentState, Message message) {
        InputMessageHandler currentMessageHandler = findMessageHandler(currentState);
        return currentMessageHandler.handle(message);
    }
    private InputMessageHandler findMessageHandler(BotState currentState) {
        if (isCurrentState(currentState)) {
            return messageHandlers.get(BotState.ASK_REGION);
        }
        return messageHandlers.get(currentState);
    }
    private boolean isCurrentState(BotState currentState) {
        return Objects.requireNonNull(currentState) == BotState.ASK_REGION;
    }
}
