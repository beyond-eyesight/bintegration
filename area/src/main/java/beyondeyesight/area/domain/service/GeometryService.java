package beyondeyesight.area.domain.service;

import beyondeyesight.area.domain.Zone;
import org.springframework.data.geo.Point;

public interface GeometryService {
    boolean contains(Zone zone, Point candidate);
}
