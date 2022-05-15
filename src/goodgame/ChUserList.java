package goodgame;

import java.util.ArrayList;
import java.util.List;

public class ChUserList {

    public static List<String> giveMeUserList(String message) {
        String usersStr;
        List<String> userList = new ArrayList();
        int usersInt = 0;
//        System.out.println("проблемное сообщение \n" + message);
        usersStr = message.substring(message.indexOf("users_in") + "users_in_channel\":".length());
        try {
            usersInt = Integer.parseInt(usersStr.substring(0, usersStr.indexOf(",")));
        } catch (NumberFormatException e) {
            System.err.println(usersStr);
            e.printStackTrace();
        }
        System.out.println("всего людей " + usersInt);
        //usersStr = usersStr.substring(usersStr.indexOf("[{"));
        for (int i = 0; i < usersInt; i++) {
            usersStr = usersStr.substring(usersStr.indexOf("name\":") + "name\":'".length());
            try {
                userList.add(usersStr.substring(0, usersStr.indexOf("\",")));
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("ChUserList.28");
                ChStatus.getStatus();
            }
            //System.out.println(i);
        }
        System.out.println("отправил юзерлист");
        return userList;
    }


}
