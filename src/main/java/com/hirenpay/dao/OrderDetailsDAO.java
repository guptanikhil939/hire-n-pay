package com.hirenpay.dao;

import com.hirenpay.exception.PersistenceException;
import com.hirenpay.model.OrderDetails;

public interface OrderDetailsDAO
{
	Integer saveOrder(OrderDetails orderDetails) throws PersistenceException;

	String getServiceName(Integer serviceId) throws PersistenceException;
}
