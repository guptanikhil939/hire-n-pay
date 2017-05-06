package com.hirenpay.service;

import com.hirenpay.exception.ServiceException;

public interface GoogleService
{	
	public String calculateDistance(String pickupLocation, String pickupCity, String dropLocation, String dropCity) throws ServiceException;
}