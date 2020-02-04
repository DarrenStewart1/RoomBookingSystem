package data;

import java.io.Serializable;

/**
 * class that is used to create the client object
 * @author Darren
 *
 */

@SuppressWarnings("serial")
public class Client implements Serializable
{
    private String givenName;
    private String familyName;
    private String emailAddress;
    private String phoneNumber;
    private int clientID;
    
   
    
    /**
     * system will use this constructor for a client that has given a phone number
     * @param clientID holds the id of the client
     * @param givenName holds the value of the clients first name 
     * @param familyName holds the value of the clients last name
     * @param emailAddress holds the clients email address
     * @param phoneNumber holds the clients phone number
     */
    public Client(int clientID, String givenName , String familyName , String emailAddress , String phoneNumber)
    {
        this.clientID = clientID;
        this.givenName = givenName;
        this.familyName = familyName;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        
        
    }
    
    /**
     * constructor that will be used if the client has not given a phone number
     * @param clientID holds the id of the client
     * @param givenName holds the value of the clients first name
     * @param familyName holds the value of the clients last name
     * @param emailAddress holds the clients email address
     */
    public Client(int clientID, String givenName , String familyName, String emailAddress)
    {
        this.clientID = clientID;
        this.givenName = givenName;
        this.familyName = familyName;
        this.emailAddress = emailAddress;
      
    }
    /**
     * @return the id of the client
     */
    public int getClientID()
    {
        return clientID;
    }

    /**
     * Set the id of the client 
     * @param clientID the id of the client
     */
    public void setClientID(int clientID)
    {
        this.clientID = clientID;
    }

    /**
     * @return the given name of the client
     */
    public String getGivenName()
    {
        return givenName;
    }
       
    /** Set the given name of the client
     * @param givenName the given name of the client
     */
    public void setGivenName(String givenName)
    {
        this.givenName = givenName;
    }
   
    /**    
     * @return the family name of the client
     */
    public String getFamilyName()
    {
        return familyName;
    }
    
    /**
     * Set the family name of the client
     * @param familyName the family name of the client
     */
    public void setFamilyName(String familyName)
    {
        this.familyName = familyName;
    }
    
    /**
     * @return the email address of the client
     */
    public String getEmailAddress()
    {
        return emailAddress;
    }
    
    /**
     * Set the email address of the client
     * @param emailAddress the email address of the client
     */
    public void setEmailAddress(String emailAddress)
    {
        this.emailAddress = emailAddress;
    }
    
    /**
     * @return the phone number of the client
     */
    public String getPhoneNumber()
    {
        return phoneNumber;
    }
   
    /**
     * Set the phone number of the client
     * @param phoneNumber the phone number of the client
     */
    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
        
        
    }
    
    //String representation that can be displayed of the client object
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("\n----------------------------------------------------------------------------------------------------------------------------------------");
        sb.append("\nClient ID : ");
        sb.append(clientID);
        sb.append("\nClients full name : ");
        sb.append(givenName);
        sb.append(" ");
        sb.append(familyName);
        sb.append("\nClients Email : ");       
        sb.append(emailAddress);      
        sb.append("\nPhone Number : ");
        sb.append(phoneNumber == null ? "No phone number given" : phoneNumber);
       
        return sb.toString();
    }
   

}
