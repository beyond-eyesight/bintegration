package beyondeyesight.fellowship.config;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Host;
import java.net.InetSocketAddress;
import java.util.Collections;
import javax.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.CqlSessionFactoryBean;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
import org.testcontainers.containers.CassandraContainer;
import org.testcontainers.utility.DockerImageName;

@Profile("test")
@EnableCassandraRepositories(basePackages = "beyondeyesight.fellowship.infra.persistence")
@Configuration
public class TestCassandraConfig extends AbstractCassandraConfiguration {

    @Value("${spring.data.cassandra.keyspace-name}")
    private String keyspaceName;


    private static final CassandraContainer<?> container;
    private static final DockerImageName CASSANDRA_IMAGE = DockerImageName
        .parse("cassandra:3.11.2");

    static {
        container = new CassandraContainer<>(CASSANDRA_IMAGE);
        container.start();
    }

    @Override
    @Nonnull
    public SchemaAction getSchemaAction() {
        return SchemaAction.CREATE_IF_NOT_EXISTS;
    }


    @Nonnull
    @Override
    protected String getKeyspaceName() {
        return keyspaceName;
    }

    @Override
    @Bean
    @Nonnull
    public CqlSessionFactoryBean cassandraSession() {
        Cluster cluster = container.getCluster();
        Host host = cluster.getMetadata().getAllHosts().stream().findAny()
            .orElseThrow(IllegalStateException::new);
        InetSocketAddress socketAddress = host.getSocketAddress();

        CqlSessionFactoryBean cqlSessionFactoryBean = new CqlSessionFactoryBean();
        cqlSessionFactoryBean.setContactPoints(socketAddress.getHostName());
        cqlSessionFactoryBean.setPort(socketAddress.getPort());
        cqlSessionFactoryBean.setLocalDatacenter(host.getDatacenter());
        cqlSessionFactoryBean.setKeyspaceName(keyspaceName);

        //todo: 걍 CreateKeyspaceSpecification.createKeyspace 만 해도 될듯?
        cqlSessionFactoryBean.setKeyspaceCreations(
            Collections.singletonList(
                CreateKeyspaceSpecification.createKeyspace(keyspaceName)
                    .ifNotExists()
                    .withSimpleReplication(1)
            )
        );
        return cqlSessionFactoryBean;
    }

    @Override
    @Nonnull
    public String[] getEntityBasePackages() {
        return new String[]{"beyondeyesight.fellowship.domain"};
    }
}
