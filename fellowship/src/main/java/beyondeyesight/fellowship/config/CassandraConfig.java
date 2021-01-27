package beyondeyesight.fellowship.config;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.config.DriverConfigLoader;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import com.datastax.oss.driver.internal.core.config.typesafe.DefaultDriverConfigLoader;
import java.util.Arrays;
import java.util.Collections;
import javax.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.CqlSessionFactoryBean;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@Configuration
@EnableCassandraRepositories(basePackages = "beyondeyesight.fellowship.infra.persistence")
public class CassandraConfig extends AbstractCassandraConfiguration {

    @Value("${spring.data.cassandra.keyspace-name}")
    private String keyspaceName;

    @Value("${spring.data.cassandra.port}")
    private int port;

    @Value("${spring.data.cassandra.contact-points}")
    private String contactPoints;

    @Value("${spring.data.cassandra.local-datacenter}")
    private String localDatacenter;

    @Override
    @Bean
    @Nonnull
    public CqlSessionFactoryBean cassandraSession() {
        CqlSessionFactoryBean cqlSessionFactoryBean = new CqlSessionFactoryBean();
        cqlSessionFactoryBean.setContactPoints(contactPoints);
        cqlSessionFactoryBean.setPort(port);
        cqlSessionFactoryBean.setKeyspaceName(keyspaceName);
        cqlSessionFactoryBean.setLocalDatacenter(localDatacenter);


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
    protected String getKeyspaceName() {
        return keyspaceName;
    }

    @Override
    @Nonnull
    public SchemaAction getSchemaAction() {
        //todo: 운영시 스키마 정책 확인
        //todo: 왜 RECREATE일 때 터지는지 확인
        return SchemaAction.NONE;
    }

    @Override
    @Nonnull
    public String[] getEntityBasePackages() {
        return new String[]{"beyondeyesight.fellowship.domain"};
    }

//    @Override
//    protected Resource getDriverConfigurationResource() {
//        return super.getDriverConfigurationResource();
//    }

//    @Nonnull
//    @Override
//    protected CqlSession getRequiredSession() {
//        DriverConfigLoader loader = DriverConfigLoader.fromClasspath("application.conf");
//        try (CqlSession session = CqlSession.builder()
//            .withConfigLoader(loader)
//            .build()) {
//
//            System.out.println("Session");
//            System.out.println(session);
//            ResultSet rs = session.execute("select * from testkeyspace.chat_room");
//            Row row = rs.one();
//            System.out.println(row.getString("name"));
//            return session;
//        } catch (Exception e) {
//            System.out.println("error");
//            System.out.println();
//            System.out.println(Arrays.toString(e.getStackTrace()));
//            return null;
//        }
//    }

    //    @Nullable
//    @Override
//    protected KeyspacePopulator keyspacePopulator() {
//        return new ResourceKeyspacePopulator(new ClassPathResource("db/cql/db-data.cql"));
//    }
}
