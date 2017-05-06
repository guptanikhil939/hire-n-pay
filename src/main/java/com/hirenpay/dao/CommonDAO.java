package com.hirenpay.dao;

import com.hirenpay.exception.PersistenceException;
import com.hirenpay.model.Otp;

public interface CommonDAO
{
	public boolean saveOTP(Otp otp) throws PersistenceException;

	public boolean confirmOTP(String phoneNumber, String otp)
			throws PersistenceException;

	public boolean deleteOTP(String phoneNumber) throws PersistenceException;
}
