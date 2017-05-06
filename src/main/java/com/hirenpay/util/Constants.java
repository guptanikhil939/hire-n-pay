package com.hirenpay.util;

public class Constants
{
	public static final String PROPERTY_FILE_PATH = "/hirenpay/config.properties";
	
	public static final Integer DELIVERY_SERVICES = 2;

	public static final String EMPTY_STRING = "";
	
	public static final String DEFAULT_PROFILE_IMAGE = "default.png";
	
	//messages	
	public static final String SUCCESS = "success";
	public static final String FAILURE = "failure";
	public static final String INVALID_PHONE_NUMBER = "Invalid Phone Number";
	public static final String USER_NOT_REGISTERED = "User Not Registered";
	public static final String USER_ALREADY_REGISTERED = "User Already Registered";
	public static final String COULD_NOT_FETCH_DISTANCE = "Could not calculate distance at the moment";
	public static final String OTP_NOT_VALID = "OTP not valid";
	public static final String INVALID_CREDENTIALS = "Invalid Credentials.";
	public static final String USER_ALREADY_SUBSCRIBED = "Seems like this number is already Subscribed. We will update you soon.";
	public static final String GET_APP_MESSAGE = "Download Hire N Pay app here : https://play.google.com/store/apps/details?id=com.hirenpay";
	public static final String FORGOT_PASSWORD_MESSAGE = "Your temporary password for login is ";

	//urls
	public static final String LOCATION_COORDINATES_PART_ONE = "http://maps.google.com/maps/api/geocode/json?address=";
	public static final String LOCATION_COORDINATES_PART_TWO = "&sensor=false";
	public static final String JSON_PARSE_RESULTS = "results";
	public static final String JSON_PARSE_FORMATTED_ADDRESS = "formatted_address";
	public static final String JSON_PARSE_GEOMETRY = "geometry";
	public static final String JSON_PARSE_LOCATION = "location";
	public static final String JSON_PARSE_LATITUDE = "lat";
	public static final String JSON_PARSE_LONGITUDE = "lng";
	public static final String DISATNCE_URL_PART_ONE = "http://maps.googleapis.com/maps/api/directions/json?origin=";
	public static final String DISATNCE_URL_PART_TWO = "&destination=";
	public static final String DISATNCE_URL_PART_THREE = "&sensor=false";
	public static final String JSON_PARSE_ROUTES = "routes";
	public static final String JSON_PARSE_LEGS = "legs";
	public static final String JSON_PARSE_DISTANCE = "distance";
	public static final String JSON_PARSE_TEXT = "text";
	public static final String JSON_PARSE_VALUE = "value";
}