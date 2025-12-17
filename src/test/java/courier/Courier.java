package courier;
import lombok.Value;
import net.datafaker.Faker;

import java.util.concurrent.ThreadLocalRandom;

@Value
public class Courier {

    private static final Faker faker = new Faker();

    String login;
    String password;
    String firstName;

    public static Courier generateRandomCourier() {
        String login = faker.name().firstName()  + ThreadLocalRandom.current().nextInt(1000, 100_10000);
        return new Courier(login, "1234", "saske");
    }

    public static Courier withNoLogin() {
        return new Courier(null, "1234", "saske");
    }

    public static Courier withNoPassword() {
        return new Courier( faker.name().firstName() + ThreadLocalRandom.current().nextInt(1000, 100_10000), "", "saske");
    }

    public static Courier withoutFirstName() {
        return new Courier(faker.name().firstName() + ThreadLocalRandom.current().nextInt(1000, 100_10000), "1234", "");
    }


}
