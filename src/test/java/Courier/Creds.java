package Courier;

public class Creds {

    private final String login;
    private final String password;


    public Creds(String login, String password) {
        this.login = login;
        this.password = password;
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