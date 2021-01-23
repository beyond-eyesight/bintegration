package beyondeyesight.fellowship.infra.persistence;

import beyondeyesight.fellowship.domain.model.chat.ChatMessage;
import java.util.UUID;
import org.springframework.data.cassandra.repository.CassandraRepository;

//todo: ChatMessageRepository도 extends 함으로써 복잡성을 줄일 수 있을지 확인
public interface ChatMessageCassandraRepository extends CassandraRepository<ChatMessage, UUID>{

    //todo: test
    ChatMessage findChatMessagesByChatRoomId(UUID chatRoomId);

    void deleteById(UUID id);

}
