# Zone

Zone은 특정 의미를 갖는 공간을 의미한다. 현재 Area 서비스에서는 다음과 같은 Zone을 지원한다.

## 1. hy-international 관련

다음 Zone들은 [hy-international](https://github.com/beyond-eyesight/hy-international) 어플리케이션에서 활용하는 공간들이다. 이 Zone들의 특징은 한양대학교에서 학생들이 주로 활동하는 공간들이라는 것이다. Zone의 구성요소인 Point들은 한양대학교 졸업생의 의견에 따라 정하였다.

```java
Zone wangsimni = new Zone(WANGSIMNI_ID, "Wangsimni",
    GeoJsonPolygon.of(
        Arrays.asList(
            new Point(37.56186460715209, 127.03878873296458),
            new Point(37.55827838080759, 127.03892370195969),
            new Point(37.55753238253602, 127.04159778997553),
            new Point(37.561966294018255, 127.04168241338516),
            new Point(37.56186460715209, 127.03878873296458))
    )
);
```

```java
Zone campus = new Zone(CAMPUS_ID, "Campus", GeoJsonPolygon.of(
    Arrays.asList(
        new Point(37.55779745949392, 127.04097013642883),
        new Point(37.558572069069136, 127.04055639416698),
        new Point(37.56017011204165, 127.04097893944592),
        new Point(37.56141921346345, 127.0447290288554),
        new Point(37.559695587571696, 127.04710584593279),
        new Point(37.56047715566452, 127.05004605689999),
        new Point(37.55702982040824, 127.0515601774627),
        new Point(37.55433871607011, 127.04819624892356),
        new Point(37.5538154114159, 127.04348753393771),
        new Point(37.55779745949392, 127.04097013642883)
    )
));
```

```java
Zone dormitory = new Zone(DORMITORY_ID, "Dormitory", GeoJsonPolygon.of(
    Arrays.asList(
        new Point(37.56089445091836, 127.04548536257013),
        new Point(37.55946005643694, 127.04484811300844),
        new Point(37.55792584743151, 127.04775900606796),
        new Point(37.55977812020412, 127.05022146425075),
        new Point(37.56089445091836, 127.04548536257013)
    )
));
```

