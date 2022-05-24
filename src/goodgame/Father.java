package goodgame;

import goodgame.cahnnel.Channel;
import goodgame.connect.ChatListener;
import goodgame.connect.NetIsAvailable;
import goodgame.multithread.Processor;
import goodgame.multithread.TimerThread;

import java.io.IOException;



public class Father {
    /**
     * Папаня запускает всё В-)
     */
    public static Channel channel = new Channel("15365", "Verloin", 45*60*1000L, "undead", "https://cdn.discordapp.com/attachments/655479213075202058/969137760755675146/771Xjpb413.gif");
//    public static String candy = "";
//    public static List<String> list = new LinkedList<>();
//    public static List<String> undeadList = new LinkedList<>(); //выключил из-за проблем с размером сообщений
//    public static TimerThread timerThread;
//    public static TimerTask task = null;
//    public static int event = 4; // 4 - базовое, если -1 то будет выбор карамельки
//    private static Processor processor;
//    private static boolean notReady = true;
//    public static ChatListener chatListener;
//    public static boolean streamIsAlive = false;

    public static void prepare() throws IOException, InterruptedException {
        DataBase.setNotReady(true);
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
        DataBase.setNotReady(false);
        DataBase.setEvent(4);
        DataBase.setCandy("");
        ChatListener.clearQ();
        DataBase.setStreamIsAlive(true);
        DataBase.setProcessor(new Processor(channel));
        DataBase.setChatListener(new ChatListener(GetToken.getToken(), DataBase.getProcessor()));
        DataBase.setTimerThread(new TimerThread(channel.getPeriod(), channel.getId(), DataBase.getProcessor()));
        Thread.sleep(1000);
        DataBase.getProcessor().start();
        Thread.sleep(2000);
        DataBase.getTimerThread().start();
        System.out.println(GetToken.getToken());
        System.out.println("количество активных потоков" + Thread.activeCount());
        while (DataBase.getStreamIsAlive()){
            Thread.sleep(10000);
//            System.out.println("в цикле " + DataBase.getStreamIsAlive());
        }
        System.out.println("стрим всё :(");
        closeSad();

    }

    public static void closeSad(){
        DataBase.getTask().cancel();
        System.out.println("завершаю");
        ChatListener.websocketClientEndpointClass.removeMessageHandler(); //не работает
        ChatListener.websocketClientEndpointClass.close(); //не работает
        DataBase.getProcessor().stop();
        DataBase.getTimerThread().stop();
        DataBase.getChatListener().stop();
        DataBase.setTimerThread(null);
        DataBase.setChatListener(null);
        DataBase.setCandy("");
        DataBase.setTask(null);
        DataBase.setEvent(4);
        DataBase.setNotReady(true);
        System.out.println("количество активных потоков" + Thread.activeCount());
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
        while (DataBase.getNotReady()){
            if (!new NetIsAvailable().internetIsAvailable()){
                //ждём подключения к интернету
                Thread.sleep(10000);
            } else { //быстрая проверка статуса стрима и наличие подключения к вебсокету
                System.out.println("инициатор - Father.85");
                if (new ChStatus().fastChannelStatus() && (ChatListener.websocketClientEndpointClass.isUserSessionNull())){
                    //снова подключаемся к стриму
                    System.out.println("стартую");
                    Father.letsRock();
                    DataBase.setNotReady(false);
                } else{
                    //ждём когда заработает стрим
                    System.out.println("жду 30 минут");
                    Thread.sleep(30*60*1000);//30*60*1000
                }
            }
        }
    }
}
