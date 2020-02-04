package ui;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import data.Booking;
import data.DataManager;



/**
 * class that has methods that allow different types of data to be entered
 * @author Darren - 538981
 *
 */
public class IOUtils
{

    private DateTimeFormatter dateFormatter;
    private DateTimeFormatter timeFormatter;
    
    
    /**
     * the format that is used for date and time values
     * @param dateFormat the format that is used to handle date values entered
     */
    public IOUtils(String dateFormat)
    {
        dateFormatter = DateTimeFormatter.ofPattern(dateFormat);
        timeFormatter = DateTimeFormatter.ofPattern("HH");
    }
    
    
    /**
     * allows the user to enter their name this will check the string of the email to ensure that its the correct criteria for it to be valid
     * @param userInput the input that the user has entered to be checked
     * @param msg the message that will be displayed
     * @return the validated name
     */
    public String readNameInput(Scanner userInput, String msg)
    {
        //ensures that the name only contains alphabetical characters
        String pattern = "[a-zA-Z]+";
        
        String input = "";
        do {
            System.out.printf("Please enter the %s: ", msg);
            input = userInput.next().trim();
            
            if(!input.matches(pattern))
            {
                System.out.println("invalid name");
                input = "";
            }
        }
        while (input.equals(""));

        return input;
    }
    
    /**
     * allows the user to enter a email address this will check the string of the email to ensure that its the correct criteria for it to be valid
     * @param userInput the input that the user has entered to be checked
     * @param msg the message that will be displayed
     * @return the validated email address
     */public String readEmailInput(Scanner userInput, String msg)
    {
        // ensures that the local part of the mail only contains letters and numbers and is between 3 and 100 letters long ensures that domain name comes after the @ and then must end with either .com or .co.uk
        String pattern = "^[a-zA-Z0-9]{1,100}@[a-zA-Z]+\\.(?:co.uk|com|org|ac.uk)$";
        String input = "";
        do {
            System.out.printf("Please enter the %s: ", msg);
            input = userInput.next().trim();
            
            if(!input.matches(pattern))
            {
                System.out.println("invalid Email address please enter a valid address : example scottsteiner@gmail.com ");
                input = "";
            }
        }
        while (input.equals(""));

        return input;
    }
     
     /**
      * Allows the user to enter a phone number which is validated to ensure that the phone number is valid
     * @param userInput allows the user to enter thier phone number
     * @param msg the message that will be displayed to the user
     * @return
     */
    public String readPhoneNumber(Scanner userInput, String msg)
     {
    	 String input = "";
    	 //ensure that the phone number only contains numbers
    	 String pattern = "^[0-9]{11}";
    	 do {
             System.out.printf("Please enter the %s: ", msg);
             input = userInput.next().trim();
  
             if(input.length() != 11)
             {
                 System.out.println("Phone number could not be created : Phone number must contain 11 digits please Entered please enter a valid phone numeber Example 80342223331");
                 input = "";
             }
             else if(!input.matches(pattern))
             {
            	 System.out.println("Phone number could not be created :  Phonen number can only contains numbers  Entered please enter a valid phone numeber Example 80342223331");
            	 input = "";
            	 
             }
            
             
         }
         while (input.equals(""));
    	 
    	 return input;
    	 
  	 
     }
     
    
    
    /**
     * allows the user to enter an integer value into the system
     * @param userInput scanner that allows user input to be entered
     * @param msg the message that will be displayed to the user 
     * @param lowerBound the minimum value that can be entered by the user
     * @param upperBound the maximum value that can be entered by the user
     * @return the valid value that was entered by the user
     */
    public int readInteger(Scanner userInput, String msg, int lowerBound, int upperBound)
    {
        int value = 0;

        while (true) {
            System.out.print(msg);
            String input = userInput.next();
            try {
                value = Integer.parseInt(input);
            }
            catch (NumberFormatException nfx) {
                System.out.print("Error: You must enter an integer\n");
                continue;
            }
            if (value >= lowerBound && value <= upperBound) {
                break;
            }
            System.out.printf("Error: You must enter a value in the range %d-%d\n", lowerBound, upperBound);
        }

        return value;
    }
    
    /**
     * Allows the user to enter string data
     * @param userInput scanner that allows user input to be entered
     * @param msg the message that will be displayed to the user 
     * @return the string that that was entered by the user
     */
    public String readStringInput(Scanner userInput, String msg)
    {
        String input = "";
        do {
            System.out.printf("Please enter the %s: ", msg);
            input = userInput.next().trim();
        }
        while (input.equals(""));

        return input;
    }
    /**
     * allows a user to enter a date value in the format of dd/MM/yyyy and ensure that the value is not in the past
     * @param userInput scanner that allows user input to be entered
     * @return the date value that was entered by the user
     */
    public LocalDate readDateInput(Scanner userInput)
    {
        LocalDate bookingDate = null;
        

        while (bookingDate == null) {
            
            System.out.printf("Please enter the date of the booking you want to make ",
                    
            DataManager.DATE_FORMAT_STRING.toUpperCase());
            String input = userInput.next();
            System.out.println("date was " + input);
            try {
                bookingDate = LocalDate.parse(input, dateFormatter);
                
                if (!Booking.isValidBookingDate(bookingDate)) {
                    System.out.println("The date cannot be in the past");
                    bookingDate = null;
                }
            }
            catch (DateTimeParseException px) {
                System.out.println("The format of the date was invalid please use the valid format dd/MM/yyyy");
            }
        }

        return bookingDate;
    }
    
    
    /**
     * Allows the user to enter a time in the format of HH
     * @param userInputscanner that allows user input to be entered
     * @return return the time that the user entered
     */
    public LocalTime readTimeInput(Scanner userInput)
    {
        LocalTime bookingTime = null;
        
        while (bookingTime == null) {
            
            System.out.println("Please the time you want to make the booking. Books can only be created on the hour");
            String time = userInput.next();
            try {
                bookingTime = LocalTime.parse(time,timeFormatter);
                
               
            }catch(DateTimeParseException e)
            {
                System.out.println("the time that was entered was not correct please enter the correct time in the 24 hour format");
            }
           
                   
        }
            
      return bookingTime; 
    }
     
     /**
     * user will enter 1 if the boolean is to be true and 0 if it is to be false this will then return a boolean value
     * @param userInputscanner that allows user input to be entered
     * @param msg the message that will be displayed to the user 
     * @param lowerBound the minimum value that can be entered by the user
     * @param upperBound the maximum value that can be entered by the user
     * @return the boolean value
     */
    public boolean readBoolean(Scanner userInput, String msg, int lowerBound, int upperBound)
     {
         int value = 0;

         while (true) {
             
             System.out.print(msg);
             String input = userInput.next();
             try {
                 
                 value = Integer.parseInt(input);
               
             }
             
             catch (NumberFormatException nfx) {
                 System.out.print("Error: You must enter an integer\n");
                 continue;
             }
             if (value >= lowerBound && value <= upperBound) {
                 break;
             }
             System.out.printf("Error: You must enter a value in the range %d-%d\n", lowerBound, upperBound);
         }
         if(value == 1)
         {
             return true;
         }   
         else return false;

         
     }
   
    
}
        
        
        
        
