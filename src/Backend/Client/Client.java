package Backend.Client;

import models.Coach;
import models.Player;
import models.Team;

import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Client {

    public static Scanner input;
    public static ObjectOutputStream objectOut;
    public static ObjectInputStream objectIn;
    public static String[] entities = {"Player", "Coach", "Team"};

    public Client() throws UnknownHostException, IOException {
        Socket s = new Socket("localhost", 4444);
        objectOut = new ObjectOutputStream(s.getOutputStream());
        input = new Scanner(System.in);
        objectIn = new ObjectInputStream(s.getInputStream());
    }


    public static String clientAddPlayer(Player p) throws IOException, IllegalAccessException, ClassNotFoundException {
        System.out.println("***************ADD PLAYER METHOD***************");
        objectOut.writeObject("11");
        objectOut.writeObject(p);
        return (String) objectIn.readObject() ;
    }

    public static void clientViewPlayer(DefaultTableModel dtm) throws IOException, ClassNotFoundException {
        System.out.println("***************VIEW PLAYER METHOD***************");
        objectOut.writeObject("12");
        try {
            while (true) {
                String[] fromServer = (String[]) objectIn.readObject();
                dtm.addRow(fromServer);
//                // System.out.println("adding");
            }
        } catch (Exception e) {
            System.out.println("ALL DATA DISPLAYED");
//            e.printStackTrace();
        }
    }
    public static ArrayList<Object[]> testclientViewPlayer() throws IOException, ClassNotFoundException {
        System.out.println("***************VIEW PLAYER METHOD***************");
        objectOut.writeObject("12");
        ArrayList<Object[]> array = new ArrayList<Object[]>(0);
        try {
            while (true) {
                String[] fromServer = (String[]) objectIn.readObject();
                array.add(fromServer);
                // System.out.println("adding");
            }
        } catch (Exception e) {
            System.out.println("ALL DATA DISPLAYED");
//            e.printStackTrace();
        }
        return array;
    }

    public static ArrayList<Object[]> testclientViewCoach() throws IOException, ClassNotFoundException {
        System.out.println("***************VIEW COACH METHOD***************");
        objectOut.writeObject("22");
        ArrayList<Object[]> array = new ArrayList<Object[]>(0);
        try {
            while (true) {
                String[] fromServer = (String[]) objectIn.readObject();
                array.add(fromServer);
                // System.out.println("adding");
            }
        } catch (Exception e) {
            System.out.println("ALL DATA DISPLAYED");
//            e.printStackTrace();
        }
        return array;
    }
    public static ArrayList<Object[]> testclientViewTeam() throws IOException, ClassNotFoundException {
        System.out.println("***************VIEW TEAM METHOD***************");
        objectOut.writeObject("32");
        ArrayList<Object[]> array = new ArrayList<Object[]>(0);
        try {
            while (true) {
                String[] fromServer = (String[]) objectIn.readObject();
                array.add(fromServer);
//                System.out.println("adding");
            }
        } catch (Exception e) {
            System.out.println("ALL DATA DISPLAYED");
//            e.printStackTrace();
        }
        return array;
    }

    public static boolean clientSearchPlayer(String p) throws IOException, ClassNotFoundException {
        System.out.println("***************SEARCH PLAYER METHOD*************** ");
        objectOut.writeObject("13");
        objectOut.writeObject(p);
        return (boolean) objectIn.readObject();
    }

    public static String clientAddCoach(Coach c) throws IllegalAccessException, IOException, ClassNotFoundException {
        System.out.println("***************ADD COACH METHOD***************");
        objectOut.writeObject("21");
        objectOut.writeObject(c);
        return (String) objectIn.readObject();
    }

//    public static void clientViewCoach(DefaultTableModel dtm) throws IOException, ClassNotFoundException {
//        System.out.println("***************VIEW COACH METHOD***************");
//        objectOut.writeObject("22");
//        try {
//            while (true) {
//                Object[] fromServer = (Object[]) objectIn.readObject();
//                dtm.addRow(fromServer);
//            }
//        } catch (Exception e) {
//
//        }
//    }

    public static void exit() {
        try{objectOut.writeObject("44");}catch (Exception e){}
        System.exit(0);
    }

    public static boolean clientSearchCoach(String c) throws IOException, ClassNotFoundException {
        System.out.println("***************SEARCH COACH METHOD*************** ");
        objectOut.writeObject("23");
        objectOut.writeObject(c);
        return (boolean) objectIn.readObject();
    }

    public static Object[][] getPlayerandCoachDataForTeamPopulation() throws IOException, ClassNotFoundException {
        System.out.println("***************ADD TEAM METHOD,,,, get data for population ***************");
        objectOut.writeObject("35");
        Object[][] obj2DArr = (Object[][]) objectIn.readObject();
        return obj2DArr;
    }

    public static String addTeam(Team t) throws IllegalAccessException, IOException, ClassNotFoundException {
        System.out.println("***************ADD COACH METHOD***************");
        objectOut.writeObject("31");
        objectOut.writeObject(t);
        return (String) objectIn.readObject();
    }

//    public static void clientViewTeam(DefaultTableModel dtm) throws IOException {
//        System.out.println("***************VIEW TEAM METHOD***************");
//        objectOut.writeObject("32");
//        try {
//            while (true) {
//                Object[] fromServer = (Object[]) objectIn.readObject();
//                dtm.addRow(fromServer);
//            }
//        } catch (Exception e) {
//
//        }
//    }

    public static boolean clientSearchTeam(String t) throws IOException, ClassNotFoundException {
        System.out.println("***************SEARCH TEAM METHOD*************** ");
        objectOut.writeObject("33");
        objectOut.writeObject(t);
        return (boolean) objectIn.readObject();
    }

    public static String deleteTeam(String s, Object valueAt) throws IOException, ClassNotFoundException {
        System.out.println(s+valueAt);
        objectOut.writeObject(s);
        objectOut.writeObject(valueAt);
        String fromServer = (String) objectIn.readObject();
        return fromServer;
    }


//    public static void clientAddTeam() throws IllegalAccessException, IOException, ClassNotFoundException {
//        System.out.println("***************ADD TEAM METHOD***************");
//        ArrayList<Player> players = (ArrayList<Player>) objectIn.readObject();
//        ArrayList<Coach> coaches = (ArrayList<Coach>) objectIn.readObject();
//        Team team = new Team();
//
//        team.setTeamName(Sides.Input.inputLine("Enter The Name of New Team: "));
//        // Sides.Generic_Object_Filler.getObjectData(team);
//
//        int count = 0;
//        // DISPLAY PLAYER FOR SELECTION
//
//        System.out.println("AVAILABLE PLAYERS:");
//
//        for (Player player : players)
//            if (!player.getSelected())
//                System.out.println(++count + " " + player.toString());
//
//        int integerInput;
//        ArrayList<Integer> selected = new ArrayList<Integer>(0);
//        ArrayList<Player> selectedPlayer = new ArrayList<Player>(0);
//        // System.out.println(players.size());
//        for (int i = 0; i < 11 && i < players.size(); i++) {
//            while (true) {
//                integerInput = Sides.Input.inputInt("SELECT PLAYER#" + i + " FROM ABOVE LIST OF PLAYERS: ",
//                        "INVALID OPTION, PLEASE SELECT A NUMBER FROM ABOVE OR PRESS 0 to end.", 0, players.size());
//                if (!selected.contains(integerInput)) {
//                    selected.add(integerInput);
//                    break;
//                }
//                System.out.println("You already Selected This Player. Please Select A Different Player");
//            }
//            if (integerInput == 0)
//                break;
//            selectedPlayer.add(players.get(--integerInput));
//        }
//        if (players.size() == selectedPlayer.size()) {
//            System.out.println(
//                    "SORRY DEAR USER. THERE ARE NO MORE VACANT PLAYERS THAT YOUR CAN ADD TO YOU TEAM AT THE MOMENT.");
//        }
//
//        if (selectedPlayer.isEmpty())
//            team.setCaptain(.getText().trim());
//        else if (selectedPlayer.size() == 1)
//            team.setCaptain(selectedPlayer.get(0).getName());
//        else {
//            count = 0;
//            System.out.println("\nPLEASE SELECT A CAPTAIN FROM THE CURRENT PLAYERS");
//            for (Player p : selectedPlayer)
//                System.out.println(++count + " " + p.getName());
//            team.setCaptain(selectedPlayer
//                    .get(Sides.Input.inputInt("Select the number from above: ",
//                            "Invalid Number. Please select from above options.", 1, selectedPlayer.size()) - 1)
//                    .getName());
//
//        }
//        count = 0;
//        // DISPLAY COACH FOR SELECTION
//        System.out.println("\nAVAILABLE COACHES:");
//        for (Coach coach : coaches)
//            System.out.println(++count + ": " + coach.toString());
//
//        selected.clear();
//        ArrayList<Coach> selectedCoach = new ArrayList<Coach>(0);
//
//        for (int i = 0; i < 4 && i < coaches.size(); i++) {
//            while (true) {
//                integerInput = Sides.Input.inputInt("SELECT COACH#" + i + " FROM ABOVE LIST OF COACHES: ",
//                        "INVALID OPTION, PLEASE SELECT A NUMBER FROM ABOVE OR PRESS 0 to end.", 0, coaches.size());
//                if (!selected.contains(integerInput)) {
//                    selected.add(integerInput);
//                    break;
//                }
//                System.out.println("You already Selected This Coach. Please Select A Different Coach");
//            }
//            if (integerInput == 0)
//                break;
//            selectedCoach.add(coaches.get(--integerInput));
//        }
//        if (coaches.size() == selectedCoach.size())
//            System.out.println(
//                    "SORRY DEAR USER. THERE ARE NO MORE VACANT COACHES THAT YOU CAN ADD TO YOUR TEAM AT THE MOMENT.");
//
//        team.setPlayers(selectedPlayer.toArray(new Player[0]));
//        team.setCoaches(selectedCoach.toArray(new Coach[0]));
//        out.flush();
//        objectOut.writeObject(team);
//        out.flush();
//        objectOut.writeObject(selectedPlayer);
//    }

}
