package goodgame.cahnnel;

import goodgame.Father;
import goodgame.Time;

import java.util.List;
import java.util.Random;

import static goodgame.Father.candy;
import static goodgame.Father.channel;

public class MessageText {
    public static String lickBody(String name, String name1) {
        Random random = new Random();
        int rnd = random.nextInt(5*10) - 1;
        return switch (rnd%5) {
            case (-1) -> ":powernet3: " + name + ", :powernet7:" + name1 + " делает ультимативный лизь ";
            case (0) -> name + ", " + Lizaki.smile(7, channel.getName()) + "  с любовью лизь от " + name1;
            case (1) -> name + ", " + Lizaki.smile(7, channel.getName()) + "  со слюнями лизь от " + name1;
            case (2) -> name + ", " + Lizaki.smile(7, channel.getName()) + "  нежный лизь от " + name1;
            case (3) -> name + ", " + Lizaki.smile(7, channel.getName()) + "  просто лизь от " + name1;
            case (4) -> name + ", " + Lizaki.smile(7, channel.getName()) + "  почему-то мерзкий лизь от " + name1 + " , наверно язык был шершавый :verloin12: ";
            default -> name + ", " + Lizaki.smile(7, channel.getName()) + "  нежный от " + name1;
        };
    }

    public static String lickStreamer(String name) {
            if (name.equals(channel.getName())) {
                return channel.getName() + ", " + Lizaki.smile(8, channel.getName()) + " ШОК! " + Users.userName(name) + " лижет себя";
            } else {
                return channel.getName() + ", " + Lizaki.smile(8, channel.getName()) + " " + Users.userName(name) + " лижет стримера";
            }
    }

    public static String lickSelf(String name) {
        return name + ", " + Lizaki.smile(7, channel.getName()) + "";
    }

    public static String lickHelp() {
        return "Команды: !лизь - лизнуть стримера, <ник> -лизь - лизнуть кого-то, хочу лизь - лизнуть себя, !хохо - лизнуть прохожего "; //, вселизь - [данные удалены]
    }

    public static String candy(String name) {
        if (candy.equals("")){
            return "До назначения карамельки осталось всего " + Time.getTime(Father.event) + " ";
//            return "Карамельку ещё не выбрали :sad: ";
        }

        return candy + ", " + Lizaki.smile(7, channel.getName()) + name + " с довольным лицом лижет карамельку ";
    }

    public static String candyOfTheStream(String name) {
        return name + ", выбирается карамелькой дня" + ", -лизь к бою! ";
    }

    public static String JACKPOT(String name) {
        return name + ", :verloin30::gold::verloinking: ";
    }

    public static String randomLick(String name, String nameLicker) {
        Random random = new Random();
        int rnd = random.nextInt(3);
        return switch (rnd) {
            case (0) -> name + ", " + Lizaki.smile(7, channel.getName()) + nameLicker + " делает неожиданный лизь ";
            case (1) -> name + ", " + Lizaki.smile(7, channel.getName()) + nameLicker + " лизнул и скрылся ";
            default -> name + ", " + Lizaki.smile(7, channel.getName()) + " быстро, резко, дерзко " + nameLicker + " делает лизь ";
        };
    }

    public static String hello(String name) {
        return name + ", :verloin30::gold::verloinking: ";
    }

    public static String lickNotAll(List<String> usersList) {
        String txt = "";
        for (String name: usersList){
            txt += name + ", ";
        }
        if ((Father.event%5 - 1) ==3) {
            System.out.println("==0");
            return txt + " а ну быстро лизнули стримера! :verloingold:";
        }
        return txt + " сделал лизь - сердцу радость :brat_loken5:";
    }

    public static String undeadLick(List<String> userList, String undead) {
        String txt = "";
        for (String name: userList){
            txt += name + ", ";
        }
        return txt + " именем " + undead + " ! Лизитесь! ";
    }

    public static String taunt(){
        return " ну посмотрите на " + channel.getName() + ", так и хочется !лизьнуть ";
    }
}
