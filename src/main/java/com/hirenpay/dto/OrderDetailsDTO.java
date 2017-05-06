package com.hirenpay.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class OrderDetailsDTO
{
	private Integer orderId;
	
	private Integer serviceCategoryId;
	
	private Integer serviceId;
	
	private String serviceName;
	
	private String pickupContactName;
	
	private String pickupFlatNumber;
	
	private String pickupLocation;
	
	private String pickupCity;
	
	private String pickupPhoneNumber;
	
	private Date pickUpDate;
	
	private String pickUpDateString;
	
	private String dropContactName;
	
	private String dropFlatNumber;
	
	private String dropLocation;
	
	private String dropCity;
	
	private String dropPhoneNumber;
	
	private Double distance;
	
	private Double estimatedCost;
	
	private String itemName;
	
	private String deliveryVehicle;
	
	private String itemWeight;

	public Integer getOrderId()
	{
		return orderId;
	}

	public void setOrderId(Integer orderId)
	{
		this.orderId = orderId;
	}

	public Integer getServiceId()
	{
		return serviceId;
	}

	public String getServiceName()
	{
		return serviceName;
	}

	public void setServiceName(String serviceName)
	{
		this.serviceName = serviceName;
	}

	public Integer getServiceCategoryId()
	{
		return serviceCategoryId;
	}

	public void setServiceCategoryId(Integer serviceCategoryId)
	{
		this.serviceCategoryId = serviceCategoryId;
	}

	public void setServiceId(Integer serviceId)
	{
		this.serviceId = serviceId;
	}

	public String getPickupContactName()
	{
		return pickupContactName;
	}

	public void setPickupContactName(String pickupContactName)
	{
		this.pickupContactName = pickupContactName;
	}
	
	public String getPickupFlatNumber()
	{
		return pickupFlatNumber;
	}

	public void setPickupFlatNumber(String pickupFlatNumber)
	{
		this.pickupFlatNumber = pickupFlatNumber;
	}

	public String getPickupLocation()
	{
		return pickupLocation;
	}

	public void setPickupLocation(String pickupLocation)
	{
		this.pickupLocation = pickupLocation;
	}

	public String getPickupCity()
	{
		return pickupCity;
	}

	public void setPickupCity(String pickupCity)
	{
		this.pickupCity = pickupCity;
	}

	public String getPickupPhoneNumber()
	{
		return pickupPhoneNumber;
	}

	public void setPickupPhoneNumber(String pickupPhoneNumber)
	{
		this.pickupPhoneNumber = pickupPhoneNumber;
	}

	public Date getPickUpDate()
	{
		return pickUpDate;
	}

	public void setPickUpDate(Date pickUpDate)
	{
		this.pickUpDate = pickUpDate;
	}

	public String getPickUpDateString()
	{
		return pickUpDateString;
	}

	public void setPickUpDateString(String pickUpDateString)
	{
		this.pickUpDateString = pickUpDateString;
	}

	public String getDropContactName()
	{
		return dropContactName;
	}

	public void setDropContactName(String dropContactName)
	{
		this.dropContactName = dropContactName;
	}

	public String getDropFlatNumber()
	{
		return dropFlatNumber;
	}

	public void setDropFlatNumber(String dropFlatNumber)
	{
		this.dropFlatNumber = dropFlatNumber;
	}

	public String getDropLocation()
	{
		return dropLocation;
	}

	public void setDropLocation(String dropLocation)
	{
		this.dropLocation = dropLocation;
	}

	public String getDropCity()
	{
		return dropCity;
	}

	public void setDropCity(String dropCity)
	{
		this.dropCity = dropCity;
	}

	public String getDropPhoneNumber()
	{
		return dropPhoneNumber;
	}

	public void setDropPhoneNumber(String dropPhoneNumber)
	{
		this.dropPhoneNumber = dropPhoneNumber;
	}

	public Double getDistance()
	{
		return distance;
	}

	public void setDistance(Double distance)
	{
		this.distance = distance;
	}

	public Double getEstimatedCost()
	{
		return estimatedCost;
	}

	public void setEstimatedCost(Double estimatedCost)
	{
		this.estimatedCost = estimatedCost;
	}

	public String getItemName()
	{
		return itemName;
	}

	public void setItemName(String itemName)
	{
		this.itemName = itemName;
	}

	public String getDeliveryVehicle()
	{
		return deliveryVehicle;
	}

	public void setDeliveryVehicle(String deliveryVehicle)
	{
		this.deliveryVehicle = deliveryVehicle;
	}

	public String getItemWeight()
	{
		return itemWeight;
	}

	public void setItemWeight(String itemWeight)
	{
		this.itemWeight = itemWeight;
	}
}