package beyondeyesight.area.domain.service;


import beyondeyesight.area.domain.Zone;
import beyondeyesight.area.domain.repository.ZoneRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ZoneService {
    private final ZoneRepository zoneRepository;
    private final GeometryService geometryService;

    public Optional<List<Zone>> getAll() {
        return Optional.of(zoneRepository.findAll());
    }

    public Optional<Zone> getById(UUID id) {
        return Optional.of(zoneRepository.findById(id));
    }

    public boolean contains(Zone zone, Point point) {
        return geometryService.contains(zone, point);
    }

}
