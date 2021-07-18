package models;

import java.io.Serializable;
import java.util.ArrayList;

public class Team implements Serializable {
    String teamName;
    String[] players = new String[0];
    String captain;
    String[] coaches = new String[0];

    @Override
    public String toString() {
        String space = "    ";
        String player_string = "";
        for (String player : players)
            player_string += player + '\n';

        String coach_string = "";
        for (String coach : coaches)
            coach_string += coach + '\n';

        return "\nTeam{" +
                "\n\n    TEAM NAME='" + teamName + '\'' +
                "\n\n    TEAM PLAYERS : \n" + player_string +
                "\n    CAPTAIN='" + captain + '\'' +
                "\n\n    COACHES : \n" + coach_string +
                "\n}";
    }

    public String[] toArray() {

        String[] strArr = new String[4];

        strArr[0] = teamName;
        String str = "";
        int count = 0;
        for (String p : players)
            str += " "+ (++count) +": "+ p+"\n";

        strArr[1] = str;
        str = "";

        strArr[2] = captain;

        count = 0;
        for (String c : coaches)
            str += " "+ (++count) +": "+ c+"\n";

        strArr[3] = str;

        return strArr;
    }

//    public void setAllData(ArrayList Data) {
//        try {
//            this.teamName = (String) Data.get(0);
//            this.players = (String[]) Data.get(1);
//            this.captain = (String) Data.get(2);
//            this.coaches = (String[]) Data.get(3);
//        } catch (Exception e) {
//        }
//    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setCaptain(String captain) {
        this.captain = captain;
    }

    public String getTeamName() {
        return teamName;
    }

    public String[] getPlayers() {
        return players;
    }

    public String getCaptain() {
        return captain;
    }

    public String[] getCoaches() {
        return coaches;
    }

    public void setPlayers(String[] players) {
        this.players = new String[players.length];
        for (int i = 0; i<players.length; i++)
            this.players[i] = players[i];
    }

    public void setCoaches(String[] coaches) {
        this.coaches = new String[coaches.length];
        for (int i = 0; i<coaches.length; i++)
            this.coaches[i] = coaches[i];
    }
}
