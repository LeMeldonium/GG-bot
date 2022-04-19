package goodgame;

public class Requests {
    public static String id;

    public static String getUserList(){
        System.out.println("хочу список");
        return "{\n" +
                "    \"type\": \"get_users_list2\",\n" +
                "    \"data\": {\n" +
                "        \"channel_id\": \"" + id + "\"\n" +
                "    }\n" +
                "}";
    }

    public static String auth(String token){
        return "{" +
                "    \"type\": \"auth\"," +
                "    \"data\": {" +
                "        \"user_id\": \"281724\"," +
                "        \"token\": \"" + token + "\"" +
                "    }" +
                "}";
    }

    public static String join() {
        return "{" +
                "    \"type\": \"join\"," +
                "    \"data\": {" +
                "        \"channel_id\": \"" + id + "\"," +
                "        \"hidden\": false" +
                "    }\n" +
                "}";
    }

    public static String sendMessage(String text){
        return "{\n" +
                "    \"type\": \"send_message\",\n" +
                "    \"data\": {\n" +
                "        \"channel_id\": \"" + id + "\",\n" +
                "        \"text\": \"" + text + "\",\n" +
                "        \"hideIcon\": false," +
                "        \"mobile\": false" +
                "    }\n" +
                "}";
    }
}
