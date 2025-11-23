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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }



}
