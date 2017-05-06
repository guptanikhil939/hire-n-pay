package com.hirenpay.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpVersion;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpParams;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hirenpay.dao.CommonDAO;
import com.hirenpay.dto.OrderDetailsDTO;
import com.hirenpay.exception.PersistenceException;
import com.hirenpay.exception.ServiceException;
import com.hirenpay.model.Otp;

@Service("commonUtil")
public class CommonUtil
{
	private static final Logger log = Logger.getLogger(CommonUtil.class);

	@Autowired
	CommonDAO commonDAO;

	private static final Random RANDOM = new SecureRandom();
	/** Length of password. @see #generateRandomPassword() */
	public static final int PASSWORD_LENGTH = 8;

	public String convertToJSONMessage(String result) throws ServiceException
	{
		log.debug("In Method convertToJSONMessage()");

		JSONObject obj = new JSONObject();

		try
		{
			obj.put("result", result);
		}
		catch (Exception exception)
		{
			throw new ServiceException(exception.getMessage(), exception);
		}

		return obj.toString();
	}

	public String convertOrderDetailsToJSONMessage(
			OrderDetailsDTO orderDetailsDTO) throws ServiceException
	{
		log.debug("In Method convertOrderDetailsToJSONMessage()");

		JSONObject obj = new JSONObject();

		try
		{
			obj.put("orderId", orderDetailsDTO.getOrderId() == null ? ""
					: orderDetailsDTO.getOrderId());
			obj.put("serviceId", orderDetailsDTO.getServiceId() == null ? ""
					: orderDetailsDTO.getServiceId());
			obj.put("serviceName",
					orderDetailsDTO.getServiceName() == null ? ""
							: orderDetailsDTO.getServiceName());
			obj.put("pickupContactName",
					orderDetailsDTO.getPickupContactName() == null ? ""
							: orderDetailsDTO.getPickupContactName());
			obj.put("pickupFlatNumber",
					orderDetailsDTO.getPickupFlatNumber() == null ? ""
							: orderDetailsDTO.getPickupFlatNumber());
			obj.put("pickupLocation",
					orderDetailsDTO.getPickupLocation() == null ? ""
							: orderDetailsDTO.getPickupLocation());
			obj.put("pickupCity", orderDetailsDTO.getPickupCity() == null ? ""
					: orderDetailsDTO.getPickupCity());
			obj.put("pickupPhoneNumber",
					orderDetailsDTO.getPickupPhoneNumber() == null ? ""
							: orderDetailsDTO.getPickupPhoneNumber());
			obj.put("pickUpDate", orderDetailsDTO.getPickUpDate() == null ? ""
					: orderDetailsDTO.getPickUpDate());

			if (null != orderDetailsDTO.getServiceCategoryId()
					&& Constants.DELIVERY_SERVICES.equals(orderDetailsDTO
							.getServiceCategoryId()))
			{
				obj.put("dropContactName",
						orderDetailsDTO.getDropContactName() == null ? ""
								: orderDetailsDTO.getDropContactName());
				obj.put("dropFlatNumber",
						orderDetailsDTO.getDropFlatNumber() == null ? ""
								: orderDetailsDTO.getDropFlatNumber());
				obj.put("dropLocation",
						orderDetailsDTO.getDropLocation() == null ? ""
								: orderDetailsDTO.getDropLocation());
				obj.put("dropCity", orderDetailsDTO.getDropCity() == null ? ""
						: orderDetailsDTO.getDropCity());
				obj.put("dropPhoneNumber",
						orderDetailsDTO.getDropPhoneNumber() == null ? ""
								: orderDetailsDTO.getDropPhoneNumber());
				obj.put("distance", orderDetailsDTO.getDistance() == null ? ""
						: orderDetailsDTO.getDistance());
				// obj.put("estimatedCost",
				// orderDetailsDTO.getEstimatedCost()==null?"":orderDetailsDTO.getEstimatedCost());
				obj.put("itemName", orderDetailsDTO.getItemName() == null ? ""
						: orderDetailsDTO.getItemName());
				obj.put("deliveryVehicle",
						orderDetailsDTO.getDeliveryVehicle() == null ? ""
								: orderDetailsDTO.getDeliveryVehicle());
				obj.put("itemWeight",
						orderDetailsDTO.getItemWeight() == null ? ""
								: orderDetailsDTO.getItemWeight());
			}
		}
		catch (Exception exception)
		{
			throw new ServiceException(exception.getMessage(), exception);
		}

		return obj.toString();
	}

	public Date convertMobileStringToDate(String dateString)
			throws ServiceException
	{
		log.debug("In Method convertMobileStringToDate()");

		Date date = null;
		String finlaString = dateString;

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		try
		{
			date = sdf.parse(finlaString);
		}
		catch (Exception exception)
		{
			throw new ServiceException(exception.getMessage(), exception);
		}

		return date;
	}

	public Date convertStringToDate(String dateString) throws ServiceException
	{
		log.debug("In Method convertStringToDate()");

		Date date = null;
		String finlaString = dateString;

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
		try
		{
			date = sdf.parse(finlaString);
		}
		catch (Exception exception)
		{
			throw new ServiceException(exception.getMessage(), exception);
		}

		return date;
	}

	public static String convertDateToString(Date date) throws ServiceException
	{
		log.debug("In Method formatDateToString()");

		String dateString = "";

		try
		{
			dateString = new SimpleDateFormat("dd/MM/yyyy hh:mm").format(date);
		}
		catch (Exception exception)
		{
			throw new ServiceException(exception.getMessage(), exception);
		}

		return dateString;
	}

	public JSONObject readJSON(HttpServletRequest request)
			throws ServiceException
	{
		log.debug("In Method readJSON()");

		BufferedReader reader = null;
		JSONObject jsonObj = null;

		try
		{
			StringBuilder sb = new StringBuilder();
			reader = request.getReader();
			String line;
			while ((line = reader.readLine()) != null)
			{
				sb.append(line).append('\n');
			}

			jsonObj = new JSONObject(sb.toString());
		}
		catch (IOException exception)
		{
			throw new ServiceException(exception.getMessage(), exception);
		}
		catch (JSONException exception)
		{
			throw new ServiceException(exception.getMessage(), exception);
		}
		finally
		{
			try
			{
				reader.close();
			}
			catch (IOException exception)
			{
				throw new ServiceException(exception.getMessage(), exception);
			}
		}

		return jsonObj;
	}

	public boolean validPhoneNumber(String phoneNumber) throws ServiceException
	{
		log.debug("In Method validPhoneNumber()");

		if (null != phoneNumber && !"".equals(phoneNumber)
				&& phoneNumber.length() == 10)
		{
			if (phoneNumber.matches("\\d{10}")) // to verify that characters
												// entered are only digits
			{
				return true;
			}
		}

		return false;
	}

	public String sendOTP(String phoneNumber) throws PersistenceException,
			ServiceException
	{
		log.debug("In Method sendOTP()");

		String result = Constants.FAILURE;

		try
		{
			String randomNumber = generateFourDigitRandomNumber();

			Otp otp = new Otp();
			boolean otpSaved;

			otp.setPhoneNumber(phoneNumber);
			otp.setOtp(randomNumber);
			otp.setGenerationDate(new Date());

			otpSaved = commonDAO.saveOTP(otp);

			if (otpSaved)
			{
				String smsMessage = ConfigurationReader.configurationBean
						.getSmsOTPMessage1()
						+ otp.getOtp()
						+ ConfigurationReader.configurationBean
								.getSmsOTPMessage2();

				List<String> phoneNumbers = new ArrayList<String>();
				phoneNumbers.add(phoneNumber);

				boolean messageSent = callSMSServer(smsMessage, phoneNumbers);

				if (messageSent)
				{
					result = Constants.SUCCESS;
				}
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

	public boolean callSMSServer(String smsMessage, List<String> phoneNumbers)
			throws ServiceException
	{
		log.debug("In Method callSMSServer()");

		try
		{
			List<String> finalUrl = prepareSMSUrl(smsMessage, phoneNumbers);

			HttpParams params = new BasicHttpParams();
			params.setParameter(CoreProtocolPNames.PROTOCOL_VERSION,
					HttpVersion.HTTP_1_1);
			DefaultHttpClient httpclient = new DefaultHttpClient(params);

			for (String url : finalUrl)
			{
//				 sendGet(url);
			}

			return true;
		}
		catch (Exception exception)
		{
			throw new ServiceException(exception.getMessage(), exception);
		}
	}

	// HTTP GET request
	private void sendGet(String url) throws Exception
	{
		try
		{
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			// optional default is GET
			con.setRequestMethod("GET");

			// add request header
			// con.setRequestProperty("User-Agent", USER_AGENT);

			int responseCode = con.getResponseCode();
			log.info("Sending 'GET' request to URL : " + url);
			log.info("Response Code : " + responseCode);

			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null)
			{
				response.append(inputLine);
			}
			in.close();

			// print result
			log.info(response.toString());
		}
		catch (Exception exception)
		{
			log.info("Problem while sending SMS: " + exception);
			throw new ServiceException(exception.getMessage(), exception);
		}

	}

	private List<String> prepareSMSUrl(String smsMessage, List phoneNumbers)
			throws ServiceException
	{
		log.debug("In Method prepareSMSUrl()");

		List<String> urls = new ArrayList<String>();

		try
		{
			String smsUrl = ConfigurationReader.configurationBean.getSmsURL();
			String smsUser = "&username="
					+ ConfigurationReader.configurationBean.getSmsUser();
			String smsPassword = "&password="
					+ ConfigurationReader.configurationBean.getSmsPassword();

			String tempStr = smsUrl + smsUser + smsPassword + "&message="
					+ URLEncoder.encode(smsMessage, "UTF-8");

			for (Object phoneNumber : phoneNumbers)
			{
				String finalUrl = tempStr + "&to=" + phoneNumber;

				urls.add(finalUrl);
			}
		}
		catch (Exception exception)
		{
			throw new ServiceException(exception.getMessage(), exception);
		}

		return urls;
	}

	public String generateFourDigitRandomNumber() throws ServiceException
	{
		log.debug("In Method generateFourDigitRandomNumber()");

		String randomNumber = "";

		try
		{
			List<Integer> numbers = new ArrayList<>();
			for (int i = 0; i < 10; i++)
			{
				numbers.add(i);
			}

			Collections.shuffle(numbers);

			for (int i = 0; i < 4; i++)
			{
				randomNumber += numbers.get(i).toString();
			}
		}
		catch (Exception exception)
		{
			throw new ServiceException(exception.getMessage(), exception);
		}

		return randomNumber;
	}

	/**
	 * Generate a random String suitable for use as a temporary password.
	 *
	 * @return String suitable for use as a temporary password
	 * @since 2.4
	 */
	public String generateRandomPassword()
	{
		// Pick from some letters that won't be easily mistaken for each
		// other. So, for example, omit o O and 0, 1 l and L.
		String letters = "abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ23456789+@";

		String pw = "";
		for (int i = 0; i < PASSWORD_LENGTH; i++)
		{
			int index = (int) (RANDOM.nextDouble() * letters.length());
			pw += letters.substring(index, index + 1);
		}
		return pw;
	}
}