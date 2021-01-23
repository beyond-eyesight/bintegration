package beyondeyesight.fellowship.domain.repository;

import beyondeyesight.fellowship.domain.model.chat.ChatRoom;
import java.util.List;

public interface ChatRoomRepository {
    List<ChatRoom> findAll();
}
