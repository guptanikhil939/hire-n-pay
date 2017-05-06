package com.hirenpay.service;

import javax.servlet.http.HttpServletRequest;

import com.hirenpay.dto.UserDTO;
import com.hirenpay.exception.PersistenceException;
import com.hirenpay.exception.ServiceException;

public interface LoginService
{
	public String loginAuth(HttpServletRequest request) throws ServiceException, PersistenceException;

	public String forgotPassword(HttpServletRequest request) throws ServiceException, PersistenceException;
}
