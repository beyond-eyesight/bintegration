package beyondeyesight.area.domain.repository;

import beyondeyesight.area.domain.Zone;
import java.util.List;
import java.util.UUID;

public interface ZoneRepository {
    Zone save(Zone zone);
    List<Zone> findAll();
    Zone findById(UUID id);
}
