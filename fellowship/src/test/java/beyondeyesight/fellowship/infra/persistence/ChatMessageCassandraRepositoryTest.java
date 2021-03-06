package beyondeyesight.fellowship.infra.persistence;

import beyondeyesight.fellowship.config.TestCassandraConfig;
import beyondeyesight.fellowship.domain.model.chat.ChatMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


/*
 * reference: https://github.com/omlip/cassandra-unit-spring-demo
 * */
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestCassandraConfig.class}, loader = SpringBootContextLoader.class)
public class ChatMessageCassandraRepositoryTest {

    @Autowired
    private ChatMessageCassandraRepository chatMessageCassandraRepository;


    //todo: ID 생성을 DB 레벨에서 할 수 있는지 확인. 애플리케이션에서는 ID 안넣어도 DB에서 생성해주는지.
    @DisplayName("#save() : should not be null after saving entity")
    @Test
    public void save() {
        System.out.println("kakakaka");
        System.out.println(chatMessageCassandraRepository);
        UUID id = UUID.randomUUID();
        UUID chatRoomId = UUID.randomUUID();
        UUID senderId = UUID.randomUUID();
        ChatMessage chatMessage = ChatMessage.of(id, chatRoomId, senderId, "chatBody");

        chatMessage = chatMessageCassandraRepository.save(chatMessage);
        assertThat(chatMessage).isNotNull();
    }
}
