package beyondeyesight.fellowship.infra.adapter;

import beyondeyesight.fellowship.config.EmbeddedRedisConfig;
import beyondeyesight.fellowship.config.TestRedisConfig;
import beyondeyesight.fellowship.domain.model.chat.ChatMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(classes = {EmbeddedRedisConfig.class, TestRedisConfig.class})
public class RedisTemplateTest {

    @Autowired
    private RedisTemplate<String, ChatMessage> redisTemplate;

    @Autowired
    private SimpMessageSendingOperations simpMessageSendingOperations;

    @Autowired
    private ObjectMapper objectMapper;

    /*
     * 이 테스트는 restTemplate이 message를 send할 시, RedisSubscriber가 message를 수신하는 것까지 확인한다.
     * */
    @Test
    public void pubsub() throws InterruptedException, JsonProcessingException {
        UUID id = UUID.randomUUID();
        UUID chatRoomId = UUID.randomUUID();
        UUID senderId = UUID.randomUUID();
        ChatMessage chatMessage = ChatMessage.of(id, chatRoomId, senderId, "testBody");
        when(objectMapper.readValue(anyString(), eq(ChatMessage.class))).thenReturn(chatMessage);
        redisTemplate.convertAndSend(TestRedisConfig.CHANNEL_NAME, chatMessage);
        Thread.sleep(50);
        verify(simpMessageSendingOperations)
            .convertAndSend("/sub/chat/room/" + "110841e3-e6fb-4191-8fd8-5674a5107c33", chatMessage);
    }
}
