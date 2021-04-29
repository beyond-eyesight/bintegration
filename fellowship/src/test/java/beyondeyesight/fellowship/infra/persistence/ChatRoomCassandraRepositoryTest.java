package beyondeyesight.fellowship.infra.persistence;

import beyondeyesight.fellowship.config.TestCassandraConfig;
import beyondeyesight.fellowship.domain.model.chat.ChatRoom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestCassandraConfig.class}, loader = SpringBootContextLoader.class)
public class ChatRoomCassandraRepositoryTest {

    @Autowired
    private CassandraOperations cassandraOperations;

    @Autowired
    private ChatRoomCassandraRepository chatRoomCassandraRepository;

    @BeforeEach
    void setUp() {
        String wangsimni = "Wangsimni";
        UUID id = UUID.nameUUIDFromBytes(wangsimni.getBytes());
        cassandraOperations.insert(ChatRoom.of(id, wangsimni));
    }

    @Test
    public void findAll() {
        assertThat(chatRoomCassandraRepository.findAll()).isNotEmpty();
    }

}
