package beyondeyesight.fellowship.infra.persistence;

import beyondeyesight.fellowship.domain.model.chat.ChatRoom;
import java.util.UUID;
import org.springframework.data.cassandra.repository.CassandraRepository;

public interface ChatRoomCassandraRepository extends CassandraRepository<ChatRoom, UUID> {

}
