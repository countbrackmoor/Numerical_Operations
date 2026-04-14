/* Project 1: Numeric Conversions
 * 
 * Purpose: The "numericConverter" method will convert a positive integer in a specified base to another base.
 * 
 * Input: A positive integer (including hexadecimal), a starting base (2-25), and a target base (2-25)
 * Output: The positive integer converted from the starting base to the target base
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NumericConversions {

    //main method
    public static void main(String args[]) {

        //Scanner is opened and inputs are requested
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter a positive integer: ");
        String integer = input.next().toLowerCase(); // Convert to lowercase for consistency
        System.out.println("Please specify the base of that integer, between 2 and 25: ");
        int oldBase = input.nextInt();
        System.out.println("Please specify the base you would like that integer converted to, between 2 and 25");
        int newBase = input.nextInt();

        // Validate bases
        if (oldBase < 2 || oldBase > 25 || newBase < 2 || newBase > 25) {
            System.out.println("Bases must be between 2 and 25. Please restart the program.");
            return;
        }

        //numericConverter method is called using the requested values as parameters
        String result = numericConverter(integer, oldBase, newBase);
        System.out.println(result);
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

        // Validate input
        if (toBeConverted == null || toBeConverted.isEmpty()) {
            return "Input cannot be empty.";
        }

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
                charValue = (letters.indexOf(c) + 10);   //if a letter, find the numerical value against the alphabet arraylist and convert it to an int
            }

            // Check if charValue is valid for the starting base
            if (charValue < 0 || charValue >= startingBase) {
                return "Value entered is invalid for the specified base. Please restart program and try again.";
            }

            convertedToBase10 = convertedToBase10 + (charValue * ((int) (Math.pow(startingBase, power))));   // Converts the character to base 10 and adds to the total
            power--;    //reduces the power by 1 to move right one digit and loop until each digit is accounted for
        }

        int num = convertedToBase10;  //set num to number converted to base 10 to prepare division
        int den = targetBase;         //set den to targetBase, also to prepare for division
        int remainder;                //declare int remainder to hold the remainder after division
        char remainderIfLetter;       //a separate char variable to hold the remainder if greater than 9

        /**
         * Target Base Conversion Loop: Uses division to convert base 10 integer
         * to target base
         */
        while (num > 0) {      //while num is greater than 0
            remainder = num % den;      //capture remainder
            num = num / den;            //update num for next iteration

            /**
             * Build converted integer from right to left
             */
            if (remainder > 9) {                                   //checks if letter is needed
                remainderIfLetter = letters.get(remainder - 10);   //finds corresponding letter from arraylist
                converted = remainderIfLetter + converted;         //adds letter to String converted, from right to left 

            } else {                                               //if remainder < 10, adds the remainder to String converted as a String
                converted = Integer.toString(remainder) + converted;
            }
        }

        /**
         * Returns a sentence summarizing the results, eg: "10101 converted from
         * base 2 to base 12 is 19_12"
         */
        return toBeConverted + " converted from base " + startingBase + " to base " + targetBase + " is " + converted + "_" + targetBase;

        //numericConverter()
    }
    //Class NumericConversions
}
