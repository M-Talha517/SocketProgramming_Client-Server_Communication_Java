package Backend.Client;

import Backend.Client.Input;
import models.Coach;
import models.Player;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;

import java.util.Hashtable;


public interface Generic_Object_Filler {
    // Map for taking minimum and maximum values for int input and error message
    Hashtable<String, ArrayList> Messages = new Hashtable<>() {{
        // FOR AGE OF PLAYER
        put("Playerage", new ArrayList() {{
                    add("Please Enter Valid Age between 18 to 55"); // invalid option message
                    add(18); // min
                    add(55);// max
                }}
        );
        // FOR RATING OF PLAYER
        put("Playerrating", new ArrayList() {{
                    add("Please Enter Valid Rating between 1 to 100"); // invalid option message
                    add(1); // min
                    add(100);// max
                }}
        );
        // FOR AGE OF COACH
        put("Coachage", new ArrayList() {{
                    add("Please Enter Valid Age between 35 to 70"); // invalid option message
                    add(35); // min
                    add(70); // max
                }}
        );
        // FOR INPUT FROM LIST OF PLAYERS OR COACHES
        put("ListItem", new ArrayList() {{
                    add("INVALID OPTION, PLEASE SELECT A NUMBER FROM ABOVE OR PRESS 0 to end."); // invalid option message

                }}
        );
    }};

    static void getObjectData(Object obj) {
        int integerInput ;
        String stringInput;


        Class<?> cls = obj.getClass();
        // to store data of all attributes of a class
        ArrayList<Serializable> objectData = new ArrayList<>();

        Field[] fs = cls.getDeclaredFields();

        System.out.println("Kindly Sides.Input The Following Data for the Entity: "+cls.getName());
        for (Field f : fs) {
            String fieldName = (f.getName());

            if ((f.getAnnotatedType().toString().contains("int"))) {
                integerInput = Input.inputInt(fieldName + ": ", (String) Messages.get(cls.getName() + fieldName).get(0),
                        (int) Messages.get(cls.getName() + fieldName).get(1), (int) Messages.get(cls.getName() + fieldName).get(2));
                objectData.add(integerInput);
            } else if ((f.getAnnotatedType().toString().contains("java.lang.String"))) {
                stringInput = Input.inputLine(fieldName + ": ");
                objectData.add(stringInput);
            }
//             else if ((f.getAnnotatedType().toString().contains("[]"))) {
//                 ArrayList<Object> list = new ArrayList<>(0);
//                 int objectCase = 0;
//                 if ((f.getAnnotatedType().toString().contains("Player"))) objectCase = 1;
//                 else if ((f.getAnnotatedType().toString().contains("Coach"))) objectCase = 2;
//                 switch (objectCase) {
//                     case 1:
//                         for (int i = 0; i < 11; i++) {
//                             list.add(new Player());
//                             getObjectData(list.get(i));
//                         }
//                     case 2:
//                         for (int i = 0; i < 11; i++) {
//                             list.add(new Coach());
//                             getObjectData(list.get(i));
//                         }
//                 }
//                 objectData.add(list);
//             }

        }
        if (obj instanceof Player) ((Player) obj).setAllData(objectData);
        if (obj instanceof Coach) ((Coach) obj).setAllData(objectData);
//         if (obj instanceof Team) ((Team) obj).setAllData(objectData);

    }
}

//else if ((f.getAnnotatedType().toString().contains("[]"))) {
//        ArrayList<Object> list = new ArrayList<>(0);
//        int objectCase = 0;
//        int count = 0;
//        selectedItem = new ArrayList<Integer>(0);
//
//        if ((f.getAnnotatedType().toString().contains("Player"))) {
//        objectCase = 1;
//        players = new ArrayList<Player>(Arrays.asList(((Team) obj).getPlayers()));
//        for (Player player : players)
//        if (!player.getSelected())
//        System.out.println(++count + player.toString());
//        else
//        players.remove(player);
//
//        }
//        else if ((f.getAnnotatedType().toString().contains("Coach"))) {
//        objectCase = 2;
//        coaches = new ArrayList<Coach>(Arrays.asList(((Team) obj).getCoaches()));
//        for (Coach coach : coaches)
//        System.out.println(++count + coach.toString());
//
//
//
//        switch (objectCase) {
//        case 1:
//        for (int i = 0; i < 11 && i <players.size(); i++) {
//        while (true) {
//        integerInput = Sides.Input.inputInt("SELECT PLAYER#" + i + " FROM ABOVE LIST OF PLAYERS", (String) Messages.get("ListItem").get(0), -1, players.size());
//        if (selectedItem.contains(integerInput)) {
//        System.out.println("YOU ALREADY SELECTED THIS PLAYER ENTER ANOTHER NUMBER");
//        continue;
//        }
//        break;
//        }
//        if (integerInput == 0) break;
//        list.add(players.get(--integerInput));
//        players.get(--integerInput).setSelected();
////                            getObjectData(list.get(i));
//        break;
//        }
//        case 2:
//        for (int i = 0; i < 11 && i <coaches.size() ; i++) {
//        while (true) {
//        integerInput = Sides.Input.inputInt("SELECT COACH#" + i + " FROM ABOVE LIST OF COACHES", (String) Messages.get("ListItem").get(0), -1, coaches.size());
//        if (selectedItem.contains(integerInput)) {
//        System.out.println("YOU ALREADY SELECTED THIS COACH ENTER ANOTHER NUMBER");
//        continue;
//        }
//        break;
//        }
//        if (integerInput == 0) break;
//        list.add(coaches.get(--integerInput));
//
////                            getObjectData(list.get(i));
//        break;
//        }
//        }
//        if (objectCase == 1) objectData.add(list.toArray(new Player[0]));
//        else if (objectCase == 2) objectData.add(list.toArray(new Coach[0]));
//        }
//        }