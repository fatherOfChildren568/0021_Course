/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;

/**
 *
 * @author ADMIN
 */
public class Validator {


    public static boolean checkInputString(String input) {
        while (true) {
            try {
                //neu khong rong => nhan string
                if (input.trim().isEmpty()) {
                    System.out.println("Input can not be empty");
                    return false;
                } else {
                    return true;
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public static boolean checkInputIntegerLimit(int input, int min, int max) {
        while (true) {
            try {
                if(input >= min && input <= max){
                    return true;
                } else {
                    System.out.format("Input must be in range [%s,%s]\n", min, max);
                    return false;
                }
            } catch (NumberFormatException e) {
                System.out.println("Input must be integer number");
            }
        }
    }
    
    public static boolean checkYN(String choice){
        if(choice.equalsIgnoreCase("y")){
            return true;
        } else if(choice.equalsIgnoreCase("n")){
            return false;
        }
        return false;
    }
    
    public static String getUD(String choice){
        if(choice.equalsIgnoreCase("U")){
            return "u";
        } else if(choice.equalsIgnoreCase("D")){
            return "d";
        }
        return null;
    }
}
