package goodgame.connect;


import goodgame.*;
import goodgame.cahnnel.Channel;
import goodgame.cahnnel.Lizaki;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class TestApp {
    public static WebsocketClientEndpointClass websocketClientEndpointClass;
    public static String message = "";
    public static Date lastTime;
    public static String text;
    public static List<String> list;
    public static TimerTask task = null;
    public static int event = 3; // 3 - базовое, если 0 то будет выбор карамельки
    public static String afk = "{\"type\":\"channel_counters";
    public static int afkCounter = 0;
    public static boolean doEvent = false;
    public static boolean canRandomize = false; //false - лизнуть часть чата, true - лизатий дня

    public static void testApp(String token, Channel channel){
        try {
            // open websocket
            websocketClientEndpointClass = new WebsocketClientEndpointClass(new URI("wss://chat.goodgame.ru/chat/websocket"));

            // add listener
            try {
                websocketClientEndpointClass.addMessageHandler(new WebsocketClientEndpointClass.MessageHandler() {
                    public void handleMessage(String message) {
                        if (message.contains("{\"type\":\"message")) {
                            text = Main.someoneAskedMe(message);
                            if(text != null){
                                websocketClientEndpointClass.sendMessage(text);
                            }
                            afkCounter = 0;
                        } else if (message.contains("{\"type\":\"users_list")){
                            System.out.println("получил список юзверей");
                            if (!doEvent) {
                                EventWithList.refreshList(message);
                            } else {
                                websocketClientEndpointClass.sendMessage(EventWithList.withList(message, canRandomize));
                                doEvent = false;
                            }
                            afkCounter = 0;
                        } else if (message.contains(afk)){
                            afkCounter++;// отключится примерно через 5 минут так как автоматические
                            if (afkCounter > 5*6) {  // сообщения приходят каждые 10 секунд
                                ChStatus.getStatus();
                            }
                        }

                        System.out.println(message);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }

            // send message to websocket
            websocketClientEndpointClass.sendMessage("{" +
                    "    \"type\": \"auth\"," +
                    "    \"data\": {" +
                    "        \"user_id\": \"281724\"," +
                    "        \"token\": \"" + token + "\"" +
                    "    }" +
                    "}");

            // wait 5 seconds for messages from websocket
            Thread.sleep(2000);

            websocketClientEndpointClass.sendMessage("{" +
                    "    \"type\": \"join\"," +
                    "    \"data\": {" +
                    "        \"channel_id\": \"" + channel.getId() + "\"," +
                    "        \"hidden\": false" +
                    "    }\n" +
                    "}");

            Thread.sleep(2000);

            websocketClientEndpointClass.sendMessage("{\n" +
                    "    \"type\": \"get_users_list2\",\n" +
                    "    \"data\": {\n" +
                    "        \"channel_id\": \""+channel.getId()+"\"\n" +
                    "    }\n" +
                    "}");

            Thread.sleep(2000);

//            websocketClientEndpointClass.sendMessage(Main.Hello());

            Thread.sleep(2000);
/*
  таймер каждые 60 минут. Если стрим выключен то выключается бот
  в идеале бот должен перейти в ожидание
 */

Thread.sleep(3500);
            task = new TimerTask() {
                public void run() {
                    if(ChStatus.getStatus()) { //каждый "период" ивент уменьшается на 1. если ивент -1
                        switch (event%4 - 1) { //то срабатывает выбор карамельки (разовый ивент)
            /* 3 часа 59 минут */                case (2) -> websocketClientEndpointClass.sendMessage(Main.message(Lizaki.smilesAll()));
            /* 2 часа 59 минут */                case (1) -> websocketClientEndpointClass.sendMessage(Main.message(Pictures.liveWithLic()));
            /* 1 час 59 минут */                 case (0) -> websocketClientEndpointClass.sendMessage(Main.taunt());
            /* 59 минут */                case (-1) -> {
                                doEvent = true;
                                websocketClientEndpointClass.sendMessage(Commands.getUserList(channel.getId()));
                            }
                            case (-2) -> {
                                canRandomize = true;
                                doEvent = true;
                                websocketClientEndpointClass.sendMessage(Commands.getUserList(channel.getId()));
                                event = 23;
                            }
                            default -> websocketClientEndpointClass.sendMessage(Main.message(Pictures.liveWithLic()));
                        }
                        System.out.println(event);
                        event--;
                    } else {
                        System.exit(0);
                    }
                }
            };
            Timer timer = new Timer("Timer");
            long delay = 1000L;
            timer.scheduleAtFixedRate (task, delay, channel.getPeriod());


/*
                 //запрос истории сообщений
                websocketClientEndpointClass.sendMessage("{" +
                        "    \"type\": \"get_channel_history\"," +
                        "    \"data\": {" +
                        "        \"channel_id\": \"" + channel.getId() + "\"" +
                        "    }" +
                        "}");
                Thread.sleep(2000);
*/

        } catch (InterruptedException ex) {
            System.err.println("InterruptedException exception: " + ex.getMessage());
        } catch (URISyntaxException ex) {
            System.err.println("URISyntaxException exception: " + ex.getMessage());
        }
    }
}
