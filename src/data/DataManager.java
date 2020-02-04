package data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.time.LocalDate;

import java.time.LocalTime;
import java.util.ArrayList;

import java.util.HashSet;

/**
 * 
 * @author Darren - 538981
 *
 */
public class DataManager {

	public static final String DATE_FORMAT_STRING = "dd/MM/yyyy";

	public static final int MIN_CLIENT_ID = 10001;
	public static final int MAX_CLIENT_ID = 99999;

	public static final int MIN_BOOKING_ID = 10001;
	public static final int MAX_BOOKING_ID = 99999;

	private static int clientIDCounter = MIN_CLIENT_ID - 1;

	private static int bookingIDCounter = MIN_BOOKING_ID - 1;

	private static ArrayList<Client> clientList;

	private static HashSet<Booking> bookingList;
	// private Map<String,Room> roomList;

	public DataManager() {
		clientList = new ArrayList<>();
		bookingList = new HashSet<>();
		// roomList = new HashMap<String,Room>();
	}

	/**
	 * Assigns the booking a booking id then adds the booking to the booking list
	 * and saves the booking
	 * 
	 * @param bookingDate     the date that the booking is being made for
	 * @param clientBooking   the information about the client who is making the
	 *                        booking
	 * @param bookingDuration the duration of the booking
	 * @param bookingTime     the time of the booking
	 * @param room            the informaiton about the room being booked
	 * @return return the new booking
	 */
	public Booking addBooking(LocalDate bookingDate, Client clientBooking, int bookingDuration, LocalTime bookingTime,
			Room room) {
		bookingIDCounter++;

		try {

			FileOutputStream fileOut = new FileOutputStream("C:\\roombookingsaves/BookingIDCounter.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeInt(bookingIDCounter);
			out.close();
			fileOut.close();

		} catch (IOException i) {
			i.printStackTrace();
			System.out.println("No file could be created");
		}

		if (bookingIDCounter > MAX_BOOKING_ID) {
			System.out.println("Maximum number of bookings reached");
		}

		Booking b = new Booking(bookingIDCounter, clientBooking, bookingDate, bookingDuration, bookingTime, room);
		bookingList.add(b);
		return b;
	}

	/**
	 * Adds a client with a phone number assigns them an id number then saves them
	 * into the system
	 * 
	 * @param givenName    the given name of the client
	 * @param familyName   the family name of the client
	 * @param emailAddress the email address of the client
	 * @param phoneNumber  the phone number of the client
	 * @return the new client that has been added
	 */
	public Client addClient(String givenName, String familyName, String emailAddress, String phoneNumber) {
		clientIDCounter++;

		try {

			FileOutputStream fileOut = new FileOutputStream("C:\\roombookingsaves/ClientIDCounter.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeInt(clientIDCounter);
			out.close();
			fileOut.close();

		} catch (IOException i) {
			i.printStackTrace();
			System.out.println("No file could be created");
		}

		if (clientIDCounter > MAX_CLIENT_ID) {
			System.out.println("Maximum number of clients reached");
		}

		Client c = new Client(clientIDCounter, givenName, familyName, emailAddress, phoneNumber);
		clientList.add(c);
		return c;
	}

	/**
	 * Adds a new client that has not given a phone number assigns them an id number
	 * then saves them into the system
	 * 
	 * @param givenName    the given name of the client
	 * @param familyName   the family name of the client
	 * @param emailAddress the email address of the client
	 * @return the new client that has been added
	 */
	public Client addClientNoPhone(String givenName, String familyName, String emailAddress) {
		clientIDCounter++;

		try {

			FileOutputStream fileOut = new FileOutputStream("C:\\roombookingsaves/ClientIDCounter.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeInt(clientIDCounter);
			out.close();
			fileOut.close();

		} catch (IOException i) {
			i.printStackTrace();
			System.out.println("No file could be created");
		}

		if (clientIDCounter > MAX_CLIENT_ID) {
			System.out.println("Maximum number of clients reached");
		}

		Client c = new Client(clientIDCounter, givenName, familyName, emailAddress);
		clientList.add(c);
		return c;
	}

	/**
	 * List all of the clients that are in the system
	 */
	public void listClients() {
		for (Client c : clientList) {
			System.out.println(c);
		}
	}

	/**
	 * List all of the bookings that are in the system
	 */
	public void listBookings() {
		for (Booking b : bookingList) {
			System.out.println(b);
		}
	}

	/**
	 * Deletes the client of the id that has been entered
	 * 
	 * @param idNumber the id of the client to be removed from the system
	 * @return the client being removed
	 */
	public Client deleteClient(int idNumber) {
		Client target = null;

		for (Client c : clientList) {
			int id = 0;

			id = ((Client) c).getClientID();

			if (id == idNumber) {
				target = c;
				break;
			}

		}
		if (target != null) {
			clientList.remove(target);
		}

		return target;

	}

	/**
	 * gets the id of the client being added to the booking then uses that
	 * information in the booking
	 * 
	 * @param idNumber the id number of the client who is being added to the booking
	 * @return the id of the client
	 */
	public Client retriveClient(int idNumber) {
		Client target = null;

		for (Client c : clientList) {
			int id = 0;

			id = ((Client) c).getClientID();

			if (id == idNumber) {
				target = c;
				break;
			}

		}
		return target;
	}

	/**
	 * Save all the new bookings that have been made
	 */
	public void saveBookings() {
		try {

			FileOutputStream fileOut = new FileOutputStream("C:\\roombookingsaves/bookingList.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(bookingList);
			out.close();
			fileOut.close();

		} catch (IOException i) {
			i.printStackTrace();
			System.out.println("No file could be created");
		}

	}

	/**
	 * Saves all the new clients that have been added in the current session
	 */
	public void saveClients() {

		try {

			FileOutputStream fileOut = new FileOutputStream("C:\\roombookingsaves/customerList.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(clientList);
			out.close();
			fileOut.close();

		} catch (IOException i) {
			i.printStackTrace();
			System.out.println("No file could be created");
		}

	}

	/**
	 * Retrieves the bookings that are stored in the system
	 */
	@SuppressWarnings("unchecked")
	public void retriveBookings() {
		// try catch exception
		try {
			// reads in the values stored in the model.ser file and stores them into the
			// model object
			FileInputStream fileIn = new FileInputStream("C:\\roombookingsaves/bookingList.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			bookingList = (HashSet<Booking>) in.readObject();
			in.close();

		} catch (IOException i) {
			i.printStackTrace();
			return;
		} catch (ClassNotFoundException c) {
			System.out.println("Model class not found");
			c.printStackTrace();
			return;
		}
	}

	/**
	 * Retrieves the clients that are stored in the system
	 */

	@SuppressWarnings("unchecked")
	public void retriveClients() {

		// try catch exception
		try {
			// reads in the values stored in the model.ser file and stores them into the
			// model object
			FileInputStream fileIn = new FileInputStream("C:\\roombookingsaves/customerList.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			clientList = (ArrayList<Client>) in.readObject();
			in.close();

		} catch (IOException i) {
			i.printStackTrace();
			return;
		} catch (ClassNotFoundException c) {
			System.out.println("Model class not found");
			c.printStackTrace();
			return;
		}
	}

	/**
	 * retrieves the id counter from the file system
	 */
	public void retriveClientIDCounter() {

		// try catch exception
		try {
			// reads in the values stored in the model.ser file and stores them into the
			// model object
			FileInputStream fileIn = new FileInputStream("C:\\roombookingsaves/clientIDCounter.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			clientIDCounter = (int) in.readInt();
			in.close();

		} catch (IOException i) {
			i.printStackTrace();
		}
	}

	/**
	 * Retrieves the booking id counter from the file system
	 */
	public void retriveBookingIDCounter() {
		// try catch exception
		try {
			// reads in the values stored in the bookingIDCounter.ser file and stores them
			// into the model object
			FileInputStream fileIn = new FileInputStream("C:\\roombookingsaves/BookingIDCounter.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			bookingIDCounter = (int) in.readInt();
			in.close();

		} catch (IOException i) {
			i.printStackTrace();
		}
	}

	/**
	 * Checks if a room already has a booking at this current date and time
	 * 
	 * @param bookingDate     the date of the booking
	 * @param bookingDuration the duration of the booking
	 * @param bookingTime     the time of the booking
	 * @param room            the room that has been booked
	 * @return if this room is available or not
	 */

	public boolean roomAvaliable(LocalDate bookingDate, Integer bookingDuration, LocalTime bookingTime, Room room) {
		for (Booking booked : bookingList) {

			// TODO booked.equals(r.getRoomNumber())
			if (bookingDate.equals(booked.getBookingDate()) && bookingDuration.equals(booked.getBookingDuration())
					&& bookingTime.equals(booked.getBookingTime())
					&& room.getRoomNumber() == booked.getRoom().getRoomNumber()) {

				// System.out.println("Room is currently booked at this time");
				return false;
			}

		}
		return true;

	}

	/**
	 * Allows the user to cancel a booking based on the id that has been entered
	 * 
	 * @param idNumber the id of the booking that the user wants to cancel
	 * @return the booking that is being cancelled
	 */
	public Booking cancelBooking(int idNumber) {
		Booking target = null;

		for (Booking b : bookingList) {
			int id = 0;

			id = ((Booking) b).getBookingID();

			if (id == idNumber) {
				target = b;
				break;
			}

		}
		if (target != null) {
			bookingList.remove(target);
		}

		return target;
	}

	/**
	 * Allows the user to look at all the bookings of a client
	 * 
	 * @param idNumber the id of the client whos bookings are being viewed
	 */
	public void listCustomerBooking(int idNumber) {
		// TODO
		ArrayList<Booking> viewedBookings = new ArrayList<>();
		// for(Client client : clientList)
		// {

		for (Booking book : bookingList) {
			if (book.getClientBooking().getClientID() == idNumber) {
				viewedBookings.add(book);

			}

		}
		System.out.println(viewedBookings);

	}

	/*
	 * Booking target = null;
	 * 
	 * for(Booking book : bookingList) { int id = 0;
	 * 
	 * id = ((Booking )book).getBookingID();
	 * 
	 * 
	 * if(id == idNumber) { target = book; break; }
	 * 
	 * if(target != null) { System.out.println(book); } }
	 * 
	 * }
	 */

	/**
	 * Checks through the system to find the room that matches the id that the user
	 * entered
	 * 
	 * @param idNumber the id of the room that the user wants to view
	 */
	public void listSpecificBooking(int idNumber) {
		for (Booking book : bookingList) {
			if (book.getBookingID() == idNumber) {
				System.out.println(book);
			}

		}
	}

	/**
	 * Checks through the system to find the client that matches the id that the
	 * user entered
	 * 
	 * @param idNumber the id of the client that the user wants to view
	 */
	public void listSpecificClient(int idNumber) {
		for (Client client : clientList) {
			if (client.getClientID() == idNumber) {
				System.out.println(client);
			}

		}
	}

}
