package beyondeyesight.area.study.geomtry;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.esri.core.geometry.GeoJsonImportFlags;
import com.esri.core.geometry.Geometry.Type;
import com.esri.core.geometry.OperatorContains;
import com.esri.core.geometry.OperatorImportFromGeoJson;
import com.esri.core.geometry.Point;
import com.esri.core.geometry.Polygon;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.data.elasticsearch.core.geo.GeoJsonPoint;
import org.springframework.data.elasticsearch.core.geo.GeoJsonPolygon;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

public class OperatorContainsTest {

    @ParameterizedTest
    @MethodSource("provideSpaceAndPoint")
    void contains(GeoJsonPolygon space, GeoJsonPoint candidate, boolean expected) {
        Polygon polygon = (Polygon) OperatorImportFromGeoJson.local()
            .execute(GeoJsonImportFlags.geoJsonImportDefaults, Type.Polygon, space.toJson(),
                null).getGeometry();

        Point point = (Point) OperatorImportFromGeoJson.local()
            .execute(GeoJsonImportFlags.geoJsonImportDefaults, Type.Point, candidate.toJson(),
                null).getGeometry();

        assertThat(OperatorContains.local().execute(polygon, point, null, null)).isEqualTo(expected);
    }

    private static Stream<Arguments> provideSpaceAndPoint() {
        GeoJsonPolygon space = GeoJsonPolygon.of(
            new GeoPoint(37.56186460715209, 127.03878873296458),
            new GeoPoint(37.55827838080759, 127.03892370195969),
            new GeoPoint(37.55753238253602, 127.04159778997553),
            new GeoPoint(37.561966294018255, 127.04168241338516),
            new GeoPoint(37.56186460715209, 127.03878873296458)
        );

        return Stream.of(
            Arguments.of(space, GeoJsonPoint.of(new GeoPoint(37.56125753120465, 127.0404482554297)), true),
            Arguments.of(space, GeoJsonPoint.of(new GeoPoint(37.56183083473308, 127.06288089200152)), false)
        );
    }
}
