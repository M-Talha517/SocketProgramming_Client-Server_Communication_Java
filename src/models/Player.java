package models;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Player implements Serializable {
    private String name;
//        int shirt_number;
    private String role;
    private int age;
    private int rating;
    private boolean selected ;

    @Override
//    public String toString() {
//        return "Player{" +
//                "name='" + name + '\'' +
//                ", role='" + role + '\'' +
//                ", age=" + age +
//                ", rating=" + rating +
//                ", selected=" + selected +'}';
//    }
    public String toString() {
        return "'"+name+"' ,"+age+"\n";
    }
    public String[] toArray(){
        String[] array = new String[4];
        array[0]=(name);
        array[1]=(Integer.toString(age));
        array[2]=(role);
        array[3]=(Integer.toString(rating));
        return array;
    }

    public String toString(String space) {
        return space+"Player{\n" + space+
                "    name=" + name + '\n' + space +
                "    role=" + role + '\n' + space +
                "    age=" + age + '\n' + space +
                "    rating=" + rating +'\n' + space+
//                ", selected=" + selected +'}';
                '}';
    }

    public Player(){};

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public int getAge() {
        return age;
    }

    public int getRating() {
        return rating;
    }

    public void setAllData(ArrayList Data){
        this.name = (String) Data.get(0);
        this.role = (String) Data.get(1);
        this.age = (int) Data.get(2);
        this.rating = (int) Data.get(3);
    }

    public void setSelected(){
        selected = true;
    }
    public void setUnselected(){
        selected = false;
    }

    public boolean getSelected(){
        return selected;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

//    public Player(String name, int player_number, String role, int age, String rating) {
//        this.name = name;
////        this.shirt_number = player_number;
//        this.role = role;
//        this.age = age;
//        this.rating = rating;
//    }

}
