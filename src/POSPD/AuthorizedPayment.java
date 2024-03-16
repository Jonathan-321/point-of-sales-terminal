package POSPD;

/**
 * Representation of an AuthorizedPayment, payments that have to be authorized
 */
public abstract class AuthorizedPayment extends Payment 
{

	/**
	 * The code for a Payment to show its authorization
	 */
	private String authorizationCode;

	public String getAuthorizationCode() 
	{
		return this.authorizationCode;
	}

	public void setAuthorizationCode(String authorizationCode) 
	{
		this.authorizationCode = authorizationCode;
	}

	/**
	 * Determines whether or not an AuthorizedPayment is authorized
	 * @return True, payment is authorized. False, payment is not authorized
	 */
	public Boolean isAuthorized() 
	{
		return false;
	}

	/**
	 * Determines whether or not an AuthorizedPayment counts as cash, usually in case of making change
	 * @return True, counts as cash. False, does NOT count as cash
	 */
	public Boolean countsAsCash() 
	{
		return true;
	}

}