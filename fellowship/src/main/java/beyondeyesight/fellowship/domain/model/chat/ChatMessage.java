package beyondeyesight.fellowship.domain.model.chat;

import lombok.Data;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;


//todo: check if serializable is needed
@Data
@Table("chat_message")
public class ChatMessage {

    @PrimaryKeyColumn(name = "msg_id", type = PrimaryKeyType.CLUSTERED, ordinal = 1)
    private UUID id;

    //todo: ChatRoom으로 해도 될지(id 말고).
    @PrimaryKeyColumn(name = "receiver_id", type = PrimaryKeyType.PARTITIONED, ordinal = 0)
    private UUID chatRoomId;

    @Column("sender_id")
    private UUID senderId;
    @Column
    private String body;

    //deserialize를 위해 필
    private ChatMessage() {}

    private ChatMessage(UUID id, UUID chatRoomId, UUID senderId, String body) {
        this.id = id;
        this.chatRoomId = chatRoomId;
        this.senderId = senderId;
        this.body = body;
    }


    public static ChatMessage of(UUID id, UUID chatRoomId, UUID sender, String body) {
        return new ChatMessage(id, chatRoomId, sender, body);
    }

    public String getChatRoomId() {
        return chatRoomId.toString();
    }

    @Override
    public String toString() {
        return "ChatMessage{" +
            "id=" + id +
            ", chatRoomId=" + chatRoomId +
            ", senderId=" + senderId +
            ", body='" + body + '\'' +
            '}';
    }
}
