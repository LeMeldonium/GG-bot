package goodgame.multithread;

import goodgame.ChStatus;
import goodgame.Commands;
import goodgame.EventWithList;
import goodgame.cahnnel.Channel;

import static goodgame.connect.ChatListener.queueMessages;
import static goodgame.connect.ChatListener.websocketClientEndpointClass;

public class Processor extends Thread{
    /**
     * !убедиться что сообщения успевают обрабатываться!
     * !вселизь выделяет слишком много пользователей!
     * поток читает очередь сообщений и обрабатывает их
     *
     */

    public String lastMessage = "";
    public boolean doEvent = false;
    public boolean makeCandy = false;//false - лизнуть часть чата, true - карамелька дня
    String afk = "{\"type\":\"channel_counters";
    Channel channel;
    int afkCounter = 0;

    public Processor(Channel channel){
        this.channel = channel;
    }

    @Override
    public void run() {
        System.out.println("процессор стартанул");
        String text;
        String message;
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        websocketClientEndpointClass.sendMessage(Requests.getUserList());//костыль
        while (true){ //проверка на заполененность очереди
                if (!queueMessages.isEmpty()) {
                    message = queueMessages.get(0);
                    System.out.println("очередь." + queueMessages.size() + message);
                    if (message.contains("{\"type\":\"message")) {
                        text = Commands.someoneAskedMe(message);
                        if (!lastMessage.contains(text) && text != null) {
                            lastMessage = text;
                            websocketClientEndpointClass.sendMessage(text);
                        }
                        afkCounter = 0;
//                    } else if (message.contains("{\"type\":\"users_list")) {
//                        EventWithList.wasRefreshedNow = false;
//                        System.out.println("получил список юзверей");
//                        if (doEvent) {
//                            websocketClientEndpointClass.sendMessage(EventWithList.withList(message, makeCandy));
//                            doEvent = false;
//                            makeCandy = false;
//                        } else {
//                            EventWithList.refreshList(message);
////                            websocketClientEndpointClass.sendMessage(EventWithList.withList(message, makeCandy));
//                        }
//                        afkCounter = 0;
                    } else if (message.contains(afk)) {
                        afkCounter++;// отключится примерно через 5 минут так как автоматические
                        if (afkCounter > 5 * 6) {  // сообщения приходят каждые 10 секунд
                            System.out.println("инициатор - Processor.64");
                            ChStatus.getStatus();
                        }
                    }
                    queueMessages.remove(0);
                }
            try {
                Thread.sleep(333);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void aaaplayer(String message){
//        EventWithList.wasRefreshedNow = true;
        System.out.println("получил список юзверей");
        if (doEvent) {
            websocketClientEndpointClass.sendMessage(EventWithList.withList(message, makeCandy));
            doEvent = false;
            makeCandy = false;
        } else {
            EventWithList.refreshList(message);
//                            websocketClientEndpointClass.sendMessage(EventWithList.withList(message, makeCandy));
        }
    }

    public long getPeriod(){
        return channel.getPeriod();
    }

    public void setDoEvent(boolean doEvent){
        this.doEvent = doEvent;
    }

    public void setMakeCandy(boolean makeCandy){
        this.makeCandy = makeCandy;
    }
}
