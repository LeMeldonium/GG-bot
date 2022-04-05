package goodgame;

import goodgame.cahnnel.MessageText;
import goodgame.connect.TestApp;

import java.util.List;
import java.util.Random;

import static goodgame.Main.channel;
import static goodgame.Main.licker;

public class EventWithList {
    public static List <String> userList = null;

    public static String withList(String message, Boolean canRandomize){
        Random random = new Random();
        String name;
        int num;
        List <String> userList;
        userList = ChUserList.giveMeUserList(message);
        if (canRandomize){
            TestApp.canRandomize = false;
            licker = userList.get(random.nextInt(userList.size()));
            return "{\n" +
                    "    \"type\": \"send_message\",\n" +
                    "    \"data\": {\n" +
                    "        \"channel_id\": \"" + channel.getId() + "\",\n" +
                    "        \"text\": \"" + MessageText.lickOfTheStream(licker) +
                    "        \"hideIcon\": false," +
                    "        \"mobile\": false" +
                    "    }\n" +
                    "}";
        } else {
            if (userList.size() <= 72){
                num = userList.size()*2/4;
            } else {
                num = userList.size()*3/5;
            }
            for (int i = 0; i < num; i++) {
                userList.remove(random.nextInt(userList.size()));
            }
            return "{\n" +
                    "    \"type\": \"send_message\",\n" +
                    "    \"data\": {\n" +
                    "        \"channel_id\": \"" + channel.getId() + "\",\n" +
                    "        \"text\": \"" + MessageText.lickNotAll(userList) +
                    "        \"hideIcon\": false," +
                    "        \"mobile\": false" +
                    "    }\n" +
                    "}";
        }
    }

    public static String randomUser(){
        Random random = new Random();
        return userList.get(random.nextInt(userList.size()));
    }

    public static void refreshList(String message){
        System.out.println("обновил список");
        userList = ChUserList.giveMeUserList(message);
        System.out.println("юзерлист сейчас " + userList.get(0));
    }
}
