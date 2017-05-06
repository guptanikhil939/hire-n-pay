package com.hirenpay.util;

import java.io.FileInputStream;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.hirenpay.model.ConfigurationBean;
import com.hirenpay.service.impl.RegistrationServiceImpl;

public class ConfigurationReader implements ServletContextListener
{

	public static ConfigurationBean configurationBean;

	private static final Logger log = Logger
			.getLogger(RegistrationServiceImpl.class);

	@Override
	public void contextDestroyed(ServletContextEvent arg0)
	{
	}

	// Run this before web application is started
	@Override
	public void contextInitialized(ServletContextEvent arg0)
	{
		log.debug("Context Initialised");
		
		configurationBean = new ConfigurationBean();

		try
		{
			Properties prop = new Properties();
			prop.load(new FileInputStream(Constants.PROPERTY_FILE_PATH));

			configurationBean.setHome((prop.getProperty("home")));
			configurationBean.setDownloadFileLocation((prop.getProperty("download.file.location")));
			configurationBean.setOfficePhoneNumber((prop.getProperty("officePhoneNumber")));
			configurationBean.setSmsURL((prop.getProperty("sms.url")));
			configurationBean.setSmsUser((prop.getProperty("sms.user")));
			configurationBean
					.setSmsPassword((prop.getProperty("sms.password")));
			configurationBean
			.setSmsOTPMessage1((prop.getProperty("sms.otp.message1")));
			configurationBean
			.setSmsOTPMessage2((prop.getProperty("sms.otp.message2")));
			configurationBean
			.setSmsOrderMessage1((prop.getProperty("sms.order.message1")));
			configurationBean
			.setSmsOrderMessage2((prop.getProperty("sms.order.message2")));
		}
		catch (Exception exception)
		{
			log.debug("Problem occured during Context Initialisation." + exception);
		}
	}
}