package com.hirenpay.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.hirenpay.dto.OrderDetailsDTO;
import com.hirenpay.dto.UserDTO;
import com.hirenpay.exception.PersistenceException;
import com.hirenpay.exception.ServiceException;

public interface AccountService
{
	public List<OrderDetailsDTO> getOrderList(HttpServletRequest request)
			throws ServiceException, PersistenceException;

	public UserDTO getProfile(HttpServletRequest request)
			throws ServiceException, PersistenceException;

	public String updateProfile(HttpServletRequest request)
			throws ServiceException, PersistenceException;
}
