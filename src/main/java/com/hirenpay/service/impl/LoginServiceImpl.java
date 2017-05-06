package com.hirenpay.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hirenpay.dao.LoginDAO;
import com.hirenpay.dao.RegistrationDAO;
import com.hirenpay.dto.UserDTO;
import com.hirenpay.exception.PersistenceException;
import com.hirenpay.exception.ServiceException;
import com.hirenpay.model.User;
import com.hirenpay.service.LoginService;
import com.hirenpay.util.CommonUtil;
import com.hirenpay.util.Constants;

@Service("loginService")
public class LoginServiceImpl implements LoginService
{
	@Autowired
	CommonUtil commonUtil;
	
	@Autowired
	LoginDAO loginDAO;
	
	@Autowired
	RegistrationDAO registrationDAO;

	private static final Logger log = Logger.getLogger(LoginServiceImpl.class);

	public String loginAuth(HttpServletRequest request)
			throws ServiceException, PersistenceException
	{
		String result = Constants.FAILURE;

		UserDTO userDTO = null;

		String phoneNumber = request.getParameter("phoneNumber");
		String password = request.getParameter("password");
		try
		{
			User user = loginDAO.loginUser(phoneNumber, password);

			if (null != user)
			{
				HttpSession session = request.getSession();
				userDTO = new UserDTO();
				userDTO.setUserId(user.getUserId());
				userDTO.setMobile(user.isMobile());
				userDTO.setWeb(user.isWeb());
				userDTO.setFirstName(user.getFirstName());
				userDTO.setLastName(user.getLastName());
				userDTO.setEmailId(user.getEmailId());
				userDTO.setPhoneNumber(user.getPhoneNumber());
				userDTO.setProfilePictureName(getProfilePicture(request,
						user.getProfilePictureName()));

				session.setAttribute("userDTO", userDTO);

				result = Constants.SUCCESS;
			}
			else
			{
				result = Constants.INVALID_CREDENTIALS;
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

	public String getProfilePicture(HttpServletRequest request,
			String profilePictureName) throws ServiceException
	{
		try
		{

			if (null != profilePictureName
					&& "default.png".equals(profilePictureName))
			{
				profilePictureName = "resources/images/"
						+ Constants.DEFAULT_PROFILE_IMAGE;
			}
			else
			{
				profilePictureName = request.getContextPath() + "/images/"
						+ profilePictureName;
			}
		}
		catch (NumberFormatException exception)
		{
			throw new ServiceException(exception.getMessage(), exception);
		}
		catch (Exception exception)
		{
			throw new ServiceException(exception.getMessage(), exception);
		}
		return profilePictureName;
	}

	public String forgotPassword(HttpServletRequest request)
			throws ServiceException
	{
		log.debug("In Method forgotPassword()");

		String result = Constants.FAILURE;

		try
		{
			String phoneNumber = request.getParameter("phoneNumber")==null?"":request.getParameter("phoneNumber");

			if (null != phoneNumber && phoneNumber.length() == 10)
			{
				List<String> phoneNumbers = new ArrayList<String>();
				phoneNumbers.add(phoneNumber);

				String tempPassword = commonUtil.generateRandomPassword();
				
				User user = registrationDAO.getUser(phoneNumber);
				
				if(null!=user)
				{
					user.setPassword(tempPassword);
					
					boolean userSaved = registrationDAO.saveUser(user);
					
					if(userSaved)
					{
						commonUtil.callSMSServer(Constants.FORGOT_PASSWORD_MESSAGE
								+ tempPassword + ". " + Constants.GET_APP_MESSAGE,
								phoneNumbers);
						return Constants.SUCCESS;
					}
					else
					{
						return Constants.FAILURE;
					}
				}
				else
				{
					return Constants.USER_NOT_REGISTERED;
				}
				
			}
		}
		catch (Exception exception)
		{
			throw new ServiceException(exception.getMessage(), exception);
		}

		return result;
	}
}
