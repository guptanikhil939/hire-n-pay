package com.hirenpay.controller.desktop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LogoutController
{

	@RequestMapping(value = "/logout", method = { RequestMethod.GET,
			RequestMethod.POST })
	public ModelAndView login(HttpServletRequest request)
	{
		HttpSession session = request.getSession(false);
		if(session!=null)
		{
			session.invalidate();
		}
		
		return new ModelAndView("redirect:/");
	}
}