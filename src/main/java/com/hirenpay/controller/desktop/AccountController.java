package com.hirenpay.controller.desktop;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hirenpay.dto.OrderDetailsDTO;
import com.hirenpay.dto.UserDTO;
import com.hirenpay.exception.PersistenceException;
import com.hirenpay.exception.ServiceException;
import com.hirenpay.service.AccountService;
import com.hirenpay.util.Constants;

@Controller
public class AccountController
{
	@Autowired
	AccountService accountService;
	
	private static final Logger log = Logger.getLogger(HomeController.class);

	@RequestMapping(value = "/myRequests", method = RequestMethod.GET)
	public ModelAndView myRequests(HttpServletRequest request)
	{
		log.debug("AccountController :: myRequests");

		ModelAndView model = new ModelAndView("myRequests");

		try
		{
			List<OrderDetailsDTO> orderDetailsDTOList = new ArrayList<OrderDetailsDTO>();
			orderDetailsDTOList = accountService.getOrderList(request);
			model.addObject("orderDetailsDTOList", orderDetailsDTOList);
		}
		catch (PersistenceException exception)
		{
			log.error("Persistence Exception : " + exception);
		}
		catch (ServiceException exception)
		{
			log.error("Service Exception : " + exception);
		}
		catch (Exception exception)
		{
			log.error("Controller Exception : " + exception);
		}

		return model;
	}
	
	@RequestMapping(value = "/myProfile", method = RequestMethod.GET)
	public ModelAndView myProfile(HttpServletRequest request)
	{
		log.debug("AccountController :: myProfile");

		ModelAndView model = new ModelAndView("myProfile");

		try
		{
			UserDTO userDTO= new UserDTO();
			userDTO = accountService.getProfile(request);
			model.addObject("userDTO", userDTO);
		}
		catch (PersistenceException exception)
		{
			log.error("Persistence Exception : " + exception);
		}
		catch (ServiceException exception)
		{
			log.error("Service Exception : " + exception);
		}
		catch (Exception exception)
		{
			log.error("Controller Exception : " + exception);
		}

		return model;
	}
	
	@RequestMapping(value = "/updateProfile", method = RequestMethod.POST)
	@ResponseBody
	public String updateProfile(HttpServletRequest request)
	{
		log.debug("AccountController :: updateProfile");

		String result = Constants.FAILURE;
		
		try
		{			
			result = accountService.updateProfile(request);
		}
		catch (PersistenceException exception)
		{
			log.error("Persistence Exception : " + exception);
		}
		catch (ServiceException exception)
		{
			log.error("Service Exception : " + exception);
		}
		catch (Exception exception)
		{
			log.error("Controller Exception : " + exception);
		}

		return result;
	}
}