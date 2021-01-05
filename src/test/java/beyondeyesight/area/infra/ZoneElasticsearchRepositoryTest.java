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
@ContextConfiguration(classes = {
    TestRestClientConfig.class}, loader = SpringBootContextLoader.class)
public class ZoneElasticsearchRepositoryTest {

    @Autowired
    private ZoneElasticsearchRepository zoneElasticsearchRepository;


    @Test
    public void saveAndFindById() {
        Zone wangsimni = new Zone("Wangsimni", GeoJsonPolygon.of(
            Arrays.asList(
                new Point(37.56186460715209, 127.03878873296458),
                new Point(37.55827838080759, 127.03892370195969),
                new Point(37.55753238253602, 127.04159778997553),
                new Point(37.561966294018255, 127.04168241338516),
                new Point(37.56186460715209, 127.03878873296458)))
        );
        Zone save = zoneElasticsearchRepository.save(wangsimni);

        Zone queried = zoneElasticsearchRepository.findById("Wangsimni").orElse(null);
        assertThat(queried).isNotNull();

    }
}
