package Courier;

import java.util.concurrent.ThreadLocalRandom;

public class Creds {

    private final String login;
    private final String password;


    public Creds(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public static Creds WithoutLogin() {
        return new Creds(null, "1234");
    }

    public static Creds WithoutPassword() {
        return new Creds("Ninja" + ThreadLocalRandom.current().nextInt(1000, 100_10000), null);
    }

    public static Creds empty() {
        return new Creds(null, null);
    }


    public static Creds getCreds(Courier one) {
        return new Creds(one.getLogin(), one.getPassword());
    }

    public String getLogin() {
        return login;
    }


    public String getPassword() {
        return password;
    }

}