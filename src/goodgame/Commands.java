package goodgame;

public class Commands {

    public static String getUserList(String chId){
        System.out.println("хочу список");
        return "{\n" +
                "    \"type\": \"get_users_list2\",\n" +
                "    \"data\": {\n" +
                "        \"channel_id\": \"" + chId + "\"\n" +
                "    }\n" +
                "}";
//        return "{\n" +
//                "\"type\": \"get_users_list\",\n" +
//                "\"data\": {\n" +
//                "\"channel_id\": \""+ chId +"\"\n" +
//                "}\n" +
//                "}";
    }
}
