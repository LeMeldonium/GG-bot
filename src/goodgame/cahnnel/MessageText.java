package goodgame.cahnnel;

import goodgame.Time;
import goodgame.connect.TestApp;

import java.util.List;
import java.util.Random;

import static goodgame.Main.channel;
import static goodgame.Main.licker;

public class MessageText {
    public static String lickBody(String name, String name1) {
        Random random = new Random();
        int rnd = random.nextInt(5*10) - 1;
        return switch (rnd%5) {
            case (-1) -> ":powernet3:" + name + ", :powernet7:" + name1 + " делает ультимативный лизь \",\n";
            case (0) -> name + ", " + Lizaki.smile(7, channel.getName()) + "  с любовью лизь от " + name1 + "\",\n";
            case (1) -> name + ", " + Lizaki.smile(7, channel.getName()) + "  со слюнями лизь от " + name1 + "\",\n";
            case (2) -> name + ", " + Lizaki.smile(7, channel.getName()) + "  нежный лизь от " + name1 + "\",\n";
            case (3) -> name + ", " + Lizaki.smile(7, channel.getName()) + "  просто лизь от " + name1 + "\",\n";
            case (4) -> name + ", " + Lizaki.smile(7, channel.getName()) + "  почему-то мерзкий лизь от " + name1 + " , наверно язык был шершавый :verloin12: \",\n";
            default -> name + ", " + Lizaki.smile(7, channel.getName()) + "  нежный от " + name1 + "\",\n";
        };
    }

    public static String lickStreamer(String name) {
            if (name.equals(channel.getName())) {
                return channel.getName() + ", " + Lizaki.smile(8, channel.getName()) + " ШОК! " + Users.userName(name) + " лижет себя\",";
            } else {
                return channel.getName() + ", " + Lizaki.smile(8, channel.getName()) + " " + Users.userName(name) + " лижет стримера\",";
            }
    }

    public static String lickSelf(String name) {
        return name + ", " + Lizaki.smile(7, channel.getName()) + "\",";
    }

    public static String lickHelp() {
        return "Команды: !лизь - лизнуть стримера, <ник> -лизь - лизнуть кого-то, хочу лизь - лизнуть себя, !хохо - лизнуть прохожего, !вселизь - [данные удалены] \",";
    }

    public static String candy() {
        if (licker.equals("")){
            return "До назначения карамельки осталось всего " + Time.getTime(TestApp.event) + " \",";
//            return "Карамельку ещё не выбрали :sad: \",";
        }
        return licker + " сегодня карамелька \",";
    }

    public static String lickOfTheStream(String name) {
        return "Карамелькой дня выбирается " + name + ", -лизь к бою! \",";
    }

    public static String JACKPOT(String name) {
        return name + ", :verloin30::gold::verloinking: \",";
    }

    public static String randomLick(String name, String nameLicker) {
        Random random = new Random();
        int rnd = random.nextInt(3);
        return switch (rnd) {
            case (0) -> name + ", " + Lizaki.smile(7, channel.getName()) + nameLicker + " делает неожиданный лизь \",";
            case (1) -> name + ", " + Lizaki.smile(7, channel.getName()) + nameLicker + " лизнул и скрылся \",";
            default -> name + ", " + Lizaki.smile(7, channel.getName()) + " быстро, резко, дерзко " + nameLicker + " делает лизь \",";
        };
    }

    public static String hello(String name) {
        return name + ", :verloin30::gold::verloinking: \",";
    }

    public static String lickNotAll(List<String> usersList) {
        String txt = "";
        for (String name: usersList){
            txt += name + ", ";
        }
        return txt + " сделал лизь - сердцу радость :brat_loken5:\",";
    }

    public static String undeadLick(List<String> userList, String undead) {
        String txt = "";
        for (String name: userList){
            txt += name + ", ";
        }
        return txt + " именем " + undead + " ! Лизитесь! \",";
    }
}
