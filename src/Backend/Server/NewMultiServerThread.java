// NEW MULTI-SERVER CLASS FOR GUI

package Backend.Server;

import models.Coach;
import models.Player;
import models.Team;

import java.net.*;
import java.io.*;
import java.util.ArrayList;

public class NewMultiServerThread extends Thread {

    final private Socket socket;
    private ObjectInputStream objectIn;
    ObjectOutputStream objectOut;

    public NewMultiServerThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        System.out.println("Connected to a Sides.Client");
        try {
            InputStream IS = socket.getInputStream();
            objectOut = new ObjectOutputStream(socket.getOutputStream());
            objectIn = new ObjectInputStream(IS);

            String fromUser;
            boolean loop = true;
            while (loop) {

//                in.skip(3);
                fromUser = (String) objectIn.readObject();
                System.out.println("User Selected " + fromUser);
                switch (fromUser) {
                    case "11" -> addPlayer();
                    case "12" -> viewPlayer();
                    case "13" -> searchPlayer();
                    case "21" -> addCoach();
                    case "22" -> viewCoach();
                    case "23" -> searchCoach();
                    case "31" -> addTeam();
                    case "32" -> viewTeam();
                    case "33" -> searchTeam();
                    case "35" -> getTeamPopulationData();
                    case "36" -> deleteTeam();
                    default -> {
                        if (fromUser.contains("exit") || fromUser.contains("4")) {
                            loop = false;
                            break;
                        } else {
                            System.out.println("Invalid Option By User");
                        }
                    }
                }
            }

            System.out.println("out of loop");
            objectIn.close();
            objectOut.close();
            socket.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void deleteTeam() throws IOException, ClassNotFoundException {
        String teamName = (String) objectIn.readObject();
        String toClient = FileHandling.deleteTeam(teamName) ? "TEAM SUCCESSFULLY DELETED":"ERROR FAILED TO DELETE TEAM";
        objectOut.writeObject(toClient);
    }

    private void getTeamPopulationData() throws IOException {
        System.out.println("IN SERVER: GETTING DATA FOR PLAYERS AND COACHES FOR TEAM");
        ArrayList<Player> players = (ArrayList<Player>)(ArrayList<?>) FileHandling.ReadFromFile("Player.ser");
        ArrayList<Coach> coaches = (ArrayList<Coach>)(ArrayList<?>) FileHandling.ReadFromFile("Coach.ser");
        ArrayList<Player> unselectedPlayers = new ArrayList<Player>(0);

        for (int i = 0 ; i < players.size(); i++) {
//            System.out.println("ALL:"+players.get(i).getName()+" "+players.get(i).getSelected());
            if (!players.get(i).getSelected()) {
                System.out.println("SELECTED:"+players.get(i).getName());
                unselectedPlayers.add(players.get(i));
            };
        }

//        System.out.println("USP:"+unselectedPlayers.toString());
        // filter out selected players to show only unselected ones
        Object[] PlayerArray = new Object[unselectedPlayers.size()];
        for (int i=0; i < unselectedPlayers.size() ; i++)
            PlayerArray[i]  = unselectedPlayers.get(i).getName()+"{"+unselectedPlayers.get(i).getAge()+"}";

        Object[] CoachArray = new Object[coaches.size()];

        for (int i=0; i < coaches.size() ; i++)
            CoachArray[i]  = coaches.get(i).getName()+"{"+coaches.get(i).getRole()+"}";

        Object[][] populationData = new Object[2][];
        populationData[0] = PlayerArray;
        populationData[1] = CoachArray;


        objectOut.writeObject(populationData);
        System.out.println("IN SERVER: DATA SENT TO CLIENT FOR TEAM");
    }


    private void addPlayer() throws IOException, ClassNotFoundException {
        System.out.println("Server: In Add Player Function");
        Player player = (Player) objectIn.readObject();
        String notification = (FileHandling.WriteToFile("Player.ser", player)) ? "PLAYER SUCCESSFULLY ADDED": "ADDING FAILED, PLAYER ALREADY EXISTS" ;
        objectOut.writeObject(notification);
    }

    public void viewPlayer() throws IOException {
        System.out.println("Server: In View Player Function");
        ArrayList<Player> array = (ArrayList<Player>) (ArrayList<?>) FileHandling.ReadFromFile("Player.ser");
        for (Player p : array) {
            System.out.println(p.toString());
            objectOut.writeObject(p.toArray());
        }
        objectOut.writeObject("endl");
    }

    public void searchPlayer() throws IOException, ClassNotFoundException {
        System.out.println("Server: In Search Player Function");
        String player_name = (String) objectIn.readObject();
        System.out.println("searching for " + player_name);
        boolean found = FileHandling.Find(player_name,"Player.ser");
        objectOut.writeObject(found);
    }

    private void addCoach() throws IOException, ClassNotFoundException {
        System.out.println("Server: In Add Coach Function");
        Coach coach = (Coach) objectIn.readObject();
        String notification = (FileHandling.WriteToFile("Coach.ser", coach)) ? "COACH SUCCESSFULLY ADDED": "ADDING FAILED, COACH ALREADY EXISTS" ;
        objectOut.writeObject(notification);
    }

    public void viewCoach() throws IOException {
        System.out.println("Server: In View Coach Function");
        ArrayList<Coach> array = (ArrayList<Coach>) (ArrayList<?>) FileHandling.ReadFromFile("Coach.ser");
        for (Coach p : array) {
            System.out.println("In Server: " + p.toArray().toString());
            objectOut.writeObject(p.toArray());
        }
        objectOut.writeObject("endl");
    }

    public void searchCoach() throws IOException, ClassNotFoundException {
        System.out.println("Server: In Search Coach Function");
        String coach_name = (String) objectIn.readObject();
        System.out.println("searching for " + coach_name);
        boolean found = FileHandling.Find(coach_name,"Coach.ser");
        objectOut.writeObject(found);
    }

    private void addTeam() throws IOException, ClassNotFoundException {
        System.out.println("Server: In Add Team Function");
        Team team = (Team) objectIn.readObject();
        String notification = (FileHandling.WriteToFile("Team.ser", team)) ? "TEAM SUCCESSFULLY ADDED": "ADDING FAILED, TEAM ALREADY EXISTS" ;
        objectOut.writeObject(notification);

    }

    public void viewTeam() throws IOException {
        System.out.println("Server: In View Team Function");
        ArrayList<Team> array = (ArrayList<Team>) (ArrayList<?>) FileHandling.ReadFromFile("Team.ser");
        for (Team p : array) {
//            System.out.println("In Server: " + p.toArray().toString());
            objectOut.writeObject(p.toArray());
        }
        objectOut.writeObject("endl");
    }

    public void searchTeam() throws IOException, ClassNotFoundException {
        System.out.println("Server: In Search Team Function");
        String team_name = (String) objectIn.readObject();
        System.out.println("searching for " + team_name);
        boolean found = FileHandling.Find(team_name,"Team.ser");
        objectOut.writeObject(found);
    }
}
