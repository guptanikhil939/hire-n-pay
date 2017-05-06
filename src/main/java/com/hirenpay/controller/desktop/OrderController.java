package com.hirenpay.controller.desktop;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hirenpay.dto.OrderDetailsDTO;
import com.hirenpay.exception.ServiceException;
import com.hirenpay.service.OrderService;
import com.hirenpay.util.CommonUtil;
import com.hirenpay.util.Constants;

@Controller
@RequestMapping(value = "/orderWeb/*")
public class OrderController
{
	@Autowired
	OrderService orderService;

	private static final Logger log = Logger.getLogger(OrderController.class);

	@RequestMapping(value = "/sendRequestConfirmationOTP", method = { RequestMethod.POST })
	@ResponseBody
	public String sendRequestConfirmationOTP(HttpServletRequest request)
	{

		log.debug("OrderController :: sendRequestConfirmationOTP");

		String result = Constants.FAILURE;

		try
		{
			result = orderService.sendRequestConfirmationOTP(request);

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
	
	@RequestMapping(value = "/confirmRequestConfirmationOTP", method = { RequestMethod.POST })
	@ResponseBody
	public String confirmRequestConfirmationOTP(HttpServletRequest request)
	{

		log.debug("OrderController :: confirmRequestConfirmationOTP");

		String result = Constants.FAILURE;

		try
		{
			result = orderService.confirmRequestConfirmationOTP(request);

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

	@RequestMapping(value = "/confirmRequest", method = { RequestMethod.POST })
	@ResponseBody
	public String confirmRequest(HttpServletRequest request)
	{

		log.debug("OrderController :: confirmRequest");

		String result = Constants.FAILURE;
		OrderDetailsDTO orderDetailsDTO = null;

		try
		{
			orderDetailsDTO = orderService.saveOrderWeb(request);

			if (null != orderDetailsDTO)
			{
				result = Constants.SUCCESS;
			}
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