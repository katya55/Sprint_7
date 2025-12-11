package Courier;

import lombok.Value;
import net.datafaker.Faker;

import java.util.concurrent.ThreadLocalRandom;

@Value
public class Creds {
   public static Faker faker = new Faker();
    String login;
    String password;

    public static Creds withoutLogin() {
        return new Creds(null, "1234");
    }

    public static Creds withoutPassword() {
        return new Creds(faker.name().firstName() + ThreadLocalRandom.current().nextInt(1000, 100_10000), null);
    }

    public static Creds empty() {
        return new Creds(null, null);
    }


    public static Creds getCreds(Courier one) {
        return new Creds(one.getLogin(), one.getPassword());
    }


}