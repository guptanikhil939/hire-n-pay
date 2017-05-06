package com.hirenpay.service;

import javax.servlet.http.HttpServletRequest;

import com.hirenpay.dto.OrderDetailsDTO;
import com.hirenpay.exception.PersistenceException;
import com.hirenpay.exception.ServiceException;


public interface OrderService
{	
	public OrderDetailsDTO saveOrder(HttpServletRequest request) throws ServiceException, PersistenceException;

	public OrderDetailsDTO saveOrderWeb(HttpServletRequest request) throws ServiceException, PersistenceException;

	public String sendRequestConfirmationOTP(HttpServletRequest request)  throws ServiceException, PersistenceException;
	
	public String confirmRequestConfirmationOTP(HttpServletRequest request)  throws ServiceException, PersistenceException;
}