package goodgame;

import goodgame.connect.ChatListener;
import goodgame.multithread.Processor;
import goodgame.multithread.TimerThread;

import java.util.TimerTask;

public class DataBase {
    private static String candy = ""; // ""
    private static TimerThread timerThread;
    private static TimerTask task = null; //null
    private static int event = 4; // 4 - базовое, если -1 то будет выбор карамельки
    private static Processor processor;
    private static boolean notReady = true; //true
    private static ChatListener chatListener;
    private static boolean streamIsAlive = false; //false

    public static void setCandy(String candy) {
        DataBase.candy = candy;
    }

    public static void setTimerThread(TimerThread timerThread) {
        DataBase.timerThread = timerThread;
    }

    public static void setTask(TimerTask task) {
        DataBase.task = task;
    }

    public static void setEvent(int event) {
        DataBase.event = event;
    }

    public static void setProcessor(Processor processor) {
        DataBase.processor = processor;
    }

    public static void setNotReady(boolean notReady) {
        DataBase.notReady = notReady;
    }

    public static void setChatListener(ChatListener chatListener) {
        DataBase.chatListener = chatListener;
    }

    public static void setStreamIsAlive(boolean streamIsAlive) {
        DataBase.streamIsAlive = streamIsAlive;
    }

    public static String getCandy() {
        return candy;
    }

    public static TimerThread getTimerThread() {
        return timerThread;
    }

    public static TimerTask getTask() {
        return task;
    }

    public static int getEvent() {
        return event;
    }

    public static Processor getProcessor() {
        return processor;
    }

    public static boolean getNotReady() {
        return notReady;
    }

    public static ChatListener getChatListener() {
        return chatListener;
    }

    public static boolean getStreamIsAlive() {
        return streamIsAlive;
    }
}
