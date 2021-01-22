package beyondeyesight.fellowship.domain.service;

import static org.assertj.core.api.Assertions.assertThat;

import beyondeyesight.fellowship.config.TestCassandraConfig;
import beyondeyesight.fellowship.config.TestRedisConfig;
import beyondeyesight.fellowship.infra.adapter.RedisPublisher;
import beyondeyesight.fellowship.infra.persistence.ChatMessageCassandraRepositoryProxy;
import org.cassandraunit.spring.CassandraUnitDependencyInjectionTestExecutionListener;
import org.cassandraunit.spring.EmbeddedCassandra;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

@ActiveProfiles("test")
@TestExecutionListeners(listeners = {
    CassandraUnitDependencyInjectionTestExecutionListener.class,
    DependencyInjectionTestExecutionListener.class}
)
@EmbeddedCassandra(timeout = 60000)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestCassandraConfig.class, TestRedisConfig.class,
    ChatMessageCassandraRepositoryProxy.class,
    RedisPublisher.class,
    ChatMessageService.class}, loader = SpringBootContextLoader.class)
class ChatMessageServiceTest {

    @Autowired
    private ChatMessageService chatMessageService;

    @Test
    void di() {
        assertThat(chatMessageService).isNotNull();
    }
}
