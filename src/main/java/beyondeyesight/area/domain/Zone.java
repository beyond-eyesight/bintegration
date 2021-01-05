package beyondeyesight.area.domain;


import java.util.Objects;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.core.geo.GeoJson;

@TypeAlias("zone")
@Document(indexName="zones")
public class Zone {

    //todo: UUID로 바꾸기
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Zone zone = (Zone) o;
        return Objects.equals(id, zone.id) &&
            Objects.equals(shape, zone.shape);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, shape);
    }
}
