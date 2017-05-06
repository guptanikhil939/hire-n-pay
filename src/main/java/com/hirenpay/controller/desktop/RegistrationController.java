package com.hirenpay.controller.desktop;

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
import com.hirenpay.util.Constants;

@Controller
@RequestMapping(value = "/web/*")
public class RegistrationController
{
	@Autowired
	RegistrationService registrationService;

	private static final Logger log = Logger.getLogger(RegistrationController.class);

	@RequestMapping(value = "/register", method = { RequestMethod.POST })
	@ResponseBody
	public String register(HttpServletRequest request)
	{
		log.debug("RegistrationController :: register");
		
		String result = "failure";

		try
		{
			result = registrationService.registerUserFromWeb(request);
			return result;
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
	
	@RequestMapping(value = "/confirmOTP", method = { RequestMethod.POST })
	@ResponseBody
	public String confirmOTP(HttpServletRequest request)
	{
		log.debug("In Method confirmOTP(request)");

		String result = Constants.FAILURE;

		try
		{
			result = registrationService.confirmOTPFromWeb(request);
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