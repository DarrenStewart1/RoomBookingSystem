package ui;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import data.Booking;
import data.Client;
import data.DataManager;
import data.Room;

/**
 * system that allows clients to make bookings
 * 
 * @author Darren - 538981 583981
 *
 */
public class Driver {

	private static DataManager dataManager;

	private static IOUtils ioUtilities;

	private static final int MIN_NUMBER_OF_SEATS = 0;
	private static final int MAX_NUMBER_OF_SEATS = 20;

	private static final int BOOLEAN_YES_CHOICE = 1;
	private static final int BOOLEAN_NO_CHOICE = 0;

	private static final int BOOKING_DURATION_MIN = 1;
	private static final int BOOKING_DURATION_MAX = 1;

	private static final int MENU_SELECTION_MIN = 1;
	private static final int MENU_SELECTION_MAX = 10;

	// private Map<String, Room> rooms = new HashMap<String, Room>();

	// 1Scanner userInput = new Scanner(System.in);

	/**
	 * on start up retrieve the information that is currently saved in the system
	 * then display the main menu
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		dataManager = new DataManager();
		ioUtilities = new IOUtils(DataManager.DATE_FORMAT_STRING);

		loadSystemInfo();

		System.out.println(
				"!!!!!!FOR INFOMRATION TO BE SAVED THE APPLICATION MUST BE CLOSED PROPERLY BY PRESSING(10) WHEN ON THE MAIN MENU!!!!!!!");

		try (Scanner consoleInput = new Scanner(System.in)) {

			processData(consoleInput);

		} catch (Exception x) {
			x.printStackTrace();
			System.err.println("There was an error in the system");
			System.err.println(x.getMessage());
		}

	}

	/**
	 * menu for the system that allows the user to select what part of the
	 * application they want to use
	 * 
	 * @param userInput the input the user enters this will be used as their menu
	 *                  choice
	 */
	private static void processData(Scanner userInput) {
		boolean runProgram = true;

		while (runProgram) {

			System.out.println(
					"\nMain menu please press a button to interact with the applicationn \n Press (1) to add a new client \n Press (2) to add a new booking \n press (3) list clients in the system "
							+ "\n press (4) list the current bookings in the system  \n press (5) view a specific booking of a client \n Press (6) view a specific client"
							+ "\n press (7) remove a client from the system \n press (8) remove a booking from the system \n press (9) View a clients bookings \n press (10) to save and exit the system");
			int option = ioUtilities.readInteger(userInput, " : ", MENU_SELECTION_MIN, MENU_SELECTION_MAX);

			switch (option) {
			case 1:

				addClient(userInput);
				break;
			case 2:
				addBooking(userInput);
				break;

			case 3:
				listClients();
				break;
			case 4:
				listBookings();
				break;
			case 5:
				viewSpecificBooking(userInput);
				break;
			case 6:
				viewSpecificClient(userInput);
				break;
			case 7:
				removeClient(userInput);
				break;
			case 8:
				cancelBooking(userInput);
				break;
			case 9:
				viewClientBooking(userInput);
				break;
			case 10:
				exitSystem();
			}

		}

	}

	/**
	 * adds the booking into the hashset and confirms to the user that it has been
	 * added
	 * 
	 * @param userInput scanner that handles any of the users inputs into the system
	 */
	public static void addBooking(Scanner userInput) {
		System.out.println("adding Booking");

		Booking b = addBookingDetails(userInput);
		
		if(b != null)

		System.out.printf("added record : %s\n ", b);

	}

	/**
	 * The user enters the requirements that they have for the room the want to book
	 * the system will then look for the room that is the closest match to what the
	 * user needs
	 * 
	 * @param userInput scanner that handles any of the users inputs into the system
	 * @return
	 */
	public static Booking addBookingDetails(Scanner userInput) {

		int workStations = ioUtilities.readInteger(userInput,
				"Enter the number of work stations required. The maximum number of seats we have avaliable is 20 : ",
				MIN_NUMBER_OF_SEATS, MAX_NUMBER_OF_SEATS);

		System.out.printf("You entered %d work stations\n", workStations);

		// int breakOutSeats = ioUtilities.readInteger(userInput, "Enter the number of
		// break out seats required. The maximum number of seats we have avaliable is 20
		// : ",MIN_NUMBER_OF_SEATS , MAX_NUMBER_OF_SEATS);
		// boolean printerRequired = ioUtilities.readBoolean(userInput, "Does the room
		// require a printer press 1 if yes 0 if no :
		// ",BOOLEAN_NO_CHOICE,BOOLEAN_YES_CHOICE);
		// boolean smartBoardRequired = ioUtilities.readBoolean(userInput, "Does the
		// room require a smart board press 1 if yes 0 if no :
		// ",BOOLEAN_NO_CHOICE,BOOLEAN_YES_CHOICE);
		LocalDate bookingDate = ioUtilities.readDateInput(userInput);
		LocalTime bookingTime = ioUtilities.readTimeInput(userInput);
		int bookingDuration = ioUtilities.readInteger(userInput, "Enter the duration of the booking in hours : ",
				BOOKING_DURATION_MIN, BOOKING_DURATION_MAX);

		

	

		ArrayList<Room> roomList = new ArrayList<>();
		Room room = new Room(6, 0, 12, "no", "no");
		
		Room room1 = new Room(8, 18, 10, "yes", "yes");
		
		Room room2 = new Room(11, 20, 0, "yes", "yes");
		
		Room room3 = new Room(13, 6, 0, "no", "yes");
		
		Room room4 = new Room(14, 18, 2, "yes", "yes");
		
		Room room5 = new Room(15, 18, 10, "yes", "yes");
		
		Room room6 = new Room(17, 18, 10, "yes", "yes");
		
		Room room7 = new Room(108, 0, 10, "no", "yes");
		
		Room room8 = new Room(120, 18, 10, "yes", "yes");
		
		Room room9 = new Room(301, 18, 10, "yes", "yes");

		roomList.add(room);
		roomList.add(room1);
		roomList.add(room2);
		roomList.add(room3);
		roomList.add(room4);
		roomList.add(room5);
		roomList.add(room6);
		roomList.add(room7);
		roomList.add(room8);
		roomList.add(room9);
		

		ArrayList<Room> roomList2 = new ArrayList<>();

		// find all rooms the meet the requirement
		for (Room rooms : roomList) {
			
		
			if (rooms.getNumberOfWorkStations() >= workStations) {
				System.out.printf("Adding room %d with %d work stations\n", rooms.getRoomNumber(), rooms.getNumberOfWorkStations());
				roomList2.add(rooms);

	

			}
		}
		
		List<Room> roomList3 = new ArrayList<>();

		// remove any room that is already booked
		for (int i = 0; i < roomList2.size(); i++) {

			Room r = roomList2.get(i);
			
			
				
	
			boolean bookingAvaliable = dataManager.roomAvaliable(bookingDate, bookingDuration, bookingTime, r);

			
			if (bookingAvaliable) {
				System.out.printf("Adding room %d with %d work stations\n", r.getRoomNumber(), r.getNumberOfWorkStations());
				
			
				roomList3.add(r);
				

			}
			
		}

		if (roomList3.size() == 0) {
			// no rooms available
			System.out.println("no booking could be made");
			return null;
		}


		Room bestRoom = roomList3.get(0);
		// find the best matching room
		for (Room rr : roomList3) {
			//System.out.printf("Checking room %d with %d work stations\n", rr.getRoomNumber(), rr.getNumberOfWorkStations());
			
			if (bestRoom.getNumberOfWorkStations() > rr.getNumberOfWorkStations()) {
				
				bestRoom = rr;
				
				System.out.println("current best room is : " + bestRoom);

			}
		}
		
		boolean createBooking = confirmBooking(userInput, bestRoom);

		if (createBooking == true)
			//boolean bookingMade = false;

		{
			boolean bookingMade = false;
			Client clientBooking = null;
			//int idNum = ioUtilities.readInteger(userInput, "Enter the id number of the client making the booking : ",DataManager.MIN_CLIENT_ID, DataManager.MAX_BOOKING_ID);
		
			//Client clientBooking = dataManager.retriveClient(idNum);
			do
			{
				
			int idNum = ioUtilities.readInteger(userInput, "Enter the id number of the client making the booking : ",DataManager.MIN_CLIENT_ID, DataManager.MAX_BOOKING_ID);
			clientBooking = dataManager.retriveClient(idNum);
				
				
			if(clientBooking == null)
			{
			System.out.println("Incorrect id entered pleaes enter a valid number");
			bookingMade = false;
			
			}
			else 
			
				bookingMade = true;
			
			}while(bookingMade == false);
			
			Booking b = getBookingDetails(clientBooking, bookingDate, bookingDuration, bookingTime, bestRoom);
			return b;
			/*boolean bookingMade = false;
			do
			{
			
				if(clientBooking == null)
				{
					 System.out.println("Incorrect booking id entered");
					 idNum = ioUtilities.readInteger(userInput, "Enter the id number of the client making the booking : ",DataManager.MIN_CLIENT_ID, DataManager.MAX_BOOKING_ID);
					 clientBooking = dataManager.retriveClient(idNum);
					 if()
					 
				}
				else
					bookingMade = true;
			
			}while(bookingMade == false);
			*/
			
			//Booking b = getBookingDetails(clientBooking, bookingDate, bookingDuration, bookingTime, bestRoom);
			
			//return b;
			
		} 
		
			return null;

	

	}

	

	/**
	 * will retrieve information about a booking
	 * 
	 * @param clientBooking   information that is stored about the client who made
	 *                        the booking
	 * @param bookingDate     the date that the booking has been made for
	 * @param bookingDuration the date duration of the booking
	 * @param bookingTime     the time that the booking has been made for
	 * @param room            Information about the room that has been booked
	 * @return
	 */
	private static Booking getBookingDetails(Client clientBooking, LocalDate bookingDate, int bookingDuration,
			LocalTime bookingTime, Room room) {
		return dataManager.addBooking(bookingDate, clientBooking, bookingDuration, bookingTime, room);

	}

	/**
	 * adds the new client into the system and confirms that they have been added
	 * 
	 * @param userInput
	 */
	private static void addClient(Scanner userInput) {
		System.out.println("adding client");

		Client c = addClientDetails(userInput);

		System.out.printf("added record : %s\n ", c);
	}

	/**
	 * get information about the client and based on the information given will
	 * create a new client object
	 * 
	 * @param userInput scanner for data that the user enters
	 * @return the clients details
	 */
	private static Client addClientDetails(Scanner userInput) {

		String clientType;

		String givenName = ioUtilities.readNameInput(userInput, "Persons given name");
		String familyName = ioUtilities.readNameInput(userInput, "family name");
		String emailAddress = ioUtilities.readEmailInput(userInput, "email address");

		System.out.println("Do you want to give your phone number Yes/no");

		Client c = null;
		clientType = userInput.next();
		switch (clientType) {
		case "yes":

			c = getClientDetails(givenName, familyName, emailAddress, userInput);

			break;
		case "no":
			c = getClientDetailsNoPhone(givenName, familyName, emailAddress);
			break;

		}
		return c;

	}

	/**
	 * retrieve information about a client only used if the client decided to not
	 * give a phone number
	 * 
	 * @param givenName    the given name of the client
	 * @param familyName   the family name of the client
	 * @param emailAddress the email address of the client
	 * @return return a new client object that does not include a phone number
	 */
	
	private static Client getClientDetailsNoPhone(String givenName, String familyName, String emailAddress) 
	{
		

	    return dataManager.addClientNoPhone(givenName, familyName, emailAddress);
	    
	    
	}

	/**
	 * retrieve information about the client this is used when the client gives
	 * their phone number
	 * 
	 * @param givenName    the given name of the client
	 * @param familyName   the family name of the client
	 * @param emailAddress the email address of the client
	 * @param userInput    scanner that allows user input to be entered
	 * @return a new client object that does include a phone number
	 */
	private static Client getClientDetails(String givenName, String familyName, String emailAddress,
			Scanner userInput) 
	{
	    
		String phoneNumber = ioUtilities.readPhoneNumber(userInput, "phone number");

		return dataManager.addClient(givenName, familyName, emailAddress, phoneNumber);

	}

	/**
	 * removes a client from the system based on the client id number entered
	 * 
	 * @param userInput scanner that allows user input to be entered
	 */
	private static void removeClient(Scanner userInput) 
	{

		int idNum = ioUtilities.readInteger(userInput, "Enter the id of the client being removed : ",
				DataManager.MIN_CLIENT_ID, DataManager.MAX_CLIENT_ID);

		Client c = dataManager.deleteClient(idNum);

		if (c != null) {
			System.out.printf("\nRecord %s\nhas been deleted\n", c);
		} else
			System.out.println("no record found");

	}

	/**
	 * removes a client from the system based on the client id number entered
	 * 
	 * @param userInput scanner that allows user input to be entered
	 */
	public static void cancelBooking(Scanner userInput)
	{

		int idNum = ioUtilities.readInteger(userInput, "Enter the id of the booking that is being removed: ",
				DataManager.MIN_BOOKING_ID, DataManager.MAX_BOOKING_ID);

		Booking b = dataManager.cancelBooking(idNum);

		if (b != null) {
			System.out.printf("\nthe booking %s\nhas been deleted\n", b);
		} else
			System.out.println("no record found");

	}

	/**
	 * lists all of the current clients in the system
	 */
	public static void listClients() {
		dataManager.listClients();
	}

	/**
	 * lists all of the current bookings in the system
	 */
	public static void listBookings() {
		dataManager.listBookings();
	}

	/**
	 * View a specific booking based on the booking id that was entered
	 * 
	 * @param userInput userInput allows the user to enter data
	 */
	public static void viewSpecificBooking(Scanner userInput)

	{
		int idNumber = ioUtilities.readInteger(userInput, "Enter the id of booking you want to view : ",
				DataManager.MIN_BOOKING_ID, DataManager.MAX_BOOKING_ID);
		dataManager.listSpecificBooking(idNumber);
	}

	/**
	 * View a specific client based on the client id entered
	 * 
	 * @param userInput allows the user to enter data
	 */
	public static void viewSpecificClient(Scanner userInput)

	{
		int idNumber = ioUtilities.readInteger(userInput, "Enter the id of the client that you want to view : ",
				DataManager.MIN_CLIENT_ID, DataManager.MAX_CLIENT_ID);

		dataManager.listSpecificClient(idNumber);
	}

	/**
	 * saves all of the current booking and clients that are in the system the exits
	 * the application
	 */
	public static void exitSystem() {
		dataManager.saveBookings();
		dataManager.saveClients();
		System.out.println("System exited sucesfully");
		System.exit(0);

	}

	/**
	 * Asks the user if they are sure that they want to make a booking for the room
	 * that is being offered
	 * 
	 * @param userInput scanner that allows user input to be entered
	 * @param room      the information about the room
	 * @return
	 */
	public static boolean confirmBooking(Scanner userInput, Room room) {
		
			System.out.println(room);

			boolean bookingConfrimation = ioUtilities.readBoolean(userInput, "\nDo you want to book this room  if Yes Press 1 | if | No Press 0 : ",
				BOOLEAN_NO_CHOICE, BOOLEAN_YES_CHOICE);
			
			

		if (bookingConfrimation == true) {
			//System.out.println("Booking created sucesfully");

			return true;

		} else
			System.out.println("Booking was not made");

		return false;

	}

	/**
	 * Views all the bookings of a specific client based on the id given
	 * 
	 * @param userInput scanner that allows user input to be entered
	 */
	public static void viewClientBooking(Scanner userInput) {

		int idNum = ioUtilities.readInteger(userInput, "Enter the id number of the client bookings you want to view: ",
				DataManager.MIN_CLIENT_ID, DataManager.MAX_BOOKING_ID);

		dataManager.listCustomerBooking(idNum);

	}

	/**
	 * When the system is booted up this is always called in the main to load the files
	 */
	public static void loadSystemInfo() {
		dataManager.retriveBookingIDCounter();
		dataManager.retriveClientIDCounter();
		dataManager.retriveBookings();
		dataManager.retriveClients();
	}

}
