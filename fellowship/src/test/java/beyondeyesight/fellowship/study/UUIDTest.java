package beyondeyesight.fellowship.study;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;
import org.junit.jupiter.api.Test;

public class UUIDTest {
    @Test
    public void nameUUIDFromBytes() {
        String name = "Wangsimni";
        UUID uuid = UUID.nameUUIDFromBytes(name.getBytes());
        assertThat(uuid).isNotNull();
        assertThat(uuid).isEqualTo(UUID.nameUUIDFromBytes(name.getBytes()));
    }
}
