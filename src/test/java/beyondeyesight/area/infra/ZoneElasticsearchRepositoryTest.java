package beyondeyesight.area.infra;

import static org.assertj.core.api.Assertions.assertThat;

import beyondeyesight.area.config.TestRestClientConfig;
import beyondeyesight.area.domain.Zone;
import beyondeyesight.area.infra.persistence.ZoneElasticsearchRepository;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.data.elasticsearch.core.geo.GeoJsonPolygon;
import org.springframework.data.geo.Point;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestRestClientConfig.class}, loader = SpringBootContextLoader.class)
public class ZoneElasticsearchRepositoryTest {

    @Autowired
    private ZoneElasticsearchRepository zoneElasticsearchRepository;


    @Test
    public void saveAndFindById() {
        List<Point> points = Arrays.asList(
            new Point(127.027926, 37.497175),
            new Point(126.991806, 37.571607),
            new Point(126.924191, 37.521624),
            new Point(126.972559, 37.554648),
            new Point(127.027926, 37.497175));
        GeoJsonPolygon geoJsonPolygon = GeoJsonPolygon.of(points);
        Zone zone = new Zone("Wangsimni", geoJsonPolygon);
        Zone save = zoneElasticsearchRepository.save(zone);

        Zone wangsimni = zoneElasticsearchRepository.findById("Wangsimni").orElse(null);
        assertThat(wangsimni).isNotNull();

    }
}
