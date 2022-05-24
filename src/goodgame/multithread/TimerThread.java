package goodgame.multithread;

import goodgame.ChStatus;
import goodgame.DataBase;
import goodgame.Pictures;
import goodgame.Requests;
import goodgame.cahnnel.MessageText;
import goodgame.cahnnel.Smiles;

import java.util.Timer;
import java.util.TimerTask;

import static goodgame.connect.ChatListener.event;
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
            DataBase.setTask( new TimerTask() {
                public void run() {
                    System.out.println("инициатор - TimerThread.33");
                    if (ChStatus.getStatus()) { //каждый "период" ивент уменьшается на 1. если ивент -1
                        switch (DataBase.getEvent() % 5 - 1) { //то срабатывает выбор карамельки (разовый ивент)
                            case (3), (-1) -> {
                                processor.setDoEvent(true);
                                websocketClientEndpointClass.sendMessage(Requests.getUserList());
                            }
                            case (2) -> websocketClientEndpointClass.sendMessage(Requests.sendMessage(Smiles.smilesAll()));
                            case (1) -> websocketClientEndpointClass.sendMessage(Requests.sendMessage(Pictures.liveWithLic()));
                            case (0) -> websocketClientEndpointClass.sendMessage(Requests.sendMessage(MessageText.taunt()));
                            case (-2) -> {
                                processor.setMakeCandy(true);
                                processor.setDoEvent(true);
                                websocketClientEndpointClass.sendMessage(Requests.getUserList());
                                DataBase.setEvent(33);
                            }
                            default -> websocketClientEndpointClass.sendMessage(Requests.sendMessage(Pictures.liveWithLic()));
                        }
                        System.out.println("event = " + (DataBase.getEvent() - 1));
                        System.out.println("candy = " + DataBase.getCandy());
                        DataBase.setEvent(DataBase.getEvent() - 1);
                    } else {
                        event--;
//                        System.exit(0);
                    }
                }
            });

            Timer timer = new Timer("Timer");
            long delay = 10*1000L;
            timer.scheduleAtFixedRate (DataBase.getTask(), delay, period);


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
