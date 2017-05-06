package com.hirenpay.dao;

import java.util.List;

import com.hirenpay.dto.UserDTO;
import com.hirenpay.exception.PersistenceException;
import com.hirenpay.model.OrderDetails;
import com.hirenpay.model.User;

public interface AccountDAO
{
	public List<OrderDetails> getOrderDetailsList(String phoneNumber) throws PersistenceException;

	public User getProfile(String phoneNumber) throws PersistenceException;

	public boolean updateProfile(User user) throws PersistenceException;
}
