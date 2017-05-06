package com.hirenpay.exception;

public class PersistenceException extends Exception
{
	private static final long serialVersionUID = 1L;

	public PersistenceException(String message)
	{
		super(message);
	}
	
	public PersistenceException(String message, Exception exception)
	{
		super(message,exception);
	}
}