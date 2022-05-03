package goodgame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class GetToken {

    public static String token = "";

    public static String giveMeToken() throws IOException {
        URL url = new URL("https://goodgame.ru/ajax/chatlogin");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestMethod("POST");
        http.setDoOutput(true);
        http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//        login=LeMeldonium&password=1!Qaz2@Wsx3#Edc
        String data = Secret.secret;

        byte[] out = data.getBytes(StandardCharsets.UTF_8);

        OutputStream stream = http.getOutputStream();
        stream.write(out);

        try (final BufferedReader in = new BufferedReader(new InputStreamReader(http.getInputStream()))) {
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            token = content.toString();
            token = token.substring(token.indexOf("\"token\":\"") + 9);
            token = token.substring(0, token.indexOf("\""));
            System.out.println(token);
            if (token.isEmpty()){
                System.exit(1);
            }
            return token;
        } catch (final Exception ex) {
            ex.printStackTrace();
            return "fuck";
        }
    }

    public static String getToken() {
        if (token.equals("")){
            try {
                giveMeToken();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return token;
    }
}
