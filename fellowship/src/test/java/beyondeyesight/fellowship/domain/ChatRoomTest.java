package beyondeyesight.fellowship.domain;

import beyondeyesight.fellowship.domain.model.chat.ChatRoom;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

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
