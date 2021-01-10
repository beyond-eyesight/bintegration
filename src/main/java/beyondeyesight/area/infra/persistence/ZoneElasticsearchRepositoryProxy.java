package beyondeyesight.area.infra.persistence;

import beyondeyesight.area.domain.Zone;
import beyondeyesight.area.domain.repository.ZoneRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
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

    @Override
    public List<Zone> saveAll(List<Zone> zones) {
        return (List<Zone>) zoneElasticsearchRepository.saveAll(zones);
    }

    @Override
    public Iterable<Zone> findAll() {
        return zoneElasticsearchRepository.findAll();
    }

    @Override
    public Zone findById(UUID id) {
        return zoneElasticsearchRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException(String.format("다음에 해당하는 아이디를 찾을 수 없습니다: %s", id)));
    }

    @Override
    public Optional<Zone> findByName(String name) {
        return zoneElasticsearchRepository.findByName(name);
    }
}
