package beyondeyesight.area.domain.repository;

import beyondeyesight.area.domain.Zone;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ZoneRepository {
    Zone save(Zone zone);
    List<Zone> saveAll(List<Zone> zones);
    Iterable<Zone> findAll();
    Zone findById(UUID id);
    Optional<Zone> findByName(String name);
}
