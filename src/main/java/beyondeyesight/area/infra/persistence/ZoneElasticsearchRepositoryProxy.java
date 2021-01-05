package beyondeyesight.area.infra.persistence;

import beyondeyesight.area.domain.Zone;
import beyondeyesight.area.domain.repository.ZoneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

// todo: impl 대신 Proxy라고 해봤다. 확인!
@RequiredArgsConstructor
@Repository
public class ZoneElasticsearchRepositoryProxy implements ZoneRepository {

    private final ZoneElasticsearchRepository zoneElasticsearchRepository;

    @Override
    public Zone save(Zone zone) {
        return zoneElasticsearchRepository.save(zone);
    }
}
