package beyondeyesight.area.ui;

import beyondeyesight.area.domain.PointDto;
import beyondeyesight.area.domain.Zone;
import beyondeyesight.area.domain.service.ZoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

    // todo: restful 찾아보기
    @GetMapping(value = "/contains")
    public boolean contains(@RequestParam(value = "name") String zoneName,
        @RequestParam(value = "lon") String lon, @RequestParam(value = "lat") String lat) {
        return zoneService.contains(zoneName, PointDto.of(lon, lat).toPoint());
    }


}

