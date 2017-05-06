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
import com.hirenpay.service.ServicesService;

@Controller
@RequestMapping(value = "/desktop/services/*")
public class ServicesController
{
	@Autowired
	ServicesService servicesService;
	
	private static final Logger log = Logger.getLogger(ServicesController.class);

	@RequestMapping(value = "/checkService", method = { RequestMethod.POST })
	@ResponseBody
	public String checkService(HttpServletRequest request)
	{
		log.debug("ServicesController :: checkService");
		
		String result = "failure";

		try
		{
			result = servicesService.checkService(request);
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
}