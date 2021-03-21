package beyondeyesight.fellowship.infra.persistence;

import static org.assertj.core.api.Assertions.assertThat;

import beyondeyesight.fellowship.config.TestCassandraConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestCassandraConfig.class}, loader = SpringBootContextLoader.class)
public class ChatRoomCassandraRepositoryTest {

    @Autowired
    private ChatRoomCassandraRepository chatRoomCassandraRepository;

    @Test
    public void findAll() {
        assertThat(chatRoomCassandraRepository.findAll()).isNotEmpty();
    }

}
