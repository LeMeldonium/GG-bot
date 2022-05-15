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
    public static Channel channel = new Channel("15365", "Verloin", 45*60*1000L, "undead", "https://cdn.discordapp.com/attachments/655479213075202058/969137760755675146/771Xjpb413.gif");
    public static String message;
    public static String candy = "";
    public static List<String> list = new LinkedList<>();
    public static List<String> undeadList = new LinkedList<>(); //выключил из-за проблем с размером сообщений
    public static TimerThread timerThread;
    public static TimerTask task = null;
    public static int event = 4; // 4 - базовое, если -1 то будет выбор карамельки
    private static Processor processor;
    private static boolean notReady = true;
    public static ChatListener chatListener;
    public static boolean streamIsAlive = false;

    public static void prepare() throws IOException, InterruptedException {
        notReady = false;
        channel = new Channel("15365", "Verloin", 45*60*1000L, "undead", "https://cdn.discordapp.com/attachments/655479213075202058/969137760755675146/771Xjpb413.gif");
//        channel = new Channel("183946", "LollyDragon", 45*60*1000L, "king", "https://cdn.discordapp.com/attachments/655479213075202058/969135443574669332/2b01ea3f699e4ea6.gif");
//        channel = new Channel("23802", "LeMeldonium", 1*60*1000L, "king", "");
//        channel = new Channel("181598", "Cubinec", 30*60*1000L, "king");

//        ChatListener.app(GetToken.getToken(), channel, processor);
        Commands commands = new Commands(channel);
        System.out.println("инициатор - Father.39");
        if (new ChStatus().fastChannelStatus()){
            letsRock();
        } else {
            whatNext();
        }
    }

    public static void letsRock() throws InterruptedException {
        candy = "";
        ChatListener.clearQ();
        streamIsAlive = true;
        processor = new Processor(channel);
        chatListener = new ChatListener(GetToken.getToken(), processor);
        timerThread = new TimerThread(channel.getPeriod(), channel.getId(), processor);
        Thread.sleep(1000);
        processor.start();
        Thread.sleep(2000);
        timerThread.start();
        System.out.println(GetToken.getToken());
        while (!streamIsAlive){
            closeSad();
        }

    }

    public static void closeSad(){
        System.out.println("завершаю");
        ChatListener.websocketClientEndpointClass.removeMessageHandler();
        ChatListener.websocketClientEndpointClass.close();
        processor.stop();
        timerThread.stop();
        timerThread = null;
        chatListener = null;
        candy = "";
        task = null;
        event = 4;
        notReady = true;
//        undeadList = new LinkedList<>();
        try {
            whatNext();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            System.out.println("косяк с whatNext()");
            System.exit(0);
        }
    }

    public static void whatNext() throws IOException, InterruptedException {
        while (notReady){
            if (!new NetIsAvailable().internetIsAvailable()){
                //ждём подключения к интернету
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else { //быстрая проверка статуса стрима и наличие подключения к вебсокету
                System.out.println("инициатор - Father.85");
                if (new ChStatus().fastChannelStatus() && ChatListener.websocketClientEndpointClass.isUserSessionNull()){
                    //снова подключаемся к стриму
                    System.out.println("стартую");
                    Father.letsRock();
                    notReady = false;
                } else{
                    //ждём когда заработает стрим
                    System.out.println("жду 30 минут");
                    Thread.sleep(30*60*1000);
                }
            }
        }
    }
}
