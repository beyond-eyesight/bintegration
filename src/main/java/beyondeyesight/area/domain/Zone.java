package beyondeyesight.area.domain;


import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.elasticsearch.core.geo.GeoJson;
import org.springframework.data.elasticsearch.core.geo.GeoJsonPolygon;

@Getter
@TypeAlias("zone")
public class Zone {
    @Id
    private final String id;
    // todo: generic
    private final GeoJson shape;

    public Zone(String id, GeoJsonPolygon shape) {
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
