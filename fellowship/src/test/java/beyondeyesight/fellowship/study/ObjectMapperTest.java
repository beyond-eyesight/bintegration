package beyondeyesight.fellowship.study;


import beyondeyesight.fellowship.domain.model.chat.ChatMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class ObjectMapperTest {

    @Test
    public void readValue() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        UUID testId = UUID.randomUUID();
        String publishedMessage = "{\"id\":" + "\"" + testId + "\","
            + "\"chatRoomId\":" + "\"" + testId + "\","
            + "\"senderId\":\"" + testId + "\","
            + "\"body\":\"testBody\"}";
        ChatMessage chatMessage = objectMapper.readValue(publishedMessage, ChatMessage.class);
        assertThat(chatMessage).isNotNull();
    }

}
