package com.hirenpay.controller.desktop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hirenpay.dao.impl.RegistrationDAOImpl;
import com.hirenpay.dto.UserDTO;
import com.hirenpay.exception.PersistenceException;
import com.hirenpay.exception.ServiceException;
import com.hirenpay.service.LoginService;
import com.hirenpay.util.Constants;

@Controller
@RequestMapping(value = "/login/*")
public class LoginController
{
	@Autowired
	LoginService loginService;

	private static final Logger log = Logger
			.getLogger(RegistrationDAOImpl.class);

	@RequestMapping(value = "/auth", method = { RequestMethod.POST })
	@ResponseBody
	public String loginAuth(HttpServletRequest request)
	{
		String result = Constants.FAILURE;

		try
		{
			result = loginService.loginAuth(request);
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

	@RequestMapping(value = "/form", method = { RequestMethod.GET,
			RequestMethod.POST })
	public ModelAndView login(HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView("loginForm");

		return modelAndView;
	}

	@RequestMapping(value = "/checkUserSession", method = { RequestMethod.POST })
	@ResponseBody
	public String checkUserSession(HttpServletRequest request)
	{
		try
		{
			HttpSession session = request.getSession(false);
			String phoneNumber = request.getParameter("phoneNumber") == null ? ""
					: request.getParameter("phoneNumber");
			UserDTO userDTO = (UserDTO) session.getAttribute("userDTO");

			if (null != userDTO && userDTO.getPhoneNumber().equals(phoneNumber))
			{
				return Constants.SUCCESS;
			}
			else
			{
				return Constants.FAILURE;
			}
		}
		catch (Exception exception)
		{
			log.error("Controller Exception : " + exception);
			return Constants.FAILURE;
		}
	}

	@RequestMapping(value = "/forgotPassword", method = { RequestMethod.POST })
	@ResponseBody
	public String forgotPassword(HttpServletRequest request)
	{
		try
		{
			return loginService.forgotPassword(request);
		}
		catch (Exception exception)
		{
			log.error("Controller Exception : " + exception);
			return Constants.FAILURE;
		}
	}
}