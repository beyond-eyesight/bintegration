package beyondeyesight.area.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.lang.NonNull;

@Configuration
@EnableElasticsearchRepositories(basePackages = "beyondeyesight.area.infra.persistence")
public class RestClientConfig extends AbstractElasticsearchConfiguration {

    @Value("${db.endpoint}")
    private String endpoint;

    @Value("${db.port}")
    private String port;


    @Override
    @NonNull
    public RestHighLevelClient elasticsearchClient() {

        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
            .connectedTo(String.format("%s:%s", endpoint, port))
            .build();

        return RestClients.create(clientConfiguration).rest();
    }
}
