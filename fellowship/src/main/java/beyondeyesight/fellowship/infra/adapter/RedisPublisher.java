package beyondeyesight.fellowship.infra.adapter;

import beyondeyesight.fellowship.domain.adapter.MessagePublisher;
import beyondeyesight.fellowship.domain.model.chat.ChatMessage;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisPublisher implements MessagePublisher {
    private final RedisTemplate<String, ChatMessage> redisTemplate;

    public RedisPublisher(RedisTemplate<String, ChatMessage> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void publish(ChatMessage chatMessage) {
        redisTemplate.convertAndSend(chatMessage.getChatRoomId(), chatMessage);
    }
}
