package goodgame.cahnnel;

public class Users {
    public static String userName(String name) {
        if (name.length() > 6) {
            return "Лизь" + name.substring(3);
        }
        return name.substring(0, name.length() - 2) + "Лизь";
    }
}
