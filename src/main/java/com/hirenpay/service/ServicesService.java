package com.hirenpay.service;

import javax.servlet.http.HttpServletRequest;

import com.hirenpay.exception.PersistenceException;
import com.hirenpay.exception.ServiceException;

public interface ServicesService
{
	public String checkService(HttpServletRequest request) throws ServiceException, PersistenceException;

	public String getAllActiveServices(HttpServletRequest request) throws ServiceException, PersistenceException;
}
