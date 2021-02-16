package beyondeyesight.area.config;

import com.amazonaws.auth.AWS4Signer;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
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
public class RestClientConfig extends AbstractElasticsearchConfiguration {

    @Value("${elasticsearch.host}")
    private String host;

    @Value("${elasticsearch.port}")
    private String port;

    private final AWSStaticCredentialsProvider awsStaticCredentialsProvider;

    @Override
    @NonNull
    public RestHighLevelClient elasticsearchClient() {

        AWS4Signer signer = new AWS4Signer();
        String serviceName = "es";
        signer.setServiceName(serviceName);
        signer.setRegionName("ap-northeast-2");
        HttpRequestInterceptor interceptor = new AWSRequestSigningApacheInterceptor(serviceName, signer, awsStaticCredentialsProvider);
        return new RestHighLevelClient(RestClient.builder(HttpHost.create(host)).setHttpClientConfigCallback(e -> e.addInterceptorLast(interceptor)));
    }
}
