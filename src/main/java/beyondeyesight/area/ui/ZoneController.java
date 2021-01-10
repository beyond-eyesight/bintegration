package beyondeyesight.area.ui;

import beyondeyesight.area.domain.Zone;
import beyondeyesight.area.domain.service.ZoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/zones")
@RequiredArgsConstructor
public class ZoneController {

    private final ZoneService zoneService;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Zone> getAll() {
        return zoneService.getAll().orElseThrow(IllegalStateException::new);
    }
}

