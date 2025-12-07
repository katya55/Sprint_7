package Courier;

import java.util.concurrent.ThreadLocalRandom;

public class Courier {

    private String login;
    private String password;
    private String firstName;

    public Courier(String login, String password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }

    public static Courier generateRandomCourier() {
        String login = "Ninja" + ThreadLocalRandom.current().nextInt(1000, 100_10000);
        return new Courier(login, "1234", "saske");
    }


    public static Courier withNoLogin() {
        return new Courier(null, "1234", "saske");
    }

    public static Courier withNoPassword() {
        return new Courier("Ninja" + ThreadLocalRandom.current().nextInt(1000, 100_10000), null, "saske");
    }

    public static Courier withoutFirstName() {
        return new Courier("Ninja" + ThreadLocalRandom.current().nextInt(1000, 100_10000), "1234", null);
    }


    public String getPassword() {
        return password;
    }


    public String getLogin() {
        return login;
    }



}
