package goodgame;

import goodgame.cahnnel.Channel;
import goodgame.connect.ChatListener;
import goodgame.multithread.Processor;
import goodgame.multithread.TimerThread;

import java.util.LinkedList;
import java.util.List;
import java.util.TimerTask;

public class Father {
    /**
     * Папаня запускает всё В-)
     */
    public static Channel channel;
    public static String message;
    public static String candy = "";
    public static List<String> list = new LinkedList<>();
    public static List<String> undeadList = new LinkedList<>();
    public static TimerThread timerThread;
    public static TimerTask task = null;
    public static int event = 3; // 4 - базовое, если 0 то будет выбор карамельки

    public static void letsRock() throws InterruptedException {
        channel = new Channel("15365", "Verloin", 45*60*1000L, "undead");
//        channel = new Channel("183946", "LollyDragon", 45*60*1000L, "king");
//        channel = new Channel("23802", "LeMeldonium", 60*60*1000L, "king");
//        channel = new Channel("181598", "Cubinec", 30*60*1000L, "king");

        Processor processor = new Processor(channel);
        timerThread = new TimerThread(channel.getPeriod(), channel.getId(), processor);
        ChatListener.app(GetToken.getToken(), channel);
        Commands commands = new Commands(channel);
        Thread.sleep(1000);
        processor.start();
        Thread.sleep(2000);
        timerThread.start();
        System.out.println(GetToken.getToken());

    }
}
