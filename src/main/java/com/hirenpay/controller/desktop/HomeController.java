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

import com.hirenpay.dto.ServiceDTO;
import com.hirenpay.exception.PersistenceException;
import com.hirenpay.exception.ServiceException;
import com.hirenpay.service.HomeService;
import com.hirenpay.util.Constants;

@Controller
public class HomeController
{
	@Autowired
	HomeService homeService;
	
	private static final Logger log = Logger.getLogger(HomeController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView getdata()
	{
		log.debug("HomerController :: getdata");

		ModelAndView model = null;

		try
		{
			List<ServiceDTO> serviceList = new ArrayList<ServiceDTO>();
			serviceList = homeService.getServiceList();
			model = new ModelAndView("home");
			model.addObject("serviceList", serviceList);
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
	
	@RequestMapping(value = "/subscribe", method = RequestMethod.POST)
	@ResponseBody
	public String subscribe(HttpServletRequest request)
	{
		log.debug("HomerController :: subscribe");
		
		String result = Constants.FAILURE;

		try
		{
			result = homeService.getApp(request);
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