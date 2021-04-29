package beyondeyesight.fellowship.infra.persistence;

import beyondeyesight.fellowship.domain.model.chat.ChatRoom;
import beyondeyesight.fellowship.domain.repository.ChatRoomRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ChatRoomCassandraRepositoryProxy implements ChatRoomRepository {

    private final ChatRoomCassandraRepository chatRoomCassandraRepository;

    public ChatRoomCassandraRepositoryProxy(
        ChatRoomCassandraRepository chatRoomCassandraRepository) {
        this.chatRoomCassandraRepository = chatRoomCassandraRepository;
    }

    @Override
    public List<ChatRoom> findAll() {
        return chatRoomCassandraRepository.findAll();
    }
}
