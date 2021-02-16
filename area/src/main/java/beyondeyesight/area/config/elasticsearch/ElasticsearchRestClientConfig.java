package beyondeyesight.area.config.elasticsearch;

import com.amazonaws.auth.AWS4Signer;
import com.amazonaws.auth.AWSCredentialsProvider;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequestInterceptor;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.lang.NonNull;

@Configuration
@EnableElasticsearchRepositories(basePackages = "beyondeyesight.area.infra.persistence")
@RequiredArgsConstructor
public class ElasticsearchRestClientConfig extends AbstractElasticsearchConfiguration {

    private static final String SERVICE_NAME = "es";
    private static final String REGION_NAME = "ap-northeast-2";
    private final AWSCredentialsProvider awsCredentialsProvider;

    @Value("${elasticsearch.endpoint}")
    private String endpoint;

    @Override
    @NonNull
    public RestHighLevelClient elasticsearchClient() {
        AWS4Signer signer = new AWS4Signer();
        signer.setServiceName(SERVICE_NAME);
        signer.setRegionName(REGION_NAME);
        HttpRequestInterceptor interceptor = new AWSRequestSigningApacheInterceptor(SERVICE_NAME,
            signer, awsCredentialsProvider);
        return new RestHighLevelClient(RestClient.builder(HttpHost.create(endpoint))
            .setHttpClientConfigCallback(e -> e.addInterceptorLast(interceptor)));
    }
}
