package goodgame.cahnnel;

import goodgame.Father;

import java.util.Random;

public class Smiles {
    public static String lizaki(int num, String name){
        Random random = new Random();
        int number = 7;
        int rnd;
        if (num == 8){
            rnd = random.nextInt(number * 6) - 1;
        } else {
            rnd = random.nextInt(number * 3);
        }
        rnd %= 7;
        return switch (rnd) {
            case (-1) -> Father.channel.getSmile();
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

    public static String kusaki(){
        String str = "";
        Random random = new Random();
        int number = 10;
        int rnd;
        rnd = random.nextInt(number * 10);
        rnd %= 2;
        return switch (rnd) {
            case (0) -> str += ":tanushkavl8:";
            case (1) -> str += ":hell_girl18:";
            default -> str += ":hell_girl18:";
        };
    }

    public static String smilesAll(){
        return " :powernet3::powernet7::verloinsilver::verloin23::frozzagold::hell_girl17::wolwerine047::brat_loken8:";
    }
}
