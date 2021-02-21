package beyondeyesight.area.config.elasticsearch;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("!local")
@Configuration
public class AWSStaticCredentialsConfiguration {
    @Value("${aws.es.accessKey}")
    private String esAccessKey;

    @Value("${aws.es.secretKey}")
    private String esSecretKey;

    // todo 하드코딩 스타일 지우고, AWSCredentialsConfiguration로 수정.
    @Bean
    public AWSStaticCredentialsProvider awsDynamoCredentialsProviderDevelopment() {
        return new AWSStaticCredentialsProvider(new BasicAWSCredentials(
            esAccessKey, esSecretKey));
    }

}
