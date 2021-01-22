package beyondeyesight.fellowship.domain.adapter;

import beyondeyesight.fellowship.domain.model.chat.ChatMessage;

public interface MessagePublisher {
    void publish(ChatMessage chatMessage);
}
