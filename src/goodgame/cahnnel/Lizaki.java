package goodgame.cahnnel;

import java.util.Random;

public class Lizaki {
    public static String smile(int num, String name){
        Random random = new Random();
        int number = 7;
        int rnd;
        if (num == 8 && name.contains("Verloin")){
            rnd = random.nextInt(number * 6) - 1;
        } else {
            rnd = random.nextInt(number * 3);
        }
        rnd %= 7;
        return switch (rnd) {
            case (-1) -> "https://media.discordapp.net/attachments/283554046550474752/732977097945120859/771Xjpb413.gif";
            case (0) -> ":verloinsilver:";
            case (1) -> ":verloin23:";
            case (2) -> ":frozzagold:";
            case (3) -> ":hell_girl17:";
            case (4) -> ":wolwerine047:";
            case (5) -> ":brat_loken8:";
            case (6) -> ":powernet7:";
            default -> "brat_loken5";
        };
    }

    public static String smilesAll(){
        return " :powernet3::powernet7::verloinsilver::verloin23::frozzagold::hell_girl17::wolwerine047::brat_loken8:\",";
    }
}
