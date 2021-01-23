package beyondeyesight.area.domain;

import org.springframework.data.geo.Point;

public class PointDto {

    private Double lon;
    private Double lat;

    // todo: 테스트 - 이상한 글
    private PointDto(String lon, String lat) {
        this.lon = Double.parseDouble(lon);
        this.lat = Double.parseDouble(lat);
    }

    public static PointDto of(String lon, String lat) {
        return new PointDto(lon, lat);
    }

    public Point toPoint() {
        return new Point(this.lon, this.lat);
    }
}
