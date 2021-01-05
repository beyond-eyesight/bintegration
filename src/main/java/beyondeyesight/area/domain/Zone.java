package beyondeyesight.area.domain;


import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.elasticsearch.core.geo.GeoJson;

@Getter
@TypeAlias("zone")
public class Zone {
    @Id
    private final String id;
    private final GeoJson<? extends Iterable<?>> shape;

    public Zone(String id, GeoJson<? extends Iterable<?>> shape) {
        this.id = id;
        this.shape = shape;
    }

    @Override
    public String toString() {
        return "Zone{" +
            "id='" + id + '\'' +
            ", shape=" + shape +
            '}';
    }
}
