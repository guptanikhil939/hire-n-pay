package com.hirenpay.controller.mobile;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hirenpay.exception.PersistenceException;
import com.hirenpay.exception.ServiceException;
import com.hirenpay.service.RegistrationService;
import com.hirenpay.util.CommonUtil;
import com.hirenpay.util.Constants;

@Controller
@RequestMapping(value = "/mobile/*")
public class RegistrationControllerMobile
{
	@Autowired
	RegistrationService registrationService;
	
	@Autowired
	CommonUtil commonUtil;

	private static final Logger log = Logger
			.getLogger(RegistrationControllerMobile.class);

	@RequestMapping(value = "/register", method = { RequestMethod.POST }, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String register(HttpServletRequest request)
	{
		log.debug("In Method register(request)");

		String result = Constants.FAILURE;

		try
		{
			result = registrationService.registerUserFromMobile(request);
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
		finally
		{
			try
			{
				result = commonUtil.convertToJSONMessage(result);
			}
			catch (ServiceException exception)
			{
				log.error("Service Exception : " + exception);
			}
		}

		return result;
	}

	@RequestMapping(value = "/confirmOTP", method = { RequestMethod.POST }, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String confirmOTP(HttpServletRequest request)
	{
		log.debug("In Method confirmOTP(request)");

		String result = Constants.FAILURE;

		try
		{
			result = registrationService.confirmOTPFromMobile(request);
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
		finally
		{
			try
			{
				result = commonUtil.convertToJSONMessage(result);
			}
			catch (ServiceException exception)
			{
				log.error("Service Exception : " + exception);
			}
		}

		return result;
	}
}