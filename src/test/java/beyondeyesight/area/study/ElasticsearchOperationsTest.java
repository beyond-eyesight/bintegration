package beyondeyesight.area.study;


import static org.assertj.core.api.Assertions.assertThat;

import beyondeyesight.area.domain.Zone;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.geo.GeoJsonPolygon;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.geo.Point;

//todo: Context로 바꾸기
@SpringBootTest
public class ElasticsearchOperationsTest {

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    @Test
    public void saveAndGet() {
        // given
        String zoneId = "testId";
        String indexName = "testIndexName";
        List<Point> points = Arrays.asList(
            new Point(127.027926, 37.497175),
            new Point(126.991806, 37.571607),
            new Point(126.924191, 37.521624),
            new Point(126.972559, 37.554648),
            new Point(127.027926, 37.497175));
        Zone zone = new Zone(zoneId, GeoJsonPolygon.of(points));
        IndexQuery indexQuery = new IndexQueryBuilder()
            .withId(zone.getId())
            .withObject(zone)
            .build();
        IndexCoordinates index = IndexCoordinates.of(indexName);

        //when
        elasticsearchOperations.index(indexQuery, index);
        //when
        Zone queried = elasticsearchOperations.get(zoneId, Zone.class, index);
        //then
        assertThat(queried).isNotNull();
    }
}
