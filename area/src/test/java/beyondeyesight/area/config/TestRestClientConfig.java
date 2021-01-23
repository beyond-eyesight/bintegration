package beyondeyesight.area.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.testcontainers.elasticsearch.ElasticsearchContainer;
import org.testcontainers.utility.DockerImageName;

@Profile("test")
@EnableElasticsearchRepositories(basePackages = "beyondeyesight.area.infra.persistence")
@Configuration
public class TestRestClientConfig {

    private static final String ELASTICSEARCH_VERSION = "7.9.2";
    private static final DockerImageName ELASTICSEARCH_IMAGE =
        DockerImageName
            .parse("docker.elastic.co/elasticsearch/elasticsearch")
            .withTag(ELASTICSEARCH_VERSION);

    private static final ElasticsearchContainer container;

    static {
         container = new ElasticsearchContainer(ELASTICSEARCH_IMAGE);
         container.start();
    }

    @Bean
    public ElasticsearchOperations elasticsearchTemplate() {
        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
            .connectedTo(container.getHttpHostAddress())
            .build();

        RestHighLevelClient client = RestClients.create(clientConfiguration).rest();
        return new ElasticsearchRestTemplate(client);
    }
}
