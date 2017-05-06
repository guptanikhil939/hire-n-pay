package com.hirenpay.service.impl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.HashMap;

import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpParams;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.hirenpay.exception.ServiceException;
import com.hirenpay.service.GoogleService;
import com.hirenpay.util.Constants;

@Service("googleService")
public class GoogleServiceImpl implements GoogleService
{
	private static final Logger log = Logger.getLogger(OrderServiceImpl.class);

	public String calculateDistance(String pickupLocation, String pickupCity,
			String dropLocation, String dropCity) throws ServiceException
	{
		log.debug("In Method calculateDistance(pickupLocation, pickupCity, dropLocation, dropCity)");

		String message = Constants.COULD_NOT_FETCH_DISTANCE;

		try
		{
			String pickUrl = Constants.LOCATION_COORDINATES_PART_ONE
					+ URLEncoder.encode(pickupLocation + " " + pickupCity,
							"UTF-8") + Constants.LOCATION_COORDINATES_PART_TWO;
			String dropUrl = Constants.LOCATION_COORDINATES_PART_ONE
					+ URLEncoder.encode(dropLocation + " " + dropCity, "UTF-8")
					+ Constants.LOCATION_COORDINATES_PART_TWO;
			HashMap<String, String> pickUpCoordinates = new HashMap<String, String>();
			pickUpCoordinates = getLocation(pickUrl, pickupCity);
			HashMap<String, String> dropCoordinates = new HashMap<String, String>();
			dropCoordinates = getLocation(dropUrl, dropCity);
			if (pickUpCoordinates.get(Constants.JSON_PARSE_LONGITUDE) != null
					&& dropCoordinates.get(Constants.JSON_PARSE_LONGITUDE) != null
					&& pickUpCoordinates.get(Constants.JSON_PARSE_LATITUDE) != null
					&& dropCoordinates.get(Constants.JSON_PARSE_LATITUDE) != null)
			{
				String distance = getDistance(pickUpCoordinates,
						dropCoordinates);
				if (null != distance && !"".equals(distance))
				{
					Integer distanceInt = Integer.parseInt(distance);
					String distanceKM = new DecimalFormat("####.##")
							.format(distanceInt / 1000.0);
					message = distanceKM;
				}
			}
		}
		catch (NumberFormatException exception)
		{
			log.error("NumberFormatException while calculating distance : "
					+ exception);
		}
		catch (Exception exception)
		{
			log.error("Exception while calculating distance : " + exception);
		}

		return message;
	}

	private String getDistance(HashMap<String, String> pickUpCoordinates,
			HashMap<String, String> dropCoordinates) throws ServiceException
	{
		String distance = null;
		HttpParams params = new BasicHttpParams();
		params.setParameter(CoreProtocolPNames.PROTOCOL_VERSION,
				HttpVersion.HTTP_1_1);
		DefaultHttpClient httpclient = new DefaultHttpClient(params);
		HttpPost httppost = new HttpPost(makeUrl(pickUpCoordinates,
				dropCoordinates));
		HttpResponse response;
		try
		{
			response = httpclient.execute(httppost);
			org.apache.http.HttpEntity entity = response.getEntity();
			InputStream is = null;
			is = entity.getContent();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "iso-8859-1"), 8);
			StringBuilder sb = new StringBuilder();
			sb.append(reader.readLine() + "\n");
			String line = "0";
			while ((line = reader.readLine()) != null)
			{
				sb.append(line + "\n");
			}
			is.close();
			reader.close();
			String result = sb.toString();
			JSONObject jsonObject = new JSONObject(result);
			JSONArray resultsArray = jsonObject
					.getJSONArray(Constants.JSON_PARSE_ROUTES);
			JSONObject resultObject = resultsArray.getJSONObject(0);
			JSONArray legsArray = resultObject
					.getJSONArray(Constants.JSON_PARSE_LEGS);
			JSONObject legsJsonObject = legsArray.getJSONObject(0);
			JSONObject distanceJsonObject = legsJsonObject
					.getJSONObject(Constants.JSON_PARSE_DISTANCE);
			distance = distanceJsonObject.optString(Constants.JSON_PARSE_VALUE)
					.toString();
		}
		catch (Exception exception)
		{
			throw new ServiceException(exception.getMessage(), exception);
		}
		return distance;
	}

	private String makeUrl(HashMap<String, String> pickUpCoordinates,
			HashMap<String, String> dropCoordinates)
	{
		return Constants.DISATNCE_URL_PART_ONE
				+ pickUpCoordinates.get(Constants.JSON_PARSE_LATITUDE) + ","
				+ pickUpCoordinates.get(Constants.JSON_PARSE_LONGITUDE)
				+ Constants.DISATNCE_URL_PART_TWO
				+ dropCoordinates.get(Constants.JSON_PARSE_LATITUDE) + ","
				+ dropCoordinates.get(Constants.JSON_PARSE_LONGITUDE)
				+ Constants.DISATNCE_URL_PART_THREE;
	}

	private HashMap<String, String> getLocation(String url, String city)
			throws ServiceException
	{
		HashMap<String, String> coordinates = new HashMap<String, String>();

		try
		{
			HttpParams params = new BasicHttpParams();
			params.setParameter(CoreProtocolPNames.PROTOCOL_VERSION,
					HttpVersion.HTTP_1_1);
			DefaultHttpClient httpclient = new DefaultHttpClient(params);

			HttpPost httppost = new HttpPost(url);
			HttpResponse response;
			response = httpclient.execute(httppost);
			org.apache.http.HttpEntity entity = response.getEntity();
			InputStream is = null;
			is = entity.getContent();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "iso-8859-1"), 8);
			StringBuilder sb = new StringBuilder();
			sb.append(reader.readLine() + "\n");
			String line = "0";
			while ((line = reader.readLine()) != null)
			{
				sb.append(line + "\n");
			}
			is.close();
			reader.close();
			String result = sb.toString();
			JSONObject jsonObject = new JSONObject(result);
			JSONArray resultsArray = jsonObject
					.getJSONArray(Constants.JSON_PARSE_RESULTS);
			for (int i = 0;; i++)
			{
				try
				{
					JSONObject resultObject = resultsArray.getJSONObject(i);
					String formattedAddress = resultObject
							.optString(Constants.JSON_PARSE_FORMATTED_ADDRESS);
					if (formattedAddress.contains(city))
					{
						JSONObject geometry = resultObject
								.getJSONObject(Constants.JSON_PARSE_GEOMETRY);
						JSONObject location = geometry
								.getJSONObject(Constants.JSON_PARSE_LOCATION);
						coordinates.put(Constants.JSON_PARSE_LATITUDE, location
								.optString(Constants.JSON_PARSE_LATITUDE)
								.toString());
						coordinates.put(
								Constants.JSON_PARSE_LONGITUDE,
								location.optString(
										Constants.JSON_PARSE_LONGITUDE)
										.toString());
						break;
					}
					else
					{
						continue;
					}
				}
				catch (NumberFormatException exception)
				{
					throw new ServiceException(exception.getMessage(),
							exception);
				}
				catch (Exception exception)
				{
					throw new ServiceException(exception.getMessage(),
							exception);
				}
			}
		}
		catch (Exception exception)
		{
			throw new ServiceException(exception.getMessage(), exception);
		}
		return coordinates;
	}
}
