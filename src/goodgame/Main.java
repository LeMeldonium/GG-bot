package goodgame;


import goodgame.cahnnel.Channel;

import java.util.LinkedList;
import java.util.List;

/**
 * -сделать получаение статуса более универсальным (для вселизя)
 * -бот не все команды отрабатывает если их очень много. Предлагаю вести список команд
 * надо сделать тред для проверки активных команд
 */

public class Main {
    public static Channel channel;
    public static String message;
    public static String candy = "";
    public static List<String> list = new LinkedList<>();
    public static List<String> undeadList = new LinkedList<>();

    public static void main(String[] args) throws InterruptedException {

//        channel = new Channel("15365", "Verloin", 45*60*1000L, "undead");
//        channel = new Channel("183946", "LollyDragon", 45*60*1000L, "king");
//        channel = new Channel("23802", "LeMeldonium", 60*60*1000L, "king");
//        channel = new Channel("181598", "Cubinec", 30*60*1000L, "king");
        int i = 0;
//        Processor processor = new Processor();
        while (i < 1) { /** логика не закончена. Тут должно происходить переподключение к каналу
                        * но я не убедился в надобности этого. Сейчас при реконнекте не теряется
                        * соединение с чатом
                        *
                        * Хочу сделать один метод, который будет ко всему подключаться потому что
                        * у меня проблема с импортом :(
                        */
            Father.letsRock();
//            processor.start();
//            System.out.println(GetToken.getToken());
            Thread.sleep(300000);
             i++;
        }
    }

//    public static String someoneAskedMe(String message){
//        String newMessage;
//        String name;
//        String name1;
//        String text = null;
//        if (message.contains("Команды:")){
//            text = "";
//        } else if (message.contains("-лизь")){
//            System.out.println(message.lastIndexOf("user_name\":\""));
//            newMessage = message.substring(message.lastIndexOf("user_name\":\"")+12);
//            name1 = newMessage.substring(0, newMessage.indexOf('\"'));
//            newMessage = message.substring(message.lastIndexOf("text\":\"")+7);
//            name = newMessage.substring(0, newMessage.indexOf(','));
//            text = Requests.sendMessage(MessageText.lickBody(name, name1));
//        } else if (message.contains("!лизь")){
//            newMessage = message.substring(message.lastIndexOf("user_name\":\"")+12);
//            name = newMessage.substring(0, newMessage.indexOf('\"'));
//            text = Requests.sendMessage(MessageText.lickStreamer(name));
////        } else if (message.contains("Казино закрыто на модификацию!")){
////            newMessage = message.substring(message.lastIndexOf("\"text\":\"")+7);
////            name1 = newMessage.substring(1, newMessage.indexOf(' '));
////            System.out.println(name1);
////            text = Requests.sendMessage(channel.getName() + ", " + name1 + " возмущён закрытым казино и просит передать :Verloin4:");
//        } else if (message.contains("хочу лизь")){
//            newMessage = message.substring(message.lastIndexOf("user_name")+12);
//            name = newMessage.substring(0, newMessage.indexOf('"'));
//            text = Requests.sendMessage(MessageText.lickSelf(name));
//        } else if (message.contains("!rfpbyj")||message.contains("!help")){
//            text = Requests.sendMessage(MessageText.lickHelp());
//        } else if (message.contains("!карамель")){
//            newMessage = message.substring(message.lastIndexOf("user_name")+12);
//            name = newMessage.substring(0, newMessage.indexOf('\"'));
//            text = Requests.sendMessage(MessageText.candy(name));
//        } else if (message.contains("ДЖЕКПОТ")){
//            newMessage = message.substring(message.lastIndexOf("text\":")+7);
//            name = newMessage.substring(0, newMessage.indexOf(','));
//            text = Requests.sendMessage(MessageText.JACKPOT(name));
//        } else if (message.contains("!хохо")){
//            ChatListener.websocketClientEndpointClass.sendMessage(Requests.getUserList(channel.getId()));
//            newMessage = message.substring(message.lastIndexOf("user_name")+12);
//            name = newMessage.substring(0, newMessage.indexOf('\"'));
//            String lickedName = "";
//            int count = 0;
//            while (lickedName.equals("") || lickedName.equals(name)){
//                lickedName = EventWithList.randomUser();
//                if (count == 4){
//                    return Requests.sendMessage("  не нашёл кого лизнуть :sad: ");
//                }
//                count++;
//            }
//            text = Requests.sendMessage(MessageText.randomLick(lickedName, name));
//
//        }
//        return text;
//    }

    /*
    public static List writer(){
        System.out.println("poisk novih");
        String newMessage = "";
        String name = null;
        List<String> helloList = new LinkedList();
        int lastIndex = 0;
        System.out.println(lastIndex);
        try {
            while (true) {
                if (message.lastIndexOf("name") >= 0) {
                    newMessage = message.substring(lastIndex);
                    name = newMessage.substring(0, newMessage.indexOf('"'));
                    if (!list.contains(name)) {
                        list.add(name);
                        helloList.add(name);
                    }
                } else {
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println(message);
            System.out.println(newMessage);
        }
        return list;
    }
*/

//    public static String message(String str) {
//
//        return "{\n" +
//                "    \"type\": \"send_message\",\n" +
//                "    \"data\": {\n" +
//                "        \"channel_id\": \"" + channel.getId() + "\",\n" +
//                "        \"text\": \"" + str + " \"," +
//                "        \"hideIcon\": false," +
//                "        \"mobile\": false" +
//                "    }\n" +
//                "}";
//    }
//    public static String taunt() {
//
//        return "{\n" +
//                "    \"type\": \"send_message\",\n" +
//                "    \"data\": {\n" +
//                "        \"channel_id\": \"" + channel.getId() + "\",\n" +
//                "        \"text\": \"" + " ну посмотрите на " + channel.getName() + ", так и хочется !лизьнуть \"," +
//                "        \"hideIcon\": false," +
//                "        \"mobile\": false" +
//                "    }\n" +
//                "}";
//    }
}
