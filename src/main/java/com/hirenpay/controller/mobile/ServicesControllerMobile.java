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
import com.hirenpay.service.ServicesService;
import com.hirenpay.util.CommonUtil;

@Controller
@RequestMapping(value = "/mobile/services/*")
public class ServicesControllerMobile
{
	@Autowired
	ServicesService servicesService;
	
	@Autowired
	CommonUtil commonUtil;
	
	private static final Logger log = Logger.getLogger(ServicesControllerMobile.class);

	@RequestMapping(value = "/checkService", method = { RequestMethod.GET }, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String checkService(HttpServletRequest request)
	{
		log.debug("ServicesController :: checkService");
		
		String result = "failure";

		try
		{
			result = servicesService.checkService(request);
			return commonUtil.convertToJSONMessage(result);
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
	
	@RequestMapping(value = "/getAllActiveServices", method = { RequestMethod.POST })
	@ResponseBody
	public String getAllServices(HttpServletRequest request)
	{
		log.debug("ServicesController :: getAllActiveServices");
		
		String result = "failure";

		try
		{
			result = servicesService.getAllActiveServices(request);
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