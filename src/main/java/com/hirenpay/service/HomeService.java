package com.hirenpay.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.hirenpay.dto.ServiceDTO;
import com.hirenpay.exception.PersistenceException;
import com.hirenpay.exception.ServiceException;

public interface HomeService
{
	public List<ServiceDTO> getServiceList() throws ServiceException, PersistenceException;

	public String subscribe(HttpServletRequest request) throws ServiceException, PersistenceException;

	public String getApp(HttpServletRequest request) throws ServiceException;
}
