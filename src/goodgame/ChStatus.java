package goodgame;

import goodgame.connect.ChatListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class ChStatus {

    public static String token = "";

    public static Boolean giveMeStatus() throws IOException {
        URL url = new URL("http://goodgame.ru/api/getchannelstatus?id="+Father.channel.getId()+"&fmt=json");
        int x = 0;
        while (x < 5) {
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("POST");
            http.setDoOutput(true);
            http.setRequestProperty("Accept", "application/json");
            http.setRequestProperty("Content-Type", "application/json");

            String data = "{\n  \"Id\": 78912,\n  \"Customer\": \"Jason Sweet\",\n  \"Quantity\": 1,\n  \"Price\": 18.00\n}";

            byte[] out = data.getBytes(StandardCharsets.UTF_8);

            OutputStream stream = http.getOutputStream();
            stream.write(out);

            final BufferedReader in = new BufferedReader(new InputStreamReader(http.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            token = content.toString();
            token = token.substring(token.indexOf("\"status\":") + 10, token.indexOf("\"status\":") + 14);
            System.out.println("status = " + token);
            if (token.contains("Live")) {
                return true;
            }
            x++;
            try {
                Thread.sleep(60000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("стрим выключен");
        System.exit(0);
        ChatListener.websocketClientEndpointClass.removeMessageHandler();

            return false;

    }

    public static Boolean getStatus() {
        try {
            return giveMeStatus();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
