package beyondeyesight.user;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@TestPropertySource(properties = {"spring.config.location=classpath:application-test.yml"})
@SpringBootTest
class UserApplicationTests {

    @Test
    void contextLoads() {
    }

}
