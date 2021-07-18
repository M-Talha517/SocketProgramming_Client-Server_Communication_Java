//// MULTISERVER THREAD FOR NON GUI PROGRAM
//
//package Backend.Server;
//
//import models.Coach;
//import models.Player;
//import models.Team;
//
//import java.net.* ;
//import java.io.* ;
//import java.util.ArrayList;
//
//public class MultiServerThread  extends Thread {
//
//
//    final private Socket socket;
//    private ObjectInputStream objectIn;
//    ObjectOutputStream objectOut;
//
//    public MultiServerThread(Socket socket) {
//        this.socket = socket ;
//    }
//    public void run() {
//        System.out.println("Connected to a Sides.Client");
//        try {
//            InputStream IS = socket.getInputStream();
//            objectOut = new ObjectOutputStream(socket.getOutputStream());
//            objectIn = new ObjectInputStream(IS);
//
//
//            String fromUser;
//            boolean loop = true;
//            while(loop){
//
////                in.skip(3);
//                fromUser = (String) objectIn.readObject();
//                System.out.println("User Selected " +fromUser);
//                switch (fromUser) {
//                    case "11" -> addPlayer();
//                    case "12" -> viewPlayer();
//                    case "13" -> searchPlayer();
//                    case "21" -> addCoach();
//                    case "22" -> ViewCoach();
//                    case "23" -> SearchCoach();
//                    case "31" -> AddTeam();
//                    case "32" -> ViewTeam();
//                    case "33" -> SearchTeam();
//
//                    default -> {
//                        if (fromUser.contains("exit") || fromUser.contains("4") ){
//                            loop = false;
//                            break;
//                        }else {
//                            System.out.println("Invalid Option By User");
//                        }
//                    }
//                }
//            }
//
//            System.out.println("out of loop");
//            objectIn.close();
//            objectOut.close();
//            socket.close() ;
//        } catch (IOException | ClassNotFoundException e) {
//            e.printStackTrace() ;
//        }
//    }
//
//    private void SearchTeam() throws IOException, ClassNotFoundException {
//        System.out.println("Server: In Search Coach Function");
//        String team_name = (String) objectIn.readObject();
//        System.out.println("searching for " + team_name);
//        ArrayList<Team> teams = (ArrayList<Team>)(ArrayList<?>) FileHandling.SearchFromFile(team_name,"Team.ser");
//        boolean found = false;
//        for (Team team : teams)
//            if (team.getTeamName().contains(team_name)) {
//                objectOut.writeObject(team.toString());
//                found = true;
//            }
//        if (!found)
//            objectOut.writeObject("Team not found");
//        objectOut.writeObject("endl");
//    }
//
//    private void AddTeam() throws IOException, ClassNotFoundException {
//        ArrayList<Player> players = (ArrayList<Player>)(ArrayList<?>) FileHandling.ReadFromFile("Player.ser");
//        ArrayList<Coach> coaches = (ArrayList<Coach>)(ArrayList<?>) FileHandling.ReadFromFile("Coach.ser");
//        objectOut.writeObject((Object)players);
//        objectOut.writeObject((Object)coaches);
//        System.out.println("Server: In Add Coach Function");
//        Team team = (Team) objectIn.readObject();
//        FileHandling.WriteToFile("Team.ser",team);
//        ArrayList<Player> playersToUpdate = (ArrayList<Player>)(ArrayList<?>) objectIn.readObject();
//        FileHandling.UpdatePlayerFile(playersToUpdate);
//    }
//
//    private void ViewTeam() throws IOException {
//
//        System.out.println("Server: In View Coach Function");
//        ArrayList<Team> array = (ArrayList<Team>)(ArrayList<?>) FileHandling.ReadFromFile("Team.ser");
//        for (Team team : array) {
//            System.out.println("In Server: " + team.toString());
//            objectOut.writeObject(team.toString());
//        }
//        objectOut.writeObject("endl");
//    }
//
//    private void SearchCoach() throws IOException, ClassNotFoundException {
//        System.out.println("Server: In Search Coach Function");
//        String coach_name = (String) objectIn.readObject();
//        System.out.println("searching for " + coach_name);
//        ArrayList<Coach> coaches = (ArrayList<Coach>)(ArrayList<?>) FileHandling.SearchFromFile(coach_name,"Coach.ser");
//        boolean found = false;
//        for (Coach coach : coaches )
//            if (coach.getName().contains(coach_name)) {
//                objectOut.writeObject(coach.toString());
//                found = true;
//            }
//        if (!found)
//            objectOut.writeObject("Coach not found");
//        objectOut.writeObject("endl");
//    }
//
//    private void ViewCoach() throws IOException {
//        System.out.println("Server: In View Coach Function");
//        ArrayList<Coach> array = (ArrayList<Coach>)(ArrayList<?>) FileHandling.ReadFromFile("Coach.ser");
//        for (Coach c : array) {
//            System.out.println("In Server: " + c.toString());
//            objectOut.writeObject(c.toString(""));
//        }
//        objectOut.writeObject("endl");
//    }
//
//
//    private void addCoach() throws IOException, ClassNotFoundException {
//        System.out.println("Server: In Add Coach Function");
//        Object toBeWritten;
//        System.out.println(toBeWritten=objectIn.readObject());
//        if (!toBeWritten.getClass().getName().toString().contains("COACH"))
//            toBeWritten = objectIn.readObject();
//
//        Coach coach = (Coach)toBeWritten ;
//        FileHandling.WriteToFile("Coach.ser",coach);
//    }
//
//    public void addPlayer() throws IOException, ClassNotFoundException {
//        System.out.println("Server: In Add Player Function");
//        Player player = (Player) objectIn.readObject();
//        FileHandling.WriteToFile("Player.ser",player);
//    }
//    public void viewPlayer() throws IOException {
//        System.out.println("Server: In View Player Function");
//        ArrayList<Player> array = (ArrayList<Player>)(ArrayList<?>) FileHandling.ReadFromFile("Player.ser");
//        for (Player p : array) {
//            System.out.println("In Server: " + p.toString());
//            objectOut.writeObject(p.toString(""));
//        }
//        objectOut.writeObject("endl");
//    }
//    public void searchPlayer() throws IOException, ClassNotFoundException {
//        System.out.println("Server: In Search Player Function");
//        String player_name = (String) objectIn.readObject();
//        System.out.println("searching for " + player_name);
//        ArrayList<Player> players = (ArrayList<Player>)(ArrayList<?>) FileHandling.SearchFromFile(player_name,"Player.ser");
//        boolean found = false;
//        for (Player player : players )
//            if (player.getName().contains(player_name)) {
//                objectOut.writeObject(player.toString());
//                found = true;
//            }
//        if (!found)
//            objectOut.writeObject("Player not found");
//        objectOut.writeObject("endl");
//    }
//}
//
