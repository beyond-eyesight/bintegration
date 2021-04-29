package beyondeyesight.area.study;


import beyondeyesight.area.domain.Zone;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.jupiter.api.Test;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.geo.GeoJsonPolygon;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.geo.Point;
import org.testcontainers.elasticsearch.ElasticsearchContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


public class ElasticsearchOperationsTest {
    private static final String ELASTICSEARCH_VERSION = "7.9.2";
    private static final DockerImageName ELASTICSEARCH_IMAGE =
        DockerImageName
            .parse("docker.elastic.co/elasticsearch/elasticsearch")
            .withTag(ELASTICSEARCH_VERSION);

    @Test
    public void saveAndGet() {
        // todo: static method로 뺄지 고려
        try (ElasticsearchContainer container = new ElasticsearchContainer(ELASTICSEARCH_IMAGE).withExposedPorts(9200)) {
            container.start();
            // given
            UUID zoneId = UUID.randomUUID();
            String zoneName = "test_name";
            String indexName = "test_index_name";
            List<Point> points = Arrays.asList(
                new Point(127.027926, 37.497175),
                new Point(126.991806, 37.571607),
                new Point(126.924191, 37.521624),
                new Point(126.972559, 37.554648),
                new Point(127.027926, 37.497175));
            Zone zone = new Zone(zoneId, zoneName, GeoJsonPolygon.of(points));
            IndexQuery indexQuery = new IndexQueryBuilder()
                .withId(zoneId.toString())
                .withObject(zone)
                .build();
            IndexCoordinates index = IndexCoordinates.of(indexName);

            ElasticsearchOperations elasticsearchOperations = createOperations(container);

            //when
            String savedIndex = elasticsearchOperations.index(indexQuery, index);
            //then
            assertThat(savedIndex).isEqualTo(zoneId.toString());
            //when
            Zone queried = elasticsearchOperations.get(zoneId.toString(), Zone.class, index);
            //then
            assertThat(queried).isNotNull();
        }
    }

    private ElasticsearchOperations createOperations(ElasticsearchContainer container) {
        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
            .connectedTo(container.getHttpHostAddress())
            .build();

        RestHighLevelClient client = RestClients.create(clientConfiguration).rest();

        return new ElasticsearchRestTemplate(client);
    }
}
