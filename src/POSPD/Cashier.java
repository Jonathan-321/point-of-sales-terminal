package POSPD;

import java.util.*;

/**
 * Representation of a cashier
 */
public class Cashier 
{

	/**
	 * Employee number of the cashier
	 */
	private String number;
	
	/**
	 * Person that knows a cashier (probably actually the cashier)
	 */
	private Person person;
	
	/**
	 * Work session the Cashier has worked/is working
	 */
	private ArrayList<Session> sessions;
	
	/**
	 * Password for the Cashier's log-in
	 */
	private String password;

	public String getNumber() 
	{
		return this.number;
	}

	public void setNumber(String number) 
	{
		this.number = number;
	}

	public Person getPerson() 
	{
		return this.person;
	}

	public void setPerson(Person person) 
	{
		this.person = person;
	}

	public String getPassword() 
	{
		return this.password;
	}

	public void setPassword(String password) 
	{
		this.password = password;
	}

	/**
	 * Cashier default constructor
	 */
	public Cashier() 
	{
		sessions = new ArrayList<Session>();
		person = new Person();
	}
	
	public Cashier(String number, String name, String sSN, String address, String city, String state, String zip, String phone, String password)
	{
		this();
		this.number = number;
		this.person = new Person(name, sSN, address, city, state, zip, phone, this);
		this.password = password;
	}
	
	/**
	 * Cashier constructor that initializes number to number, person to person, and password to password.
	 * @param number Cashier employee number
	 * @param person Person the Cashier knows
	 * @param password Password for Cashier log-in
	 */
	public Cashier(String number, Person person, String password) 
	{
		this();
		this.number = number;
		this.person = person;
		this.password = password;
	}

	/**
	 * Adds a new work session to a cashier
	 * @param session Session to add to the worker
	 */
	public void addSession(Session session) 
	{
		sessions.add(session);
	}

	/**
	 * Removes a work session from the cashier
	 * @param session Session to remove from the cashier
	 */
	public void removeSession(Session session) 
	{
		sessions.remove(session);
	}

	/**
	 * Determines whether or not a Cashier is authorized
	 * @param password Password of the cashier, used to determine authorization
	 * @return true, cashier is authorizes, false, cashier is not authorized
	 */
	public Boolean isAuthorized(String password) 
	{
		return this.password.equals(password);
	}

	/**
	 * Makes a string representation of a Cashier
	 * @return String representation of a Cashier
	 */
	public String toString() 
	{	
		return person.getName();
	}

	public Boolean isUsed()
	{
		Boolean result = false;
		if(!sessions.isEmpty())
		{
			result = true;
		}
		return result;
	}
	public boolean IsOkToDelete()
	{
		if (getSessions().isEmpty())
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public ArrayList<Session> getSessions() {
		return sessions;
	}

	public void setSessions(ArrayList<Session> sessions) {
		this.sessions = sessions;
	}
	
}