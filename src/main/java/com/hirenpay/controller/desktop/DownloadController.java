package com.hirenpay.controller.desktop;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hirenpay.exception.ServiceException;
import com.hirenpay.service.DownloadService;
import com.hirenpay.util.ConfigurationReader;

@Controller
@RequestMapping("/download/*")
public class DownloadController
{
	@Autowired
	DownloadService downloadService;

	private static final Logger log = Logger
			.getLogger(DownloadController.class);

	@RequestMapping(value = "/RateCard", method = RequestMethod.GET)
	public void doDownload(HttpServletRequest request,
			HttpServletResponse response) throws IOException
	{
		try
		{
			downloadService.downloadFile(
					request,
					response,
					ConfigurationReader.configurationBean
							.getDownloadFileLocation() + "RateCard.pdf");
		}
		catch (ServiceException exception)
		{
			log.error("Service Exception : " + exception);
		}
		catch (Exception exception)
		{
			log.error("Controller Exception : " + exception);
		}
	}
}