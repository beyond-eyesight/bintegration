package beyondeyesight.fellowship.infra.persistence;

import beyondeyesight.fellowship.domain.model.chat.ChatRoom;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.UUID;

public interface ChatRoomCassandraRepository extends CassandraRepository<ChatRoom, UUID> {

}
