package beyondeyesight.area.infra;

import static org.assertj.core.api.Assertions.assertThat;

import beyondeyesight.area.config.TestRestClientConfig;
import beyondeyesight.area.domain.Zone;
import beyondeyesight.area.infra.persistence.ZoneElasticsearchRepository;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.data.elasticsearch.core.geo.GeoJsonPolygon;
import org.springframework.data.geo.Point;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
    TestRestClientConfig.class}, loader = SpringBootContextLoader.class)
public class ZoneElasticsearchRepositoryTest {

    private final static UUID WANGSIMNI_ID = UUID.fromString("110841e3-e6fb-4191-8fd8-5674a5107c33");
    private final static UUID CAMPUS_ID = UUID.fromString("4f0a4a02-26c6-4441-915d-c0f61cda0178");
    private final static UUID DORMITORY_ID = UUID.fromString("cd865f7d-3923-4065-a5e6-5c093e7b5442");

    @Autowired
    private ZoneElasticsearchRepository zoneElasticsearchRepository;

    private static Stream<Arguments> provideZoneIds() {
        return Stream.of(
            Arguments.of(WANGSIMNI_ID),
            Arguments.of(CAMPUS_ID),
            Arguments.of(DORMITORY_ID)
        );
    }

    @BeforeEach
    public void setup() {
        List<Zone> zones = setupZones();
        Iterable<Zone> saved = zoneElasticsearchRepository.saveAll(zones);
        assertThat(saved).isEqualTo(zones);
    }

    @ParameterizedTest
    @MethodSource("provideZoneIds")
    public void findById(UUID id) {
        Zone queried = zoneElasticsearchRepository.findById(id).orElse(null);
        assertThat(queried).isNotNull();
    }

    private List<Zone> setupZones() {
        Zone wangsimni = new Zone(WANGSIMNI_ID, "Wangsimni", GeoJsonPolygon.of(
            Arrays.asList(
                new Point(37.56186460715209, 127.03878873296458),
                new Point(37.55827838080759, 127.03892370195969),
                new Point(37.55753238253602, 127.04159778997553),
                new Point(37.561966294018255, 127.04168241338516),
                new Point(37.56186460715209, 127.03878873296458)))
        );

        Zone campus = new Zone(CAMPUS_ID, "Campus", GeoJsonPolygon.of(
            Arrays.asList(
                new Point(37.55779745949392, 127.04097013642883),
                new Point(37.558572069069136, 127.04055639416698),
                new Point(37.56017011204165, 127.04097893944592),
                new Point(37.56141921346345, 127.0447290288554),
                new Point(37.559695587571696, 127.04710584593279),
                new Point(37.56047715566452, 127.05004605689999),
                new Point(37.55702982040824, 127.0515601774627),
                new Point(37.55433871607011, 127.04819624892356),
                new Point(37.5538154114159, 127.04348753393771),
                new Point(37.55779745949392, 127.04097013642883)
            )
        ));

        Zone dormitory = new Zone(DORMITORY_ID, "Dormitory", GeoJsonPolygon.of(
            Arrays.asList(
                new Point(37.56089445091836, 127.04548536257013),
                new Point(37.55946005643694, 127.04484811300844),
                new Point(37.55792584743151, 127.04775900606796),
                new Point(37.55977812020412, 127.05022146425075),
                new Point(37.56089445091836, 127.04548536257013)
            )
        ));

        return Arrays.asList(wangsimni, campus, dormitory);
    }
}
