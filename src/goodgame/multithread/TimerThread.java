package goodgame.multithread;

import goodgame.ChStatus;
import goodgame.Father;
import goodgame.Pictures;
import goodgame.Requests;
import goodgame.cahnnel.Lizaki;
import goodgame.cahnnel.MessageText;

import java.util.Timer;
import java.util.TimerTask;

import static goodgame.connect.ChatListener.websocketClientEndpointClass;

public class TimerThread extends Thread{
    long startTime = System.currentTimeMillis();
//    int event = 4; // 4 - базовое, если 0 то будет выбор карамельки
//    static TimerTask task = null;
    Processor processor;
    String id;
    long period;

    public TimerThread(long period, String id, Processor processor){
        this.period = period;
        this.id = id;
        this.processor = processor;
    }

    @Override
    public void run() { //глупая версия таймера
            Father.task = new TimerTask() {
                public void run() {
                    System.out.println("инициатор - TimerThread.33");
                    if (ChStatus.getStatus()) { //каждый "период" ивент уменьшается на 1. если ивент -1
                        switch (Father.event % 5 - 1) { //то срабатывает выбор карамельки (разовый ивент)
                            case (3), (-1) -> {
                                processor.setDoEvent(true);
                                websocketClientEndpointClass.sendMessage(Requests.getUserList());
                            }
                            case (2) -> websocketClientEndpointClass.sendMessage(Requests.sendMessage(Lizaki.smilesAll()));
                            case (1) -> websocketClientEndpointClass.sendMessage(Requests.sendMessage(Pictures.liveWithLic()));
                            case (0) -> websocketClientEndpointClass.sendMessage(Requests.sendMessage(MessageText.taunt()));
                            case (-2) -> {
                                processor.setMakeCandy(true);
                                processor.setDoEvent(true);
                                websocketClientEndpointClass.sendMessage(Requests.getUserList());
                                Father.event = 33;
                            }
                            default -> websocketClientEndpointClass.sendMessage(Requests.sendMessage(Pictures.liveWithLic()));
                        }
                        System.out.println("event = " + (Father.event - 1));
                        System.out.println("candy = " + Father.candy);
                        Father.event--;
                    } else {
//                        System.exit(0);
                    }
                }
            };

            Timer timer = new Timer("Timer");
            long delay = 1000L;
            timer.scheduleAtFixedRate (Father.task, delay, period);


//            try {
//                Thread.sleep(processor.getPeriod());
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            System.out.println("период = " + period);
//            try {
//                Thread.sleep(60000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

    }
}
