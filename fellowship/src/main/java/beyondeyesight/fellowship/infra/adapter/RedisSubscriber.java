package beyondeyesight.fellowship.infra.adapter;

import beyondeyesight.fellowship.domain.model.chat.ChatMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@Service
public class RedisSubscriber implements MessageListener {
    private final RedisTemplate<String, ChatMessage> redisTemplate;
    private final ObjectMapper objectMapper;
    private final SimpMessageSendingOperations messagingTemplate;

    public RedisSubscriber(RedisTemplate<String, ChatMessage> redisTemplate, ObjectMapper objectMapper, SimpMessageSendingOperations messagingTemplate) {
        this.redisTemplate = redisTemplate;
        this.objectMapper = objectMapper;
        this.messagingTemplate = messagingTemplate;
    }


    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {
            String publishMessage = redisTemplate.getStringSerializer().deserialize(message.getBody());
            ChatMessage chatMessage = objectMapper.readValue(publishMessage, ChatMessage.class);
            //todo: refac
            messagingTemplate.convertAndSend("/sub/chat/room/110841e3-e6fb-4191-8fd8-5674a5107c33", chatMessage);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
