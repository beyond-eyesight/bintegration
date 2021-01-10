package beyondeyesight.area;

import beyondeyesight.area.config.TestRestClientConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(classes = TestRestClientConfig.class)
class AreaApplicationTests {

    @Test
    void contextLoads() {
    }

}
