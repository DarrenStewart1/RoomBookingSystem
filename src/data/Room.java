package data;

import java.io.Serializable;

/**
 * Class that will create a room object there is two different types of rooms a computer room and meeting room
 * a meeting room does not contain any computers
 * @author Darren 538981
 *
 */
@SuppressWarnings("serial")
public class Room implements Serializable
{

    private int roomNumber;
    private int numberOfWorkStations;
    private int numberOfBreakOutSeats;
    private String printerReq;
    private String smartBoard;
    
    
    /**
     * this constructor will be used to make rooms that are computer labs
     * @param roomNumber
     * @param numberOfWorkStations
     * @param numberOfBreakOutSeats
     * @param printerReq
     * @param smartBoard
     */
    public Room(int roomNumber , int numberOfWorkStations , int numberOfBreakOutSeats , String printerReq , String smartBoard)
    {
        this.roomNumber = roomNumber;
        this.numberOfWorkStations = numberOfWorkStations;
        this.numberOfBreakOutSeats = numberOfBreakOutSeats;
        this.printerReq = printerReq;
        this.smartBoard = smartBoard;
    }

    /**
     * @return the room number
     */
    public int getRoomNumber()
    {
        return roomNumber;
    }


    /**
     * Set the room number
     * @param roomNumber the room number
     */
    public void setRoomNumber(int roomNumber)
    {
        this.roomNumber = roomNumber;
    }


    /**
     * @return the number of work stations the room has
     */
    public int getNumberOfWorkStations()
    {
        return numberOfWorkStations;
    }


    /**
     * Set the number of work stations the room has
     * @param numberOfWorkStations the number of work stations the room has
     */
    public void setNumberOfWorkStations(int numberOfWorkStations)
    {
        this.numberOfWorkStations = numberOfWorkStations;
    }


    /**
     * @return the number of break out seats the room has
     */
    public int getNumberOfBreakOutSeats()
    {
        return numberOfBreakOutSeats;
    }


    /**
     * Set the number of break out seats the room has
     * @param numberOfBreakOutSeats the number of break out seats the room has
     */
    public void setNumberOfBreakOutSeats(int numberOfBreakOutSeats)
    {
        this.numberOfBreakOutSeats = numberOfBreakOutSeats;
    }


    /**
     * @return if the room requires a printer or not
     */
    public String isPrinterReq()
    {
        return printerReq;
    }


    /**
     * Set if the room requires a printer or not
     * @param printerReq if a printer is needed
     */
    public void setPrinterReq(String printerReq)
    {
     
        this.printerReq = printerReq;
    }


    /**
     * @return if the room requires a smart board or not
     */
    public String isSmartBoard()
    {
        return smartBoard;
    }


    /**
     * Set if the room requires a smart board or not
     * @param smartBoard if a smart board is needed
     */
    public void setSmartBoard(String smartBoard)
    {
        this.smartBoard = smartBoard;
    }
    // creates a string representation of the room object
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("\nthe room number is: ");
        sb.append(roomNumber);
        sb.append(" \nthe number of work stations the room has : ");
        sb.append(numberOfWorkStations);
        sb.append(" \nthe number of break out seats the room has is ");
        sb.append(numberOfBreakOutSeats);
        sb.append(" \nthe room has a printer : ");
        
        sb.append(printerReq);
        
        sb.append(" the room has a smartboard : ");
        sb.append(smartBoard);
        return sb.toString();
    }
    
    
}
