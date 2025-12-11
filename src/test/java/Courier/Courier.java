package Courier;
import lombok.Value;
import net.datafaker.Faker;

import java.util.concurrent.ThreadLocalRandom;

@Value
public class Courier {

    private static final Faker faker = new Faker();

    private String login;
    private String password;
    private String firstName;

    public Courier(String login, String password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }

    public static Courier generateRandomCourier() {
        String login = faker.name().firstName()  + ThreadLocalRandom.current().nextInt(1000, 100_10000);
        return new Courier(login, "1234", "saske");
    }

    public static Courier withNoLogin() {
        return new Courier(null, "1234", "saske");
    }

    public static Courier withNoPassword() {
        return new Courier( faker.name().firstName() + ThreadLocalRandom.current().nextInt(1000, 100_10000), null, "saske");
    }

    public static Courier withoutFirstName() {
        return new Courier(faker.name().firstName() + ThreadLocalRandom.current().nextInt(1000, 100_10000), "1234", null);
    }


}
