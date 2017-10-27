/* Project 1: Numeric Conversions
 * 
 * Purpose: The "numericConverter" method will convert a positive integer in a specified base to another base.
 * 
 * Input: A positive integer (including hexidecimal), a starting base (2-25), and a target base (2-5)
 * Output: The positive integer converted from the starting base to the target base
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WylanAdamCSC205NumericConversions {

    //main method
    public static void main(String args[]) {

        //Scanner is opened and inputs are requested
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter a positive integer: ");
        String integer = input.next();
        System.out.println("Please specify the base of that integer, between 2 and 25: ");
        int oldBase = input.nextInt();
        System.out.println("Please specify the base you would like that integer converted to, between 2 and 25");
        int newBase = input.nextInt();

        //numericConverter method is called using the requested values as parameters
        System.out.println(numericConverter(integer, oldBase, newBase));
        //main()
    }

    /**
     *
     * @param toBeConverted the positive integer requested entered as a String
     * @param startingBase the starting base requested for the integer, an Int
     * value
     * @param targetBase the target base requested to convert the integer to,
     * also an Int Value
     * @return Returns a String with the previous information
     */
    public static String numericConverter(String toBeConverted, int startingBase, int targetBase) {

        /**
         * Create an arraylist and fill it with the alphabet
         */
        List<Character> letters = new ArrayList<>(26);
        for (char x = 'a'; x <= 'z'; x++) {
            letters.add(x);
        }

        String converted = ""; //initializes the String "converted" variable as an empty string that will house the final converted value

        int power = toBeConverted.length() - 1;    //sets power value to length of string (-1 to start at 0)
        int convertedToBase10 = 0;
        int charValue;

        /**
         * BASE 10: Conversion Loop: Loops through each digit of the String from
         * left to right, constantly adding the value to the total to convert to
         * Base 10
         */
        for (int i = 0; i < toBeConverted.length(); i++) {
            char c = toBeConverted.charAt(i); //inspects character at i

            if (Character.isDigit(c)) {   //if character is a number
                charValue = Character.getNumericValue(c);  //convert it to an int
            } else {
                charValue = (letters.indexOf(c) + 10);   //if a letter, find the numerical value against the alphabet arraylist and conver it to an int
            }

            convertedToBase10 = convertedToBase10 + (charValue * ((int) (Math.pow(startingBase, power))));   // Converts the character to base 10 and adds to the total
            power--;    //reduces the power by 1 to move right one digit and loop until each digit is accounted for

            /**
             * This is important! After the digit for this loop is converted, it
             * checks to make sure the value isn't higher than the specified
             * base. e.g.: Using a 4 in base 2 is invalid, as it's higher than
             * the base.
             */
            if (startingBase <= charValue) {
                return "Value entered is invalid at the specified base. Please restart program and try again.";
            }
            //Base 10 Conversion Loops repeats until fully converted the positive integer to base 10, or finding an invalid value.
        }

        int num = convertedToBase10;  //set num to number converted to base 10 to prepare division
        int den = targetBase;         //set den to targetBase, also to prepare for division
        int remainder;                //declare int remainder to hold the remainder after division
        char remainderIfLetter;       //a separate char variable to hold the remainder if greater than 9

        /**
         * Target Base Conversion Loop: Uses division to convert base 10 integer
         * to target base
         */
        while ((num / den) >= 1) {      //while num / den is not a fraction
            remainder = num % den;      //capture remainder

            if (num % den > 0) {                  //if num does not divide evenly
                num = ((num - remainder) / den);  //then subtract remainder from num for next loop's division
            } else {                              //else divide as normal
                num = (num / den);
            }

            /**
             * Build converted integer from right to left
             *
             */
            if (remainder > 9) {                                   //checks if hexadecimal is needed
                remainderIfLetter = letters.get(remainder - 10);   //finds corresponding letter from arraylist
                converted = remainderIfLetter + converted;         //adds letter to String converted, from right to left 

            } else {                                               //if remainder < 10, adds the remainder to String converted as a String
                converted = Integer.toString(remainder) + converted;
            }

        }
        /**
         * Performs Target Base Conversion one last time, if necessary, once num
         * is less than den
         */
        if ((num / den) < 1) {
            remainder = num;
            if (remainder > 9) {
                remainderIfLetter = letters.get(remainder - 10);
                converted = remainderIfLetter + converted;
            } else {
                converted = Integer.toString(remainder) + converted;
            }

        } else {
        }

        /**
         * Returns the converted number
         */
        return converted;

        //numericConverter()
    }
    //Class WylanAdamCSC205NumericConversions
}
