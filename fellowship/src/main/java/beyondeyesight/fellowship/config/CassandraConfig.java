package beyondeyesight.fellowship.config;

import com.datastax.oss.driver.api.core.CqlSessionBuilder;
import javax.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.cassandra.CassandraAutoConfiguration;
import org.springframework.boot.autoconfigure.cassandra.CassandraProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;




@Configuration
@EnableCassandraRepositories(basePackages = "beyondeyesight.fellowship.infra.persistence")
@EnableConfigurationProperties(CassandraProperties.class)
public class CassandraConfig extends CassandraAutoConfiguration {

    @Value("${spring.data.cassandra.keyspace-name}")
    private String keyspaceName;

    @Value("${spring.data.cassandra.port}")
    private int port;

    @Value("${spring.data.cassandra.contact-points}")
    private String contactPoints;

    @Value("${spring.data.cassandra.local-datacenter}")
    private String localDatacenter;

    @Autowired
    private CassandraProperties cassandraProperties;

    @Autowired
    private CqlSessionBuilder cqlSessionBuilder;

    @Nonnull
    protected String getKeyspaceName() {
        return keyspaceName;
    }

    @Nonnull
    public SchemaAction getSchemaAction() {
        //todo: 운영시 스키마 정책 확인
        //todo: 왜 RECREATE일 때 터지는지 확인
        return SchemaAction.NONE;
    }

    @Nonnull
    public String[] getEntityBasePackages() {
        return new String[]{"beyondeyesight.fellowship.domain"};
    }
}
