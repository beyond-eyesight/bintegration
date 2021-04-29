package beyondeyesight.area.domain;


import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.core.geo.GeoJson;

import java.util.Objects;
import java.util.UUID;

@Getter
@TypeAlias("zone")
@Document(indexName="zones")
public class Zone {

    @Id
    private final UUID id;
    @Field(type = FieldType.Text)
    private final String name;
    private final GeoJson<? extends Iterable<?>> shape;

    public Zone(UUID id, String name, GeoJson<? extends Iterable<?>> shape) {
        this.id = id;
        this.name = name;
        this.shape = shape;
    }

    @Override
    public String toString() {
        return "Zone{" +
            "id=" + id +
            ", name='" + name + '\'' +
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
            Objects.equals(name, zone.name) &&
            Objects.equals(shape, zone.shape);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, shape);
    }
}
