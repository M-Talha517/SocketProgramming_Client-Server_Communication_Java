package Backend.Client;

import java.util.Scanner;

public interface Input {

    static int inputInt(String inputText, String errMessage, int min, int max){
        int input = 0;
        System.out.print(inputText);
        Scanner sc = new Scanner(System.in);
        while(true){
            
            if(sc.hasNextInt()) {
                input = sc.nextInt();
                if (input>=min && input<=max)
                    break;
            }else{sc.next();}
            System.out.println(errMessage);
            
        }

        return input;
    }

    static double inputDouble(String inputText, String errMessage, double min, double max){
        double input = 0;
        System.out.print(inputText);
        Scanner sc = new Scanner(System.in);
        while(true){
            if(sc.hasNextDouble()) {
                input = sc.nextDouble();
                if (input>=min && input<=max)
                    break;
            }
            System.out.println(errMessage);
        }
        return input;
    }


    static String inputLine(String inputText){
        Scanner input = new Scanner(System.in);
        System.out.print(inputText);
        return input.nextLine().toUpperCase();
    }

    static String inputWord(String inputText){
        Scanner input = new Scanner(System.in);
        System.out.print(inputText);
        return input.next().toUpperCase();
    }
}