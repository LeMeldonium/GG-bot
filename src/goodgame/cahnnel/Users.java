package goodgame.cahnnel;

public class Users {
    public static String userName(String name) {
        if (name.length() > 6) {
            return "Лизь" + name.substring(3);
        }
        return name.substring(0, name.length() - 2) + "Лизь";
        /*
        switch (name) {
            case ("Linfred"):
                return "Лизьfred";
            case ("DerekCorpse"):
                return "DerekЛизьpse";
            case ("LeMeldonium"):
                return "Лизьeldonium";
            case ("Verloin"):
                return "Лизьloin";
            case ("Gojji"):
                return "Лизьjji";
            case ("--AXEL--"):
                return "--AXEЛизь--";
            case ("KostebraK"):
                return "ЛизьstebraK";
            case ("chartv"):
                return "chartЛизь";
        }
        return name;
         */
    }
}
