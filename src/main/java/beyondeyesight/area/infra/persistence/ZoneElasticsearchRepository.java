package beyondeyesight.area.infra.persistence;

import beyondeyesight.area.domain.Zone;
import java.util.UUID;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ZoneElasticsearchRepository extends ElasticsearchRepository<Zone, UUID> {
}
