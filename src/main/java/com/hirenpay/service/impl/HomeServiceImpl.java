package com.hirenpay.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hirenpay.dao.HomeDAO;
import com.hirenpay.dto.ServiceDTO;
import com.hirenpay.exception.PersistenceException;
import com.hirenpay.exception.ServiceException;
import com.hirenpay.model.Services;
import com.hirenpay.model.Subscriber;
import com.hirenpay.service.HomeService;
import com.hirenpay.util.CommonUtil;
import com.hirenpay.util.Constants;

@Service("homeService")
public class HomeServiceImpl implements HomeService
{
	@Autowired
	HomeDAO homeDAO;
	
	@Autowired
	CommonUtil commonUtil;

	private static final Logger log = Logger.getLogger(HomeServiceImpl.class);

	public List<ServiceDTO> getServiceList() throws ServiceException,
			PersistenceException
	{
		List<ServiceDTO> serviceDTOList = new ArrayList<ServiceDTO>();

		try
		{
			List<Services> serviceList = homeDAO.getServiceList();

			for (Services service : serviceList)
			{
				ServiceDTO serviceDTO = new ServiceDTO();
				serviceDTO.setServiceId(service.getServiceId());
				serviceDTO.setServiceCategoryId(service.getServiceCategoryId());
				serviceDTO.setServiceName(service.getServiceName());
				serviceDTO.setServiceDescription(service
						.getServiceDescription());
				serviceDTOList.add(serviceDTO);
			}
		}
		catch (PersistenceException exception)
		{
			throw new PersistenceException(exception.getMessage(), exception);
		}
		catch (Exception exception)
		{
			throw new ServiceException(exception.getMessage(), exception);
		}

		return serviceDTOList;
	}

	public String subscribe(HttpServletRequest request)
			throws ServiceException, PersistenceException
	{
		log.debug("In Method subscribe()");

		String result = Constants.FAILURE;

		try
		{
			String subscriberPhoneNumber = request
					.getParameter("subscriberPhoneNumber");

			if (null != subscriberPhoneNumber
					&& subscriberPhoneNumber.length() == 10)
			{
				Subscriber subscriber = null;

				subscriber = homeDAO.getSubscriber(subscriberPhoneNumber);

				if (null != subscriber && null != subscriber.getPhoneNumber()
						&& subscriber.getPhoneNumber().length() == 10)
				{
					result = Constants.USER_ALREADY_SUBSCRIBED;
				}
				else
				{
					subscriber = new Subscriber();
					subscriber.setPhoneNumber(subscriberPhoneNumber);

					boolean subscribed = homeDAO.subscribe(subscriber);

					if (subscribed)
					{
						result = Constants.SUCCESS;
					}
					else
					{
						result = Constants.FAILURE;
					}
				}
			}
		}
		catch (PersistenceException exception)
		{
			throw new PersistenceException(exception.getMessage(), exception);
		}
		catch (Exception exception)
		{
			throw new ServiceException(exception.getMessage(), exception);
		}

		return result;
	}

	public String getApp(HttpServletRequest request) throws ServiceException
	{
		log.debug("In Method getApp()");

		String result = Constants.FAILURE;

		try
		{
			String subscriberPhoneNumber = request
					.getParameter("subscriberPhoneNumber");

			if (null != subscriberPhoneNumber
					&& subscriberPhoneNumber.length() == 10)
			{
				List<String> phoneNumbers = new ArrayList<String>();
				phoneNumbers.add(subscriberPhoneNumber);
				commonUtil.callSMSServer(Constants.GET_APP_MESSAGE,
						phoneNumbers);
				return Constants.SUCCESS;
			}
		}
		catch (Exception exception)
		{
			throw new ServiceException(exception.getMessage(), exception);
		}

		return result;
	}
}
