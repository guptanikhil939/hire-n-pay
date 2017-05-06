package com.hirenpay.service.impl;

import org.springframework.stereotype.Service;

import com.hirenpay.service.CostCalculatorService;

@Service("costCalculatorService")
public class CostCalculatorServiceImpl implements CostCalculatorService
{	
	double cost = 10;
	
	public double calculateEstimate(double distance)
	{
		return distance * cost;
	}
}