package beyondeyesight.fellowship.study;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class UUIDTest {
    @Test
    public void nameUUIDFromBytes() {
        String name = "Wangsimni";
        UUID uuid = UUID.nameUUIDFromBytes(name.getBytes());
        assertThat(uuid).isNotNull();
        assertThat(uuid).isEqualTo(UUID.nameUUIDFromBytes(name.getBytes()));
    }
}
