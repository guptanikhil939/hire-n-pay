package com.hirenpay.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "order_details")
public class OrderDetails
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "order_id")
	private Integer orderId;

	@Column(name = "service_id")
	private Integer serviceId;

	@Column(name = "pickup_contact_name")
	private String pickupContactName;

	@Column(name = "pickup_flat_number")
	private String pickupFlatNumber;

	@Column(name = "pickup_location")
	private String pickupLocation;

	@Column(name = "pickup_city")
	private String pickupCity;

	@Column(name = "pickup_phone_number")
	private String pickupPhoneNumber;

	@Column(name = "pickup_date")
	private Date pickUpDate;

	@Column(name = "drop_contact_name")
	private String dropContactName;

	@Column(name = "drop_flat_number")
	private String dropFlatNumber;

	@Column(name = "drop_location")
	private String dropLocation;

	@Column(name = "drop_city")
	private String dropCity;

	@Column(name = "drop_phone_number")
	private String dropPhoneNumber;

	@Column(name = "item_name")
	private String itemName;

	@Column(name = "delivery_vehicle")
	private String deliveryVehicle;

	@Column(name = "item_weight")
	private String itemWeight;

	@Column(name = "distance")
	private Double distance;

	@Column(name = "estimated_cost")
	private Double estimatedCost;

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
}