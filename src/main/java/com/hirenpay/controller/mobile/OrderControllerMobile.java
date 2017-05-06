package com.hirenpay.controller.mobile;

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
@RequestMapping(value = "/orderMobile/*")
public class OrderControllerMobile
{
	@Autowired
	OrderService orderService;
	
	@Autowired
	CommonUtil commonUtil;
	
	private static final Logger log = Logger.getLogger(OrderControllerMobile.class);

	@RequestMapping(value = "/confirmRequest", method = { RequestMethod.POST }, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String confirmRequest(HttpServletRequest request)
	{
		log.debug("OrderController :: confirmRequest");
		
		String result = Constants.FAILURE;
		OrderDetailsDTO orderDetailsDTO= null;

		try
		{
			result = commonUtil.convertToJSONMessage(result);
			
			orderDetailsDTO = orderService.saveOrder(request);
			
			if(null!=orderDetailsDTO)
			{
				result = commonUtil.convertOrderDetailsToJSONMessage(orderDetailsDTO);
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