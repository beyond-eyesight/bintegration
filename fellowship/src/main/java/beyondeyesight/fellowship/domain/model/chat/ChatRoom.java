package beyondeyesight.fellowship.domain.model.chat;

import lombok.Data;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Data
@Table("chat_room")
public class ChatRoom {

    @PrimaryKeyColumn(name = "room_id", type = PrimaryKeyType.CLUSTERED, ordinal = 1)
    private UUID id;

    @PrimaryKeyColumn(name = "name", type = PrimaryKeyType.PARTITIONED, ordinal = 0)
    private String name;

    private ChatRoom(UUID id, String name) {
        //todo: region 서비스를 만들면, id를 받아서 생성
        this.id = id;
        this.name = name;
    }

    public static ChatRoom of(UUID id, String name) {
        return new ChatRoom(id, name);
    }

    @Override
    public String toString() {
        return "ChatRoom{" +
            "id=" + id +
            ", name='" + name + '\'' +
            '}';
    }
}
