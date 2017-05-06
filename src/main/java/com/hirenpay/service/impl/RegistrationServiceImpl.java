package com.hirenpay.service.impl;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hirenpay.dao.CommonDAO;
import com.hirenpay.dao.RegistrationDAO;
import com.hirenpay.dto.LoginDTO;
import com.hirenpay.dto.mobile.MobileDataDTO;
import com.hirenpay.exception.PersistenceException;
import com.hirenpay.exception.ServiceException;
import com.hirenpay.model.User;
import com.hirenpay.service.RegistrationService;
import com.hirenpay.util.CommonUtil;
import com.hirenpay.util.Constants;

@Service("registrationService")
public class RegistrationServiceImpl implements RegistrationService
{

	@Autowired
	CommonUtil commonUtil;

	@Autowired
	RegistrationDAO registrationDAO;

	@Autowired
	CommonDAO commonDAO;

	MobileDataDTO mobileDataDTO = null;

	LoginDTO loginDTO = null;

	private static final Logger log = Logger
			.getLogger(RegistrationServiceImpl.class);

	public String registeredUserForMobile(HttpServletRequest request)
			throws ServiceException, PersistenceException
	{
		log.debug("In Method registeredUser(request)");

		String result = Constants.FAILURE;

		try
		{
			JSONObject jsonObj = commonUtil.readJSON(request);
			String phoneNumber = (String) jsonObj.get("phoneNumber");
			mobileDataDTO = new MobileDataDTO();
			mobileDataDTO.setPhoneNumber(phoneNumber);

			if (commonUtil.validPhoneNumber(phoneNumber))
			{
				boolean registeredUser = false;

				registeredUser = registrationDAO
						.registeredUserForMobile(phoneNumber);

				if (registeredUser)
				{
					result = Constants.USER_ALREADY_REGISTERED;
				}
				else
				{
					result = Constants.USER_NOT_REGISTERED;
				}
			}
			else
			{
				result = Constants.INVALID_PHONE_NUMBER;
			}
		}
		catch (NumberFormatException exception)
		{
			throw new ServiceException(exception.getMessage(), exception);
		}
		catch (PersistenceException exception)
		{
			throw new PersistenceException(exception.getMessage(), exception);
		}
		catch (Exception exception)
		{
			throw new ServiceException(exception.getMessage(), exception);
		}

		return result;
	}

	public String registerUserFromMobile(HttpServletRequest request)
			throws ServiceException, PersistenceException
	{
		log.debug("In Method registerUserFromMobile()");

		String result = Constants.FAILURE;

		try
		{
			result = registeredUserForMobile(request);

			if (null != result
					&& result.contains(Constants.USER_NOT_REGISTERED))
			{
				result = commonUtil.sendOTP(mobileDataDTO.getPhoneNumber());
			}
		}
		catch (PersistenceException exception)
		{
			throw new PersistenceException(exception.getMessage(), exception);
		}
		catch (Exception exception)
		{
			throw new ServiceException(exception.getMessage(), exception);
		}

		return result;
	}

	public String registerUserFromWeb(HttpServletRequest request)
			throws ServiceException, PersistenceException
	{

		log.debug("In Method registerUserFromWeb()");

		String result = Constants.FAILURE;

		try
		{
			result = registeredUserForWeb(request);

			if (null != result
					&& result.contains(Constants.USER_NOT_REGISTERED))
			{
				result = commonUtil.sendOTP(loginDTO.getPhoneNumber());
			}
		}
		catch (PersistenceException exception)
		{
			throw new PersistenceException(exception.getMessage(), exception);
		}
		catch (Exception exception)
		{
			throw new ServiceException(exception.getMessage(), exception);
		}

		return result;
	}

	public String confirmOTPFromMobile(HttpServletRequest request)
			throws ServiceException, PersistenceException
	{
		log.debug("In Method confirmOTPFromMobile(request)");

		String result = Constants.FAILURE;

		try
		{
			JSONObject jsonObj = commonUtil.readJSON(request);
			String phoneNumber = (String) jsonObj.get("phoneNumber");
			String otp = (String) jsonObj.get("otp");

			if (commonUtil.validPhoneNumber(phoneNumber))
			{
				boolean otpConfirmed = false;

				otpConfirmed = commonDAO.confirmOTP(phoneNumber, otp);

				if (otpConfirmed)
				{
					boolean otpDeleted = commonDAO.deleteOTP(phoneNumber);
					
					User user = new User();

					boolean userSaved = false;

					user = registrationDAO.getUser(phoneNumber);

					if (null == user)
					{
						user = new User();
						user.setPhoneNumber(phoneNumber);
						user.setCreatedDate(new Date());
						user.setStatus(true);
					}
					user.setMobile(true);

					userSaved = registrationDAO.saveUser(user);

					if (userSaved)
					{
						result = Constants.SUCCESS;
					}
				}
				else
				{
					result = Constants.OTP_NOT_VALID;
				}
			}
			else
			{
				result = Constants.INVALID_PHONE_NUMBER;
			}
		}
		catch (NumberFormatException exception)
		{
			throw new ServiceException(exception.getMessage(), exception);
		}
		catch (PersistenceException exception)
		{
			throw new PersistenceException(exception.getMessage(), exception);
		}
		catch (Exception exception)
		{
			throw new ServiceException(exception.getMessage(), exception);
		}

		return result;
	}

	public String registeredUserForWeb(HttpServletRequest request)
			throws ServiceException, PersistenceException
	{
		log.debug("In Method registeredUserForWeb(request)");

		String result = Constants.FAILURE;

		try
		{
			String phoneNumber = request.getParameter("phoneNumber") == null ? ""
					: request.getParameter("phoneNumber");

			loginDTO = new LoginDTO();
			loginDTO.setPhoneNumber(phoneNumber);

			if (commonUtil.validPhoneNumber(phoneNumber))
			{
				boolean registeredUser = false;

				registeredUser = registrationDAO
						.registeredUserForWeb(phoneNumber);

				if (registeredUser)
				{
					result = Constants.USER_ALREADY_REGISTERED;
				}
				else
				{
					result = Constants.USER_NOT_REGISTERED;
				}
			}
			else
			{
				result = Constants.INVALID_PHONE_NUMBER;
			}
		}
		catch (NumberFormatException exception)
		{
			throw new ServiceException(exception.getMessage(), exception);
		}
		catch (PersistenceException exception)
		{
			throw new PersistenceException(exception.getMessage(), exception);
		}
		catch (Exception exception)
		{
			throw new ServiceException(exception.getMessage(), exception);
		}

		return result;
	}

	public String confirmOTPFromWeb(HttpServletRequest request)
			throws ServiceException, PersistenceException
	{
		log.debug("In Method confirmOTPFromWeb(request)");

		String result = Constants.FAILURE;

		try
		{
			String phoneNumber = request.getParameter("phoneNumber") == null ? ""
					: request.getParameter("phoneNumber");
			String password = request.getParameter("password") == null ? ""
					: request.getParameter("password");
			String otp = request.getParameter("otp") == null ? "" : request
					.getParameter("otp");

			loginDTO = new LoginDTO();
			loginDTO.setPhoneNumber(phoneNumber);
			loginDTO.setPassword(password);
			loginDTO.setOtp(otp);

			if (commonUtil.validPhoneNumber(phoneNumber))
			{
				boolean otpConfirmed = false;

				otpConfirmed = commonDAO.confirmOTP(phoneNumber, otp);

				if (otpConfirmed)
				{
					boolean otpDeleted = commonDAO.deleteOTP(phoneNumber);
					
					User user = new User();

					boolean userSaved = false;

					user = registrationDAO.getUser(phoneNumber);

					if (null == user)
					{
						user = new User();
						user.setPhoneNumber(phoneNumber);
						user.setCreatedDate(new Date());
						user.setStatus(true);
					}

					user.setPassword(password);
					user.setWeb(true);

					userSaved = registrationDAO.saveUser(user);

					if (userSaved)
					{
						result = Constants.SUCCESS;
					}
				}
				else
				{
					result = Constants.OTP_NOT_VALID;
				}
			}
			else
			{
				result = Constants.INVALID_PHONE_NUMBER;
			}
		}
		catch (NumberFormatException exception)
		{
			throw new ServiceException(exception.getMessage(), exception);
		}
		catch (PersistenceException exception)
		{
			throw new PersistenceException(exception.getMessage(), exception);
		}
		catch (Exception exception)
		{
			throw new ServiceException(exception.getMessage(), exception);
		}

		return result;
	}

}