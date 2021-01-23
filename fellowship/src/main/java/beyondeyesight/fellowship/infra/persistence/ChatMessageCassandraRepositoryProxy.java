package beyondeyesight.fellowship.infra.persistence;

import beyondeyesight.fellowship.domain.model.chat.ChatMessage;
import beyondeyesight.fellowship.domain.repository.ChatMessageRepository;
import org.springframework.stereotype.Repository;


@Repository
public class ChatMessageCassandraRepositoryProxy implements ChatMessageRepository {

    private final ChatMessageCassandraRepository chatMessageCassandraRepository;

    public ChatMessageCassandraRepositoryProxy(
        ChatMessageCassandraRepository chatMessageCassandraRepository) {
        this.chatMessageCassandraRepository = chatMessageCassandraRepository;
    }

    @Override
    public ChatMessage save(ChatMessage chatMessage) {
        return chatMessageCassandraRepository.save(chatMessage);
    }
}
