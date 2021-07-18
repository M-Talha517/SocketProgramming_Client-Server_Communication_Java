package Backend.Server;

import models.Coach;
import models.Player;
import models.Team;

import java.io.*;
import java.util.ArrayList;


public interface FileHandling {


    static boolean WriteToFile(String filename, Object obj) {
//		filename = filename;
        boolean NewObject = true;
        System.out.println("In WRITE TO FILE");
        ObjectOutputStream outputStream = null;
        try {
            ArrayList<Object> list = ReadFromFile(filename);

            int Case = 0;
            if (obj instanceof Player) Case = 1;
            else if (obj instanceof Coach) Case = 2;
            else if (obj instanceof Team) Case = 3;
//			System.out.println(Case);
            NewObject = true;
            switch (Case) {
                case 1:
                    for (int i = 0; i < list.size(); i++) {
                        Player player = (Player) list.get(i);
                        Player playerToWrite = (Player) obj;
                        if (
                                player.getName().equalsIgnoreCase(playerToWrite.getName()) &&
                                        player.getAge() == playerToWrite.getAge()
//                                        && player.getRating() == playerToWrite.getRating()
                                        && player.getRole().equalsIgnoreCase(playerToWrite.getRole())
                        )
                            NewObject = false;
                    }


                    break;
                case 3:
                    for (int i = 0; i < list.size(); i++) {
                        Team team = (Team) list.get(i);
                        Team teamToWrite = (Team) obj;
                        if (team.getTeamName().equalsIgnoreCase(teamToWrite.getTeamName()))
                            NewObject = false;
                    }
                    break;
                case 2:
                    for (int i = 0; i < list.size(); i++) {
                        Coach coach = (Coach) list.get(i);
                        Coach coachToWrite = (Coach) obj;
                        if (
                                coach.getName().equalsIgnoreCase(coachToWrite.getName()) &&
                                        coach.getAge() == coachToWrite.getAge()
                                        && coach.getRole().equalsIgnoreCase(coachToWrite.getRole())
                        )
                            NewObject = false;
                    }
                    break;
            }
            filename = "src/Files/" + filename;
            outputStream = new ObjectOutputStream(new FileOutputStream(filename));
            // in case of appending a new object to the file
            if (NewObject) list.add(obj);
            System.out.println(list.toString() + "TO BE WRITTEN");
            for (int i = 0; i < list.size(); i++) {
                outputStream.writeObject(list.get(i));
            }

            if (Case == 3) {
                System.out.println("IN TEAM : CHANGING PLAYER STATUS TO SELECTED");
                list = ReadFromFile("Player.ser");
                System.out.println(list.toString());
                Team team = (Team) obj;
                String[] players = team.getPlayers();
                for (int h = 0; h < players.length; h++) {
                    String[] data = new String[2];
                    for (String str : data)
                        System.out.println("a" + str);

                    data[0] = players[h].substring(0, players[h].indexOf("{"));
                    data[1] = players[h].substring(players[h].indexOf('{'));
                    data[1] = data[1].replace("}", "");
                    data[1] = data[1].replace("{", "");
//                    data[1] =data[1].replace(",","");
                    data[1] = data[1].trim();

                    for (String str : data)
                        System.out.println("b" + str);

                    for (int i = 0; i < list.size(); i++) {
                        Player player = (Player) list.get(i);
                        if (player.getName().equalsIgnoreCase(data[0]) && player.getAge() == Integer.parseInt(data[1])) {
                            System.out.println("MATCH" + data[0]);
                            player.setSelected();
                            break;
                        }
                    }
                }
                System.out.println(list.toString() + "TO BE WRITTEN");
                outputStream = new ObjectOutputStream(new FileOutputStream("src/Files/Player.ser"));
                for (int i = 0; i < list.size(); i++) {
                    outputStream.writeObject(list.get(i));
                }
            }
        } catch (IOException e) {
            //  System.out.println("IO Exception while opening file");
        } finally {
            // cleanup code which closes output stream if its object was created
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                //  System.out.println("IO Exception while closing file");
            }
            System.out.println("END WRITE TO FILEEEEEEEE");
            return NewObject;
        }
    }


    static ArrayList<Object> ReadFromFile(String filename) {
        filename = "src/Files/" + filename;
        System.out.println("In READ FILE");
        ObjectInputStream inputStream = null;
        ArrayList<Object> list = new ArrayList<>();
        try {
            inputStream = new ObjectInputStream(new FileInputStream(filename));
            boolean EOF = false;
            while (!EOF) {
                try {
                    Object myObj;
                    myObj = inputStream.readObject();

                    list.add(myObj);
                } catch (ClassNotFoundException e) {
                    //System.out.println("Class not found");
                } catch (EOFException end) {
                    // EOFException is raised when file ends
                    // set End Of File flag to true so that loop exits
                    EOF = true;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find file");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null)
                    inputStream.close();
            } catch (IOException e) {
                //System.out.println("IO Exception while closing file");
            }
        }
        if (!list.isEmpty())
            System.out.println(list.get(0).getClass());
//        if (filename.contains("Player"))
//            for (Object o : list)
//                System.out.println(((Player) o).getSelected());
        System.out.println("END READFILE");
        return list;
    }

    public static ArrayList<Object> SearchFromFile(String value, String filename) {
//		filename = filename;
        value = value.toUpperCase();
        System.out.println("In SEARCH FILE");
        ArrayList<Object> list = ReadFromFile(filename);
//		System.out.println(list);
        ArrayList<Object> results = new ArrayList<Object>(0);
        int Case = 0;
//        for (int i = 0 ; i < list.size();i++)                           For Checking All Usernames till search ends
//            System.out.println(list.get(i).getUser_Name());
        if (filename.contains("Player")) Case = 1;
        else if (filename.contains("Coach")) Case = 2;
        else if (filename.contains("Team")) Case = 3;
        boolean found = false;
        switch (Case) {
            case 1:
                for (int i = 0; i < list.size(); i++)
                    if (((Player) list.get(i)).getName().contains(value))
                        results.add(list.get(i));

                break;
            case 2:
                for (int i = 0; i < list.size(); i++)
                    if (((Coach) list.get(i)).getName().contains(value))
                        results.add(list.get(i));
                break;
            case 3:
                for (int i = 0; i < list.size(); i++)
                    if (((Team) list.get(i)).getTeamName().contains(value))
                        results.add(list.get(i));
                break;
        }
        System.out.println("END SEARCH FILE" + results.toString());
        return results;
    }

    public static boolean Find(String value, String filename) {
//		filename = "src/Files/"+filename;
        value = value.toUpperCase();
        System.out.println("In FIND FILE");
        ArrayList<Object> list = ReadFromFile(filename);
//		System.out.println(list);

        int Case = 0;
//        for (int i = 0 ; i < list.size();i++)                           For Checking All Usernames till search ends
//            System.out.println(list.get(i).getUser_Name());
        if (filename.contains("Player")) Case = 1;
        else if (filename.contains("Coach")) Case = 2;
        else if (filename.contains("Team")) Case = 3;
        boolean found = false;
        System.out.println("CASE :" + Case);
        switch (Case) {
            case 1:
                for (int i = 0; i < list.size(); i++)
                    if (((Player) list.get(i)).getName().equalsIgnoreCase(value))
                        return true;
                break;
            case 2:
                for (int i = 0; i < list.size(); i++)
                    if (((Coach) list.get(i)).getName().equalsIgnoreCase(value))
                        return true;
                break;
            case 3:
                for (int i = 0; i < list.size(); i++)
                    if (((Team) list.get(i)).getTeamName().equalsIgnoreCase(value))
                        return true;
        }

        return false;
    }


    static void UpdatePlayerFile(String[] players) throws IOException {

        System.out.println("IN UPDATE FILE");
        ObjectOutputStream outputStream = null;
        ArrayList list = ReadFromFile("Player.ser");

        for (int h = 0; h < players.length; h++) {
            String[] data = new String[2];
            for (String str : data)
                System.out.println("a" + str);

            data[0] = players[h].substring(0, players[h].indexOf("{"));
            data[1] = players[h].substring(players[h].indexOf('{'));
            data[1] = data[1].replace("}", "");
            data[1] = data[1].replace("{", "");
//                    data[1] =data[1].replace(",","");
            data[1] = data[1].trim();

            for (String str : data)
                System.out.println("b" + str);

            for (int i = 0; i < list.size(); i++) {
                Player player = (Player) list.get(i);
                if (player.getName().equalsIgnoreCase(data[0]) && player.getAge() == Integer.parseInt(data[1])) {
                    System.out.println("MATCH" + data[0]);
                    player.setUnselected();
                    break;
                }
            }
        }
        System.out.println(list.toString() + "TO BE WRITTEN");
        outputStream = new ObjectOutputStream(new FileOutputStream("src/Files/Player.ser"));
        for (int i = 0; i < list.size(); i++) {
            outputStream.writeObject(list.get(i));
        }

    }

     static Boolean deleteTeam(String teamName) throws IOException {
        try{
            ArrayList arr = ReadFromFile("Team.ser");
            Object obj = null;
            for (Object o : arr)
                if (((Team)o).getTeamName().equalsIgnoreCase(teamName)){
                    obj=o;
                    System.out.println("match");
                }

            System.out.println( arr.remove(obj));
            String filename = "src/Files/Team.ser";
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filename));
            // in case of appending a new object to the file

            for (int i = 0; i < arr.size(); i++) {
                outputStream.writeObject(arr.get(i));
            }
            UpdatePlayerFile(((Team)obj).getPlayers());
            return true;
        }catch (Exception e){
            return false;
        }
     }
}