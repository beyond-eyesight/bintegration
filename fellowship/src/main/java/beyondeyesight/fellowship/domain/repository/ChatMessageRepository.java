package beyondeyesight.fellowship.domain.repository;

import beyondeyesight.fellowship.domain.model.chat.ChatMessage;


public interface ChatMessageRepository {

    ChatMessage save(ChatMessage chatMessage);
}
