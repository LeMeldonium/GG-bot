package goodgame.multithread;

import goodgame.ChStatus;
import goodgame.Commands;
import goodgame.Main;
import goodgame.Pictures;
import goodgame.cahnnel.Lizaki;

import java.util.Timer;
import java.util.TimerTask;

import static goodgame.connect.TestApp.websocketClientEndpointClass;

public class TimerThread extends Thread{
    long startTime = System.currentTimeMillis();
    int event = 4; // 4 - базовое, если 0 то будет выбор карамельки
    static TimerTask task = null;
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
        while (true){
            task = new TimerTask() {
                public void run() {
                    if (ChStatus.getStatus()) { //каждый "период" ивент уменьшается на 1. если ивент -1
                        switch (event % 5 - 1) { //то срабатывает выбор карамельки (разовый ивент)
                            /* 3 часа 59 минут */
                            case (3) -> websocketClientEndpointClass.sendMessage(Commands.getUserList(id));
                            /* 3 часа 59 минут */
                            case (2) -> websocketClientEndpointClass.sendMessage(Main.message(Lizaki.smilesAll()));
                            /* 2 часа 59 минут */
                            case (1) -> websocketClientEndpointClass.sendMessage(Main.message(Pictures.liveWithLic()));
                            /* 1 час 59 минут */
                            case (0) -> websocketClientEndpointClass.sendMessage(Main.taunt());
                            /* 59 минут */
                            case (-1) -> {
                                processor.setDoEvent(true);
//                        doEvent = true;
                                websocketClientEndpointClass.sendMessage(Commands.getUserList(id));
                            }
                            case (-2) -> {
                                processor.setCanRandomize(true);
                                processor.setDoEvent(true);
                                websocketClientEndpointClass.sendMessage(Commands.getUserList(id));
                                event = 23;
                            }
                            default -> websocketClientEndpointClass.sendMessage(Main.message(Pictures.liveWithLic()));
                        }
                        System.out.println("event = " + (event - 1));
                        System.out.println("candy = " + Main.candy);
                        event--;
                    } else {
                        System.exit(0);
                    }
                }
            };

            Timer timer = new Timer("Timer");
            long delay = 1000L;
            timer.scheduleAtFixedRate (task, delay, processor.getPeriod());


//            try {
//                Thread.sleep(processor.getPeriod());
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }
}
