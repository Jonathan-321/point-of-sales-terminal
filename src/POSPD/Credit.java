package POSPD;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 * Representation of a Credit card
 */
public class Credit extends AuthorizedPayment 
{

	/**
	 * Type of card (Visa, Master Card, etc)
	 */
	private String cardType;
	/**
	 * Account Number to define what account the card belongs to
	 */
	private String acctNumber;
	/**
	 * When a card will expire
	 */
	private LocalDate expireDate;

	public String getCardType() 
	{
		return this.cardType;
	}

	public void setCardType(String cardType) 
	{
		this.cardType = cardType;
	}

	public String getAcctNumber() 
	{
		return this.acctNumber;
	}

	public void setAcctNumber(String acctNumber) 
	{
		this.acctNumber = acctNumber;
	}

	public LocalDate getExpireDate() 
	{
		return this.expireDate;
	}

	public void setExpireDate(LocalDate expireDate) 
	{
		this.expireDate = expireDate;
	}

	/**
	 * Default constructor for a Credit
	 */
	public Credit() 
	{
		cardType = "";
		acctNumber = "";
		expireDate = LocalDate.parse("1/1/1111", DateTimeFormatter.ofPattern("M/d/yyyy"));
	}

	/**
	 * Constructor for a credit that initializes cardType to cardType, acctNumber to acctNumber, and expireDate to expireDate
	 * @param cardType Type of card (Visa, Master Card, etc)
	 * @param acctNumber Account Number to define what account the card belongs to
	 * @param expireDate When a card will expire
	 */
	public Credit(String cardType, String acctNumber, String expireDate) 
	{
		this();
		this.cardType = cardType;
		this.acctNumber = acctNumber;
		this.expireDate = LocalDate.parse(expireDate, DateTimeFormatter.ofPattern("M/d/yyyy"));
	}

	/**
	 * Determines whether or not a card is authorized to pay for a Sale
	 * @return True, is authorized. False, is not authorized
	 */
	public Boolean isAuthorized() 
	{
		Random rand = new Random();
		Boolean result = true;
		
		if(rand.nextInt(100) + 1 > 85)
		{
			result = false;
		}
		
		return result;
	}

	/**
	 * Determines whether a Credit payment counts as Cash, usually used for cash back
	 * @return True, counts as cash. False, does not count as cash
	 */
	public Boolean countsAsCash() 
	{
		return true;
	}

	
	/**
	 * Makes a String representation of a Credit
	 * @return The String representation of a Credit
	 */
	public String toString() 
	{
		// TODO - implement Credit.toString
		throw new UnsupportedOperationException();
	}

}