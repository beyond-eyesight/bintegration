package beyondeyesight.area.infra.persistence;

import beyondeyesight.area.domain.Zone;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Optional;
import java.util.UUID;

public interface ZoneElasticsearchRepository extends ElasticsearchRepository<Zone, UUID> {
    Optional<Zone> findByName(String name);
}
