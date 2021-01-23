package beyondeyesight.area.infra.service;

import beyondeyesight.area.domain.Zone;
import beyondeyesight.area.domain.service.GeometryService;
import com.esri.core.geometry.GeoJsonImportFlags;
import com.esri.core.geometry.Geometry;
import com.esri.core.geometry.Geometry.Type;
import com.esri.core.geometry.OperatorContains;
import com.esri.core.geometry.OperatorImportFromGeoJson;
import org.springframework.data.elasticsearch.core.geo.GeoJson;
import org.springframework.data.elasticsearch.core.geo.GeoJsonPoint;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

@Service
public class ESRIGeometryService implements GeometryService {

    @Override
    public boolean contains(Zone zone, Point candidate) {

        Geometry shape = convertToGeometry(zone.getShape(), Type.Polygon);
        Geometry point = convertToGeometry(GeoJsonPoint.of(candidate), Type.Point);
        // todo: sr에 일단 잘 모르고 null을 넣었는데, 확인!
        return OperatorContains.local().execute(shape, point, null, null);
    }

    private Geometry convertToGeometry(GeoJson<? extends Iterable<?>> shape, Type type) {
        return OperatorImportFromGeoJson.local()
            .execute(GeoJsonImportFlags.geoJsonImportDefaults, type, shape.toJson(),
                null).getGeometry();
    }
}
