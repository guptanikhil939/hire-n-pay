package com.hirenpay.service;

import javax.servlet.http.HttpServletRequest;

import com.hirenpay.exception.PersistenceException;
import com.hirenpay.exception.ServiceException;

public interface RegistrationService
{
	public String registerUserFromMobile(HttpServletRequest request) throws ServiceException, PersistenceException;
	
	public String registerUserFromWeb(HttpServletRequest request) throws ServiceException, PersistenceException;

	public String registeredUserForMobile(HttpServletRequest request) throws ServiceException, PersistenceException;
	
	public String registeredUserForWeb(HttpServletRequest request) throws ServiceException, PersistenceException;

	public String confirmOTPFromMobile(HttpServletRequest request) throws ServiceException, PersistenceException;
	
	public String confirmOTPFromWeb(HttpServletRequest request) throws ServiceException, PersistenceException;
}
