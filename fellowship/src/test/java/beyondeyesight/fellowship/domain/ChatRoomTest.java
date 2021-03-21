package beyondeyesight.fellowship.domain;

import static org.assertj.core.api.Assertions.assertThat;

import beyondeyesight.fellowship.domain.model.chat.ChatRoom;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChatRoomTest {

    @DisplayName("#of() : should return new RegionalChatRoom")
    @Test
    void of() {
        //given
        String name = "testRoomName";
        UUID id = UUID.nameUUIDFromBytes(name.getBytes());
        //when
        ChatRoom chatRoom = ChatRoom.of(id, name);
        //then
        assertThat(chatRoom).isNotNull();
    }
}
