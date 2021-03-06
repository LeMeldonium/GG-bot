package goodgame;

import goodgame.cahnnel.MessageText;

import java.util.List;
import java.util.Random;


public class EventWithList {
    public static List <String> userList = null;
//    public static boolean wasRefreshedNow = false;
    public static boolean mustRefreshList = false;

    public static String withList(String message, boolean makeCandy){
        Random random = new Random();
        String name;
        int num;
        List <String> userList;
        userList = ChUserList.giveMeUserList(message);
        if (makeCandy){
//            TestApp.canRandomize = false;

            DataBase.setCandy(userList.get(random.nextInt(userList.size())));
            return Requests.sendMessage(MessageText.candyOfTheStream(DataBase.getCandy()));
        } else {
            if (userList.size() <= 72){
                num = userList.size()*2/4;
            } else {
                num = userList.size()*3/5;
            }
            for (int i = 0; i < num; i++) {
                userList.remove(random.nextInt(userList.size()));
            }
            return Requests.sendMessage(MessageText.lickNotAll(userList));
        }
    }

    public static String randomUser(String message){
        Random random = new Random();
        if (mustRefreshList) {
            refreshList(message);
        }
        return userList.get(random.nextInt(userList.size()));
    }

    public static void refreshList(String message){
        System.out.println("обновил список");
        userList = ChUserList.giveMeUserList(message);
//        wasRefreshedNow = true;
        mustRefreshList = false;
    }
}
