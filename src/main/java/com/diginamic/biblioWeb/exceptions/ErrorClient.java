package com.diginamic.biblioWeb.exceptions;

/**
 * On créer une Exception fonctionnelle pour gérer les erreues sur les clients
 * 
 * @author Boris
 *
 */
public class ErrorClient extends Exception
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7214504423106845565L;

	public ErrorClient()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public ErrorClient(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
	{
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public ErrorClient(String message, Throwable cause)
	{
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public ErrorClient(String message)
	{
		super(message);
		// TODO Auto-generated constructor stub
	}

	public ErrorClient(Throwable cause)
	{
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
