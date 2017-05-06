package com.hirenpay.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hirenpay.dao.CommonDAO;
import com.hirenpay.dao.OrderDetailsDAO;
import com.hirenpay.dto.OrderDetailsDTO;
import com.hirenpay.exception.PersistenceException;
import com.hirenpay.exception.ServiceException;
import com.hirenpay.model.OrderDetails;
import com.hirenpay.model.User;
import com.hirenpay.service.CostCalculatorService;
import com.hirenpay.service.GoogleService;
import com.hirenpay.service.OrderService;
import com.hirenpay.util.CommonUtil;
import com.hirenpay.util.ConfigurationReader;
import com.hirenpay.util.Constants;

@Transactional
@Service("orderService")
public class OrderServiceImpl implements OrderService
{
	private static final Logger log = Logger.getLogger(OrderServiceImpl.class);

	@Autowired
	GoogleService googleService;

	@Autowired
	CommonUtil commonUtil;

	@Autowired
	CostCalculatorService costCalculatorService;

	@Autowired
	OrderDetailsDAO orderDetailsDAO;

	@Autowired
	CommonDAO commonDAO;

	public OrderDetailsDTO saveOrder(HttpServletRequest request)
			throws ServiceException, PersistenceException
	{
		log.debug("In Method saveOrder(request)");

		int orderId = -1;
		OrderDetails orderDetails = null;
		OrderDetailsDTO orderDetailsDTO = null;
		Integer serviceId = null;
		String serviceName = "";
		String dropContactName = "";
		String dropFlatNumber = "";
		String dropLocation = "";
		String dropCity = "";
		String dropPhoneNumber = "";
		String itemName = null;
		String deliveryVehicle = "";
		String itemWeight = "";

		try
		{
			JSONObject jsonObj = commonUtil.readJSON(request);

			Integer serviceCategoryId = Integer.parseInt((String) jsonObj
					.get("serviceCategoryId"));

			if (null != serviceCategoryId
					&& !Constants.DELIVERY_SERVICES.equals(serviceCategoryId))
			{
				serviceId = Integer.parseInt((String) jsonObj.get("serviceId"));
				serviceName = orderDetailsDAO.getServiceName(serviceId);
			}

			String pickupContactName = (String) jsonObj
					.get("pickupContactName");
			String pickupFlatNumber = (String) jsonObj.get("pickupFlatNumber");
			String pickupLocation = (String) jsonObj.get("pickupLocation");
			String pickupCity = (String) jsonObj.get("pickupCity");
			String pickupPhoneNumber = (String) jsonObj
					.get("pickupPhoneNumber");
			Date pickUpDate = commonUtil
					.convertMobileStringToDate((String) jsonObj
							.get("pickUpDate"));

			if (null != serviceCategoryId
					&& Constants.DELIVERY_SERVICES.equals(serviceCategoryId))
			{
				dropContactName = (String) jsonObj.get("dropContactName");
				dropFlatNumber = (String) jsonObj.get("dropFlatNumber");
				dropLocation = (String) jsonObj.get("dropLocation");
				dropCity = (String) jsonObj.get("dropCity");
				dropPhoneNumber = (String) jsonObj.get("dropPhoneNumber");
				itemName = (String) jsonObj.get("itemName");
				deliveryVehicle = (String) jsonObj.get("deliveryVehicle");
				itemWeight = (String) jsonObj.get("itemWeight");
			}

			orderDetails = new OrderDetails();
			orderDetails.setServiceId(serviceId);
			orderDetails.setPickupContactName(pickupContactName);
			orderDetails.setPickupFlatNumber(pickupFlatNumber);
			orderDetails.setPickupLocation(pickupLocation);
			orderDetails.setPickupCity(pickupCity);
			orderDetails.setPickupPhoneNumber(pickupPhoneNumber);
			orderDetails.setPickUpDate(pickUpDate);

			if (null != serviceCategoryId
					&& Constants.DELIVERY_SERVICES.equals(serviceCategoryId))
			{
				serviceName = "Delivery";
				orderDetails.setDropContactName(dropContactName);
				orderDetails.setDropFlatNumber(dropFlatNumber);
				orderDetails.setDropLocation(dropLocation);
				orderDetails.setDropCity(dropCity);
				orderDetails.setDropPhoneNumber(dropPhoneNumber);
				orderDetails.setItemName(itemName);
				orderDetails.setDeliveryVehicle(deliveryVehicle);
				orderDetails.setItemWeight(itemWeight);

				String distanceStr = googleService.calculateDistance(
						pickupLocation, pickupCity, dropLocation, dropCity);
				double distance = 0.0;

				if (null != distanceStr
						&& !Constants.COULD_NOT_FETCH_DISTANCE
								.equals(distanceStr))
				{
					distance = Double.parseDouble(distanceStr);
					double estimatedCost = costCalculatorService
							.calculateEstimate(distance);

					orderDetails.setDistance(distance);
					orderDetails.setEstimatedCost(estimatedCost);
				}
			}

			orderId = orderDetailsDAO.saveOrder(orderDetails);

			if (orderId > 0)
			{
				SimpleDateFormat sdf = new SimpleDateFormat(
						"E dd/MM/yyyy 'at' hh:mm:ss a");

				String smsMessage = ConfigurationReader.configurationBean
						.getSmsOrderMessage1()
						+ "Order Id: "
						+ orderId
						+ ", Service Name: "
						+ serviceName
						+ ", Time: "
						+ pickUpDate
						+ ConfigurationReader.configurationBean
								.getSmsOrderMessage2();

				String officePhoneNumber = ConfigurationReader.configurationBean
						.getOfficePhoneNumber();

				List<String> phoneNumbers = new ArrayList<String>();
				phoneNumbers.add(pickupPhoneNumber);
				phoneNumbers.add(officePhoneNumber);

				boolean messageSent = commonUtil.callSMSServer(smsMessage,
						phoneNumbers);

				if (messageSent)
				{
					log.info("Order Confirmation Message Sent.");
				}
				else
				{
					log.info("Order Confirmation Message Not Sent.");
				}

				orderDetailsDTO = new OrderDetailsDTO();
				orderDetailsDTO.setServiceCategoryId(serviceCategoryId);
				orderDetailsDTO.setOrderId(orderDetails.getOrderId());
				orderDetailsDTO.setServiceId(orderDetails.getServiceId());
				orderDetailsDTO.setServiceName(serviceName);
				orderDetailsDTO.setPickupContactName(orderDetails
						.getPickupContactName());
				orderDetailsDTO.setPickupFlatNumber(orderDetails
						.getPickupFlatNumber());
				orderDetailsDTO.setPickupLocation(orderDetails
						.getPickupLocation());
				orderDetailsDTO.setPickupCity(orderDetails.getPickupCity());
				orderDetailsDTO.setPickupPhoneNumber(orderDetails
						.getPickupPhoneNumber());
				orderDetailsDTO.setPickUpDate(orderDetails.getPickUpDate());

				if (null != serviceCategoryId
						&& Constants.DELIVERY_SERVICES
								.equals(serviceCategoryId))
				{
					orderDetailsDTO.setDropContactName(orderDetails
							.getDropContactName());
					orderDetailsDTO.setDropFlatNumber(orderDetails
							.getDropFlatNumber());
					orderDetailsDTO.setDropLocation(orderDetails
							.getDropLocation());
					orderDetailsDTO.setDropCity(orderDetails.getDropCity());
					orderDetailsDTO.setDropPhoneNumber(orderDetails
							.getDropPhoneNumber());
					orderDetailsDTO.setDistance(orderDetails.getDistance());
					// orderDetailsDTO.setEstimatedCost(orderDetails
					// .getEstimatedCost());
					orderDetailsDTO.setDeliveryVehicle(orderDetails
							.getDeliveryVehicle());
					orderDetailsDTO.setItemWeight(orderDetails.getItemWeight());
					orderDetailsDTO.setDropContactName(orderDetails
							.getDropContactName());
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

		return orderDetailsDTO;
	}

	public OrderDetailsDTO saveOrderWeb(HttpServletRequest request)
			throws ServiceException, PersistenceException
	{
		log.debug("In Method saveOrder(request)");

		int orderId = -1;
		OrderDetails orderDetails = null;
		OrderDetailsDTO orderDetailsDTO = null;
		Integer serviceId = null;
		String selectedServiceList = "";
		String serviceName = "";
		String dropContactName = "";
		String dropFlatNumber = "";
		String dropLocation = "";
		String dropCity = "";
		String dropPhoneNumber = "";
		String itemName = null;
		String deliveryVehicle = "";
		String itemWeight = "";
		String services[] = null;

		try
		{
			Integer serviceCategoryId = Integer.parseInt((String) request
					.getParameter("serviceCategoryId") == null ? "0" : request
					.getParameter("serviceCategoryId"));

			if (null != serviceCategoryId
					&& !Constants.DELIVERY_SERVICES.equals(serviceCategoryId))
			{
				selectedServiceList = (String) request
						.getParameter("selectedServiceList") == null ? ""
						: request.getParameter("selectedServiceList");
				services = (selectedServiceList.replaceAll("service", ""))
						.split(",");
			}

			String pickupContactName = (String) request
					.getParameter("pickupContactName") == null ? "" : request
					.getParameter("pickupContactName");
			String pickupFlatNumber = (String) request
					.getParameter("pickupFlatNumber") == null ? "" : request
					.getParameter("pickupFlatNumber");
			String pickupLocation = (String) request
					.getParameter("pickupLocation") == null ? "" : request
					.getParameter("pickupLocation");
			String pickupCity = (String) request.getParameter("pickupCity") == null ? ""
					: request.getParameter("pickupCity");
			String pickupPhoneNumber = (String) request
					.getParameter("pickupPhoneNumber") == null ? "" : request
					.getParameter("pickupPhoneNumber");
			String date = (String) request.getParameter("date") == null ? ""
					: request.getParameter("date");

			String timeHours = (String) request.getParameter("timeHours") == null ? ""
					: request.getParameter("timeHours");

			String timeMinutes = (String) request.getParameter("timeMinutes") == null ? ""
					: request.getParameter("timeMinutes");

			String timeAmPm = (String) request.getParameter("timeAmPm") == null ? ""
					: request.getParameter("timeAmPm");

			String time = (String) request.getParameter("time") == null ? ""
					: request.getParameter("time");

			Date pickupDate = new Date();

			if (null != time && time.equals("0"))
			{
				pickupDate = new Date();
				Calendar cal = Calendar.getInstance();
				cal.setTime(pickupDate);
				cal.add(Calendar.MINUTE, 30);

				pickupDate = cal.getTime();
			}
			else
			{
				pickupDate = commonUtil.convertStringToDate(date + " "
						+ timeHours + ":" + timeMinutes + " " + timeAmPm);
			}

			if (null != serviceCategoryId
					&& Constants.DELIVERY_SERVICES.equals(serviceCategoryId))
			{
				dropContactName = (String) request
						.getParameter("dropContactName") == null ? "" : request
						.getParameter("dropContactName");
				dropFlatNumber = (String) request
						.getParameter("dropFlatNumber") == null ? "" : request
						.getParameter("dropFlatNumber");
				dropLocation = (String) request.getParameter("dropLocation") == null ? ""
						: request.getParameter("dropLocation");
				dropCity = (String) request.getParameter("dropCity") == null ? ""
						: request.getParameter("dropCity");
				dropPhoneNumber = (String) request
						.getParameter("dropPhoneNumber") == null ? "" : request
						.getParameter("dropPhoneNumber");
				itemName = (String) request.getParameter("itemName") == null ? ""
						: request.getParameter("itemName");
				deliveryVehicle = (String) request
						.getParameter("deliveryVehicle") == null ? "" : request
						.getParameter("deliveryVehicle");
				itemWeight = (String) request.getParameter("itemWeight") == null ? ""
						: request.getParameter("itemWeight");
			}

			if (null != serviceCategoryId
					&& !Constants.DELIVERY_SERVICES.equals(serviceCategoryId))
			{
				for (int i = 0; i < services.length; i++)
				{
					orderDetails = new OrderDetails();
					orderDetails.setServiceId(serviceId);
					orderDetails.setPickupContactName(pickupContactName);
					orderDetails.setPickupFlatNumber(pickupFlatNumber);
					orderDetails.setPickupLocation(pickupLocation);
					orderDetails.setPickupCity(pickupCity);
					orderDetails.setPickupPhoneNumber(pickupPhoneNumber);
					orderDetails.setPickUpDate(pickupDate);

					orderDetails.setServiceId(Integer.parseInt(services[i]));
					orderId = orderDetailsDAO.saveOrder(orderDetails);
				}
			}

			if (null != serviceCategoryId
					&& Constants.DELIVERY_SERVICES.equals(serviceCategoryId))
			{
				serviceName = "Delivery";
				
				orderDetails = new OrderDetails();
				orderDetails.setServiceId(serviceId);
				orderDetails.setPickupContactName(pickupContactName);
				orderDetails.setPickupFlatNumber(pickupFlatNumber);
				orderDetails.setPickupLocation(pickupLocation);
				orderDetails.setPickupCity(pickupCity);
				orderDetails.setPickupPhoneNumber(pickupPhoneNumber);
				orderDetails.setPickUpDate(pickupDate);
				
				orderDetails.setDropContactName(dropContactName);
				orderDetails.setDropFlatNumber(dropFlatNumber);
				orderDetails.setDropLocation(dropLocation);
				orderDetails.setDropCity(dropCity);
				orderDetails.setDropPhoneNumber(dropPhoneNumber);
				orderDetails.setItemName(itemName);
				orderDetails.setDeliveryVehicle(deliveryVehicle);
				orderDetails.setItemWeight(itemWeight);

				String distanceStr = googleService.calculateDistance(
						pickupLocation, pickupCity, dropLocation, dropCity);
				double distance = 0.0;

				if (null != distanceStr
						&& !Constants.COULD_NOT_FETCH_DISTANCE
								.equals(distanceStr))
				{
					distance = Double.parseDouble(distanceStr);
					double estimatedCost = costCalculatorService
							.calculateEstimate(distance);

					orderDetails.setDistance(distance);
					orderDetails.setEstimatedCost(estimatedCost);
				}
				
				orderId = orderDetailsDAO.saveOrder(orderDetails);
			}

			if (orderId > 0)
			{
				String smsMessage = ConfigurationReader.configurationBean
						.getSmsOrderMessage1()
						+ "Order Id: "
						+ orderId
						+ ", Service Name: "
						+ serviceName
						+ ", Time: "
						+ pickupDate
						+ ConfigurationReader.configurationBean
								.getSmsOrderMessage2();

				String officePhoneNumber = ConfigurationReader.configurationBean
						.getOfficePhoneNumber();

				List<String> phoneNumbers = new ArrayList<String>();
				phoneNumbers.add(pickupPhoneNumber);
				phoneNumbers.add(officePhoneNumber);

				boolean messageSent = commonUtil.callSMSServer(smsMessage,
						phoneNumbers);

				if (messageSent)
				{
					log.info("Order Confirmation Message Sent.");
				}
				else
				{
					log.info("Order Confirmation Message Not Sent.");
				}

				orderDetailsDTO = new OrderDetailsDTO();
				orderDetailsDTO.setServiceCategoryId(serviceCategoryId);
				orderDetailsDTO.setOrderId(orderDetails.getOrderId());
				orderDetailsDTO.setServiceId(orderDetails.getServiceId());
				orderDetailsDTO.setPickupContactName(orderDetails
						.getPickupContactName());
				orderDetailsDTO.setPickupFlatNumber(orderDetails
						.getPickupFlatNumber());
				orderDetailsDTO.setPickupLocation(orderDetails
						.getPickupLocation());
				orderDetailsDTO.setPickupCity(orderDetails.getPickupCity());
				orderDetailsDTO.setPickupPhoneNumber(orderDetails
						.getPickupPhoneNumber());
				orderDetailsDTO.setPickUpDate(orderDetails.getPickUpDate());

				if (null != serviceCategoryId
						&& Constants.DELIVERY_SERVICES
								.equals(serviceCategoryId))
				{
					orderDetailsDTO.setDropContactName(orderDetails
							.getDropContactName());
					orderDetailsDTO.setDropFlatNumber(orderDetails
							.getDropFlatNumber());
					orderDetailsDTO.setDropLocation(orderDetails
							.getDropLocation());
					orderDetailsDTO.setDropCity(orderDetails.getDropCity());
					orderDetailsDTO.setDropPhoneNumber(orderDetails
							.getDropPhoneNumber());
					orderDetailsDTO.setDistance(orderDetails.getDistance());
					orderDetailsDTO.setEstimatedCost(orderDetails
							.getEstimatedCost());
					orderDetailsDTO.setDeliveryVehicle(orderDetails
							.getDeliveryVehicle());
					orderDetailsDTO.setItemWeight(orderDetails.getItemWeight());
					orderDetailsDTO.setDropContactName(orderDetails
							.getDropContactName());
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

		return orderDetailsDTO;
	}

	@Override
	public String sendRequestConfirmationOTP(HttpServletRequest request)
			throws ServiceException, PersistenceException
	{
		String phoneNumber = request.getParameter("phoneNumber") == null ? ""
				: request.getParameter("phoneNumber");

		return commonUtil.sendOTP(phoneNumber);

	}

	@Override
	public String confirmRequestConfirmationOTP(HttpServletRequest request)
			throws ServiceException, PersistenceException
	{
		log.debug("In Method confirmOTPFromWeb(request)");

		String result = Constants.FAILURE;

		try
		{
			String phoneNumber = request.getParameter("phoneNumber") == null ? ""
					: request.getParameter("phoneNumber");
			String otp = request.getParameter("otp") == null ? "" : request
					.getParameter("otp");

			if (commonUtil.validPhoneNumber(phoneNumber))
			{
				boolean otpConfirmed = false;

				otpConfirmed = commonDAO.confirmOTP(phoneNumber, otp);

				if (otpConfirmed)
				{
					boolean otpDeleted = commonDAO.deleteOTP(phoneNumber);
					result = Constants.SUCCESS;
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