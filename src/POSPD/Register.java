package POSPD;

import java.util.ArrayList;

/**
 * A representation of a cash register owned by the company
 */
public class Register 
{

	/**
	 * Identification Number of a Register
	 */
	private String number;
	/**
	 * The drawer of Cash a Register holds during a Session
	 */
	private CashDrawer cashDrawer;

	private ArrayList<Session> sessions;
	
	public String getNumber() 
	{
		return this.number;
	}

	public void setNumber(String number) 
	{
		this.number = number;
	}

	public CashDrawer getCashDrawer() 
	{
		return this.cashDrawer;
	}

	public void setCashDrawer(CashDrawer cashDrawer) 
	{
		this.cashDrawer = cashDrawer;
	}

	/**
	 * Default constructor of a Register
	 */
	public Register() 
	{
		number = "";
		cashDrawer = new CashDrawer();
		sessions = new ArrayList<Session>();
	}
	
	public Register(String number)
	{
		this();
		this.number = number;
	}
	
	/**
	 * A constructor for a Register that takes a Number
	 * @param number Identification Number for a Cash Register
	 */
	public Register(String number, CashDrawer cashDrawer) 
	{
		this();
		this.number = number;
		this.cashDrawer = cashDrawer;
	}

	/**
	 * Makes a String representation of a Register
	 * @return String representation of a Register
	 */
	public String toString() 
	{
		return new String(number);
	}

	public ArrayList<Session> getSessions()
	{
		return sessions;
	}

	public void setSessions(ArrayList<Session> sessions)
	{
		this.sessions = sessions;
	}
	
	public void addSession(Session session)
	{
		sessions.add(session);
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
}