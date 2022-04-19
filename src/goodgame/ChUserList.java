package goodgame;

import java.util.ArrayList;
import java.util.List;

public class ChUserList {

    public static List<String> giveMeUserList(String message) {
        String usersStr;
        List<String> userList = new ArrayList();
        int usersInt;
//        System.out.println("проблемное сообщение \n" + message);
        usersStr = message.substring(message.indexOf("users_in") + "users_in_channel\":".length());
        usersInt = Integer.parseInt(usersStr.substring(0, usersStr.indexOf(",")));
        System.out.println("всего людей " + usersInt);
        //usersStr = usersStr.substring(usersStr.indexOf("[{"));
        for (int i = 0; i < usersInt; i++) {
            usersStr = usersStr.substring(usersStr.indexOf("name\":") + "name\":'".length());
            userList.add(usersStr.substring(0, usersStr.indexOf("\",")));
            //System.out.println(i);
        }
        return userList;
    }


}
