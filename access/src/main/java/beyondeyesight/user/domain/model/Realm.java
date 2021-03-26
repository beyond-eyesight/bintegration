package beyondeyesight.user.domain.model;

public class Realm {

    private String name;

    private Realm(String name) {
        this.name = name;
    }

    public static Realm from(String name) {
        return new Realm(name);
    }
}
