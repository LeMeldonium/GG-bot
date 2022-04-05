package goodgame;

import goodgame.connect.TestApp;

public class Time {

    public static String getTime(int plus) {
        plus++;
        System.out.println(plus + " plus");
        int pH = (int) (plus * Main.channel.getPeriod() / 3600000);
        System.out.println(pH + " plus chasov");
        int pM = (int) (plus * Main.channel.getPeriod() % 60000);
        System.out.println(pM + " plus minut");
        long delta = TestApp.task.scheduledExecutionTime() + Main.channel.getPeriod() - System.currentTimeMillis();
        if (delta < 0) {
            delta *= -1;
        }

        int mils = (int) (delta % 1000);
        delta /= 1000;
        int sec = 0;
        if (delta > 0) {
            sec = (int) (delta % 60);
            delta /= 60;
        }

        int min = 0;
        if (delta > 0) {
            min = (int) (delta % 60);
            delta /= 60;
        }

        int hour = 0;
        if (delta > 0) {
            hour = (int) (delta % 24);
        }
        hour += pH;
        min += pM;
        System.out.println(pM + " pM");
        System.out.println(min + " min");
        return hour + ":" + min + ":" + sec + "." + mils;
    }
}
