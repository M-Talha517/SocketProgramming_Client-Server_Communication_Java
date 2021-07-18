package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class Coach implements Serializable {
    String name;
    int age;
    String role;
    boolean selected = false;    

    @Override
    public String toString() {
        return "Coach{" +
                " name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    public String[] toArray(){
        String[] array = new String[3];
        array[0]=(name);
        array[1]=(Integer.toString(age));
        array[2]=(role);
        return array;
    }

    public static String[] CoachAttributes(){
        return (String[]) Arrays.stream(((Object) new Coach()).getClass().getDeclaredFields()).map(field -> field.getName()).toArray();
    };
    public String toString(String space) {
        return space+"Coach{\n" + space+
                "    name=" + name + '\n' + space +
                "    age=" + age + '\n' + space +
                "    role=" + role + '\n' + space +
                '}';
    }
    public void setAllData(ArrayList Data){
        this.name = (String) Data.get(0);
        this.age = (int) Data.get(1);
        this.role = (String) Data.get(2);
        
    }
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getRole() {
        return role;
    }

    public void setSelected(){
        this.selected = true;
    }
}
