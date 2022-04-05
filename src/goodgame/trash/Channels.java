package goodgame.trash;

public class Channels {

    public static String channelId(String chName){
        return switch (chName) {
            case ("LeMeldonium") -> "23802";
            case ("LollyDragon") -> "183946";
            case ("Verloin") -> "15365";
            case ("Brat_Loken") -> "161950";
            default -> null;
        };

    }
}
