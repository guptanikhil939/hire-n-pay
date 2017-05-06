package com.hirenpay.exception;

public class ServiceException extends Exception
{
	private static final long serialVersionUID = 1L;

	public ServiceException(String message)
	{
		super(message);
	}
	
	public ServiceException(String message, Exception exception)
	{
		super(message,exception);
	}
}
