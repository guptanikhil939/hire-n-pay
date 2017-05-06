package com.hirenpay.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionFilter implements Filter
{

	public void destroy()
	{

	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException
	{
		HttpServletResponse response = (HttpServletResponse) res;
		HttpServletRequest request = (HttpServletRequest) req;
		HttpSession session = request.getSession();

		String requestedPage = getRequestedPage(request);

		if ("".equals(requestedPage) || requestedPage.contains("web") || requestedPage.contains("mobile") || requestedPage.contains("resources") || requestedPage.contains("login") || requestedPage.contains("subscribe") || requestedPage.contains("order"))
		{
			// Accessing login/logout is always permitted
			chain.doFilter(request, response);
			return;
		}

		if (null == session)
		{
			// Forward the control to home page if session expires
			response.sendRedirect(request.getContextPath() + "/");
		}
		else if(null!=session && null == session.getAttribute("userDTO"))
		{
			response.sendRedirect(request.getContextPath() + "/");
		}
		else
		{
			chain.doFilter(req, res);
		}
	}

	public void init(FilterConfig arg0) throws ServletException
	{

	}

	private String getRequestedPage(HttpServletRequest aHttpRequest)
	{
		String url = aHttpRequest.getRequestURI();
		int firstSlash = url.indexOf("/", 0);
		String requestedPage = null;
		if (firstSlash != -1)
			requestedPage = url.substring(firstSlash + 1, url.length());
		return requestedPage;
	}
}