package goodgame.multithread;

import goodgame.ChStatus;
import goodgame.EventWithList;
import goodgame.GetToken;
import goodgame.Main;
import goodgame.cahnnel.Channel;
import goodgame.connect.TestApp;

import static goodgame.connect.TestApp.queueMessages;
import static goodgame.connect.TestApp.websocketClientEndpointClass;

public class Processor extends Thread{
    /**
     * !убедиться что сообщения успевают обрабатываться!
     * !вселизь выделяет слишком много пользователей!
     * поток читает очередь сообщений и обрабатывает их
     *
     */

    public boolean doEvent;
    public boolean canRandomize;//false - лизнуть часть чата, true - карамелька дня
    public Channel channel;
    TimerThread timerThread;

    public Processor(){
        channel = new Channel("15365", "Verloin", 45*60*1000L, "undead");
//        channel = new Channel("183946", "LollyDragon", 45*60*1000L, "king");
//        channel = new Channel("23802", "LeMeldonium", 10*60*1000L, "king");
        timerThread = new TimerThread(channel.getPeriod(), channel.getId(), this);
        TestApp.testApp(GetToken.getToken(), channel);
    }

    @Override
    public void run() {
        String text;
        String afk = "{\"type\":\"channel_counters";
        String message;
        int afkCounter = 0;
        doEvent = false;
        canRandomize = false;
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        timerThread.start();
        while (true){ //проверка на заполененность очереди
                if (!queueMessages.isEmpty()) {
                    message = queueMessages.get(0);
                    if (message.contains("{\"type\":\"message")) {
                        text = Main.someoneAskedMe(message);
                        if (text != null) {
                            websocketClientEndpointClass.sendMessage(text);
                        }
                        afkCounter = 0;
                    } else if (message.contains("{\"type\":\"users_list")) {
                        System.out.println("получил список юзверей");
                        if (doEvent) {
                            websocketClientEndpointClass.sendMessage(EventWithList.withList(message, canRandomize, this));
                            doEvent = false;
                            canRandomize = false;
                        } else {
                            EventWithList.refreshList(message);
                            websocketClientEndpointClass.sendMessage(EventWithList.withList(message, canRandomize, this));
                        }
                        afkCounter = 0;
                    } else if (message.contains(afk)) {
                        afkCounter++;// отключится примерно через 5 минут так как автоматические
                        if (afkCounter > 5 * 6) {  // сообщения приходят каждые 10 секунд
                            ChStatus.getStatus();
                        }
                    }
                    System.out.println(message);
                    queueMessages.remove(0);
                }
            try {
                Thread.sleep(333);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public long getPeriod(){
        return channel.getPeriod();
    }

    public void setDoEvent(boolean doEvent){
        this.doEvent = doEvent;
    }

    public void setCanRandomize(boolean canRandomize){
        this.canRandomize = canRandomize;
    }
}
