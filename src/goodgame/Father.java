package goodgame;

import goodgame.cahnnel.Channel;
import goodgame.connect.ChatListener;
import goodgame.connect.NetIsAvailable;
import goodgame.multithread.Processor;
import goodgame.multithread.TimerThread;

import java.io.IOException;
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
    public static List<String> undeadList = new LinkedList<>(); //выключил из-за проблем с размером сообщений
    public static TimerThread timerThread;
    public static TimerTask task = null;
    public static int event = 4; // 4 - базовое, если -1 то будет выбор карамельки
    private static Processor processor;

    public static void letsRock() throws InterruptedException {
        channel = new Channel("15365", "Verloin", 45*60*1000L, "undead");
//        channel = new Channel("183946", "LollyDragon", 45*60*1000L, "king");
//        channel = new Channel("23802", "LeMeldonium", 1*60*1000L, "king");
//        channel = new Channel("181598", "Cubinec", 30*60*1000L, "king");

        Processor processor = new Processor(channel);
        timerThread = new TimerThread(channel.getPeriod(), channel.getId(), processor);
        ChatListener.app(GetToken.getToken(), channel, processor);
        Commands commands = new Commands(channel);
        Thread.sleep(1000);
        processor.start();
        Thread.sleep(2000);
        timerThread.start();
        System.out.println(GetToken.getToken());

    }

    public static void closeSad(){
        processor.stop();
        timerThread.stop();
        try {
            whatNext();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public static void whatNext() throws IOException, InterruptedException {
        boolean notReady = true;
        while (notReady){
            if (!new NetIsAvailable().internetIsAvailable()){
                //ждём подключения к интернету
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else { //быстрая проверка статуса стрима и наличие подключения к вебсокету
                if (new ChStatus().fastChannelStatus() && !ChatListener.websocketClientEndpointClass.getUserSession()){
                    //снова подключаемся к стриму
                    Father.letsRock();
                    notReady = false;
                } else{
                    //ждём когда заработает стрим
                    Thread.sleep(30*60*1000);
                }
            }
        }
    }
}
