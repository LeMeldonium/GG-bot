package goodgame;

import goodgame.cahnnel.Channel;
import goodgame.cahnnel.MessageText;
import goodgame.connect.ChatListener;

public class Commands {

    static Channel channel = null;

    public Commands(Channel channel){
        Commands.channel = channel;
    }
    public static String someoneAskedMe(String message){
        String newMessage;
        String name;
        String name1;
        String text = null;
        if (message.contains("Команды:")){
            text = "";
        } else if (message.contains("-лизь")){
            System.out.println(message.lastIndexOf("user_name\":\""));
            newMessage = message.substring(message.lastIndexOf("user_name\":\"")+12);
            name1 = newMessage.substring(0, newMessage.indexOf('\"'));
            newMessage = message.substring(message.lastIndexOf("text\":\"")+7);
            name = newMessage.substring(0, newMessage.indexOf(','));
            text = Requests.sendMessage(MessageText.lickBody(name, name1));
        } else if (message.contains("!лизь")){
            newMessage = message.substring(message.lastIndexOf("user_name\":\"")+12);
            name = newMessage.substring(0, newMessage.indexOf('\"'));
            text = Requests.sendMessage(MessageText.lickStreamer(name));
//        } else if (message.contains("Казино закрыто на модификацию!")){
//            newMessage = message.substring(message.lastIndexOf("\"text\":\"")+7);
//            name1 = newMessage.substring(1, newMessage.indexOf(' '));
//            System.out.println(name1);
//            text = Requests.sendMessage(channel.getName() + ", " + name1 + " возмущён закрытым казино и просит передать :Verloin4:");
        } else if (message.contains("хочу лизь")){
            newMessage = message.substring(message.lastIndexOf("user_name")+12);
            name = newMessage.substring(0, newMessage.indexOf('"'));
            text = Requests.sendMessage(MessageText.lickSelf(name));
        } else if (message.contains("!rfpbyj")||message.contains("!help")){
            text = Requests.sendMessage(MessageText.lickHelp());
        } else if (message.contains("!карамель")){
            newMessage = message.substring(message.lastIndexOf("user_name")+12);
            name = newMessage.substring(0, newMessage.indexOf('\"'));
            text = Requests.sendMessage(MessageText.candy(name));
        } else if (message.contains("ДЖЕКПОТ")){
            newMessage = message.substring(message.lastIndexOf("text\":")+7);
            name = newMessage.substring(0, newMessage.indexOf(','));
            text = Requests.sendMessage(MessageText.JACKPOT(name));
        } else if (message.contains("!хохо")){
            EventWithList.mustRefreshList = true;
            ChatListener.websocketClientEndpointClass.sendMessage(Requests.getUserList());
            newMessage = message.substring(message.lastIndexOf("user_name")+12);
            name = newMessage.substring(0, newMessage.indexOf('\"'));
            String lickedName = "";
            int count = 0;
            while (lickedName.equals("") || lickedName.equals(name)){
                if (!EventWithList.mustRefreshList) {
                    lickedName = EventWithList.randomUser(message);
                    if (count == 4) {
                        return Requests.sendMessage(name + ",  не нашёл кого лизнуть :sad: ");
                    }
                    count++;
                }
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            text = Requests.sendMessage(MessageText.randomLick(lickedName, name));

        } else if (message.contains("стоп!")){
            newMessage = message.substring(message.lastIndexOf("user_name")+12);
            name = newMessage.substring(0, newMessage.indexOf('\"'));
            if (name.equals("LeMeldonium") || name.equals(channel.getName())){
                System.out.println("выключился по приказу " + name);
                System.exit(0);
            }
        }
        return text;
    }
}
