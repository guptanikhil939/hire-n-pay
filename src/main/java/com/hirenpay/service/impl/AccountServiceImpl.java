package com.hirenpay.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hirenpay.dao.AccountDAO;
import com.hirenpay.dao.OrderDetailsDAO;
import com.hirenpay.dto.OrderDetailsDTO;
import com.hirenpay.dto.UserDTO;
import com.hirenpay.exception.PersistenceException;
import com.hirenpay.exception.ServiceException;
import com.hirenpay.model.OrderDetails;
import com.hirenpay.model.User;
import com.hirenpay.service.AccountService;
import com.hirenpay.util.CommonUtil;
import com.hirenpay.util.Constants;

@Transactional
@Service("accountService")
public class AccountServiceImpl implements AccountService
{
	@Autowired
	AccountDAO accountDAO;

	@Autowired
	OrderDetailsDAO orderDetailsDAO;

	public List<OrderDetailsDTO> getOrderList(HttpServletRequest request)
			throws ServiceException, PersistenceException
	{
		List<OrderDetailsDTO> orderDetailsDTOList = new ArrayList<OrderDetailsDTO>();

		try
		{
			HttpSession session = request.getSession(false);
			UserDTO userDTO = (UserDTO) session.getAttribute("userDTO");

			if (null != userDTO && null != userDTO.getPhoneNumber())
			{
				List<OrderDetails> orderDetails = accountDAO
						.getOrderDetailsList(userDTO.getPhoneNumber());

				for (OrderDetails order : orderDetails)
				{
					OrderDetailsDTO orderDetailsDTO = new OrderDetailsDTO();
					orderDetailsDTO.setOrderId(order.getOrderId());
					orderDetailsDTO.setServiceName(orderDetailsDAO
							.getServiceName(order.getServiceId()));
					orderDetailsDTO.setPickUpDateString(CommonUtil
							.convertDateToString(order.getPickUpDate()));
					orderDetailsDTOList.add(orderDetailsDTO);
				}
			}
		}
		catch (NumberFormatException exception)
		{
			throw new ServiceException(exception.getMessage(), exception);
		}
		catch (PersistenceException exception)
		{
			throw new PersistenceException(exception.getMessage(), exception);
		}
		catch (Exception exception)
		{
			throw new ServiceException(exception.getMessage(), exception);
		}
		return orderDetailsDTOList;
	}

	public UserDTO getProfile(HttpServletRequest request)
			throws ServiceException, PersistenceException
	{
		UserDTO userDTO = new UserDTO();
		User user = new User();

		try
		{
			HttpSession session = request.getSession(false);
			userDTO = (UserDTO) session.getAttribute("userDTO");

			user = accountDAO.getProfile(userDTO.getPhoneNumber());

			userDTO.setFirstName(user.getFirstName());
			userDTO.setLastName(user.getLastName());
			userDTO.setEmailId(user.getEmailId());

			return userDTO;
		}
		catch (NumberFormatException exception)
		{
			throw new ServiceException(exception.getMessage(), exception);
		}
		catch (PersistenceException exception)
		{
			throw new PersistenceException(exception.getMessage(), exception);
		}
		catch (Exception exception)
		{
			throw new ServiceException(exception.getMessage(), exception);
		}
	}

	public String updateProfile(HttpServletRequest request)
			throws ServiceException, PersistenceException
	{
		String result = Constants.FAILURE;
		UserDTO userDTO = new UserDTO();
		User user = new User();

		try
		{
			String firstName = request.getParameter("firstName") == null ? ""
					: request.getParameter("firstName");
			String lastName = request.getParameter("lastName") == null ? ""
					: request.getParameter("lastName");
			String email = request.getParameter("email") == null ? "" : request
					.getParameter("email");

			HttpSession session = request.getSession(false);
			userDTO = (UserDTO) session.getAttribute("userDTO");

			user = accountDAO.getProfile(userDTO.getPhoneNumber());

			if (null != user)
			{
				user.setFirstName(firstName);
				user.setLastName(lastName);
				user.setEmailId(email);

				boolean profileUpdated = accountDAO.updateProfile(user);

				if (profileUpdated)
				{
					result = Constants.SUCCESS;
				}
				else
				{
					result = Constants.FAILURE;
				}
			}
		}
		catch (NumberFormatException exception)
		{
			throw new ServiceException(exception.getMessage(), exception);
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
}
