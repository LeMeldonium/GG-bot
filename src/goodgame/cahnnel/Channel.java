package goodgame.cahnnel;

import goodgame.Requests;

public class Channel {
    String id;
    String name;
    long period;
    int status;
    String smile;

    public Channel(String id, String name, long period, String uStatus, String smile) {
        this.id = id;
        this.name = name;
        this.period = period;
        this.status = userStatus(uStatus);
        Requests.id = id;
        this.smile = smile;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getPeriod() {
        return period;
    }

    public int getStatus() {
        return status;
    }

    public String getSmile() {
        return smile;
    }

    public int userStatus(String uStatus){
        return switch (uStatus) {
            case ("bronze") -> 1;
            case ("silver") -> 2;
            case ("gold") -> 3;
            case ("diamond") -> 4;
            case ("king") -> 5;
            case ("undead") -> 6;
            default -> 7;
        };
    }
}
