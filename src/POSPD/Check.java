package POSPD;

import java.math.BigDecimal;
import java.util.Random;

/**
 * Representation of a Check to be used during a Sale
 */
public class Check extends AuthorizedPayment 
{

	/**
	 * Bank RoutingNumber of the check, specifies what bank the money is coming from
	 */
	private String routingNumber;
	/**
	 * Bank AccountNumber of the check, specifies who's account the money will come from
	 */
	private String accountNumber;
	/**
	 * A number specific to one check
	 */
	private String checkNumber;

	public String getRoutingNumber() 
	{
		return this.routingNumber;
	}

	public void setRoutingNumber(String routingNumber) 
	{
		this.routingNumber = routingNumber;
	}

	public String getAccountNumber() 
	{
		return this.accountNumber;
	}

	public void setAccountNumber(String accountNumber) 
	{
		this.accountNumber = accountNumber;
	}

	public String getCheckNumber() 
	{
		return this.checkNumber;
	}

	public void setCheckNumber(String checkNumber) 
	{
		this.checkNumber = checkNumber;
	}

	/**
	 * Default constructor of a Check
	 */
	public Check() 
	{
		this.setAmount(new BigDecimal(0));
		this.setAmtTendered(new BigDecimal(0));
		routingNumber = "";
		accountNumber = "";
		checkNumber = "";
	}

	/**
	 * Constructor for a check that initializes it's amount to amount, accountNumber to accountNumber, and checkNumber to checkNumber
	 * @param amount Amount to be paid during a Sale
	 * @param accountNumber Account identifier for a Check's bank
	 * @param checkNumber Check's identifier number
	 */
	public Check(String amount, String amountTendered, String accountNumber, String checkNumber) 
	{
		this();
		this.setAmount(new BigDecimal(amount));
		this.setAmtTendered(new BigDecimal(amountTendered));
		this.accountNumber = accountNumber;
		this.checkNumber = checkNumber;
	}

	/**
	 * Determines whether or not a Check is authorized to pay a Sale
	 * @return True, is authorized. False, is NOT authorized
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
	 * Determines whether a Check payment counts as Cash, used in terms of making change
	 * @return True, counts as cash. False, does not count as cash
	 */
	public Boolean countsAsCash() 
	{
		return true;
	}

	
	/**
	 * Makes the string representation of a Check
	 * @return String representation of a Check
	 */
	public String toString() 
	{
		return new String("\n Amount: " + this.getAmount() +  "\nRouting #: " + routingNumber + "\nAccount #: " + accountNumber + "\nCheck #: " + checkNumber);
	}

}