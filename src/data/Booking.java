package data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;



/**
 * class that is used to create the booking object
 * @author Darren - 538981
 *
 */
@SuppressWarnings("serial")
public class Booking implements Serializable
{

    private LocalTime bookingTime;
    private LocalDate bookingDate;
    private int bookingDuration;
    private int bookingID;
    private Client clientBooking;
    private Room room;
    
    /**
     * Constructor for the booking object
     * @param bookingID the id of the booking this value will always be unqiue
     * @param clientBooking the information about the client who is making the booking
     * @param bookingDate the date that the booking has been made for
     * @param bookingDuration the duration of the booking
     * @param bookingTime the time of the booking
     * @param room information about the room that has been booked
     */
    public Booking(int bookingID, Client clientBooking , LocalDate bookingDate ,int bookingDuration ,LocalTime bookingTime , Room room ) 
    {
        this.bookingTime = bookingTime;
        this.bookingDate = bookingDate;
        this.bookingDuration = bookingDuration;    
        this.bookingID = bookingID;
        this.clientBooking = clientBooking;
        this.room = room;
        
    }
    
    
    /**
     * Validates the date of the booking to ensure that it is not in the past
     * @param bookingDate the date that the booking is being made for
     * @return a boolean value
     */
    public static boolean isValidBookingDate(LocalDate bookingDate)
    {
        if(bookingDate == null)
        {
            return false;
        }
        return bookingDate.isAfter(LocalDate.now());
        
        
    }
      
    /**
     * @return the room that has been booked
     */
    public Room getRoom() {
        return room;
    }

    /**
     * Set the room that has been booked
     * @param room the room that has been booked
     */
    public void setRoom(Room room) {
        this.room = room;
    }
    

    /**
     * @return the time that the booking was made for
     */
    public LocalTime getBookingTime()
    {
        return bookingTime;
    }

    /**
     * Set the time that the booking was made for
     * @param bookingTime the time that the booking was made for
     */
    public void setBookingTime(LocalTime bookingTime)
    {
        this.bookingTime = bookingTime;
        
    }

    /**
     * @return the date that the booking was made for
     */
    public LocalDate getBookingDate()
    {
        return bookingDate;
    }

    /**
     * Set the date that the booking was made for 
     * @param bookingDate the date that the booking was made for
     */
    public void setBookingDate(LocalDate bookingDate)
    {
        this.bookingDate = bookingDate;
    }

    /**
     * @return the duration of the booking
     */
    public int getBookingDuration()
    {
        return bookingDuration;
    }

    /**
     * Set the duration of the booking
     * @param bookingDuration the duration of the booking
     */
    public void setBookingDuration(int bookingDuration)
    {
        this.bookingDuration = bookingDuration;
    }

    /**
     * @return the id of the booking
     */
    public int getBookingID()
    {
        return bookingID;
    }

    /**
     * Set the id of the booking
     * @param bookingID the id of the booking
     */
    public void setBookingID(int bookingID)
    {
        this.bookingID = bookingID;
    }

    /**
     * @return the client who made the booking
     */
    public Client getClientBooking()
    {
        return clientBooking;
    }

    /**
     * Set the client who made the booking
     * @param clientBooking the client who made the booking
     */
    public void setClientBooking(Client clientBooking)
    {
        this.clientBooking = clientBooking;
    }
    
    // create a string representation of the booking object that can be displayed
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("\n------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        sb.append("\nThe ID of the booking : ");
        sb.append(bookingID);
        sb.append(" \nThe Room that has been booked is : ");
        sb.append("Room " + getRoom().getRoomNumber());
        sb.append(" \nThe booking was made by : ");
        sb.append(getClientBooking().getGivenName() + " "+getClientBooking().getFamilyName());
        sb.append(" \nThe ID of the client who made this booking is  : " + getClientBooking().getClientID());
        sb.append(" \nThe date of the booking is on : ");
        sb.append(bookingDate);
        sb.append(" \nThe time of the booking is : ");
        sb.append(bookingTime); 
        sb.append(" \nThe duration of the booking is : ");
        sb.append(bookingDuration + " Hour");   
       

     
    
        return sb.toString();
    }
    
    
}
