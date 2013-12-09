package com.sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.sample.json.IOData;
import com.samples.entities.User;

public class AbstractTestNGTest {
	private static ObjectMapper objectMapper;
	
	@BeforeClass
	public static void   setupBeforeClass()
	{
		 
		objectMapper = new ObjectMapper();
	}
		
	@AfterClass
	public static void   teardownAfterClass()
	{
		
	}
	
	protected void matchMaps(final Map outputAsMap, final Map inputAsMap) {
		final Set keySet = outputAsMap.keySet();
		for (Object key : keySet) {
			Object value=outputAsMap.get(key);
			
			
			Object otherValue=inputAsMap.get(key);
			
			boolean mismatch=false;
			if(value!=null)
			{
				if(value instanceof Map)
				{
					matchMaps((Map)value, (Map)otherValue);
				}
				else
				{
					if(!value.equals(otherValue))
					{
						mismatch=true;
					}
				}
				
			}
			else 
			{
				if(otherValue!=null)
				{
					mismatch=true;
				}
			}
			if(mismatch)
			{
				System.err.println(key+"="+value+",otherValue="+otherValue);
			}
			else
			{
				System.out.println(key+"="+value+",otherValue="+otherValue);
			}
			if(mismatch)
			{
				throw new AssertionError();
			}
		}
	}

	protected IOData<User, User> processPostAndResponse(User inputObject, String url)
			throws IOException, JsonGenerationException, JsonMappingException,
			JsonParseException, UnsupportedEncodingException,
			ClientProtocolException {
		final String inputString = objectMapper.writeValueAsString(inputObject);
		System.out.println("inputString="+inputString);
		final HashMap inputAsMap = objectMapper.readValue(inputString, HashMap.class);
		
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpPost postRequest = new HttpPost(
				url);
			postRequest.addHeader("accept", "application/json");
			StringEntity input = new StringEntity(inputString);
			//StringEntity input = new StringEntity("{\"address\":{\"state\":\"state\",\"country\":\"country\",\"line3\":\"line3\",\"line2\":\"line2\",\"line1\":\"line1\",\"city\":\"city\",\"zipOrPin\":\"zipOrPin\"},\"id\":null,\"loginId\":\"loginId1\",\"gender\":\"M\",\"firstName\":\"FirstName1\",\"lastName\":\"LastName1\",\"emailId\":\"emailId@amail.com\",\"phone\":\"(011)2512-5189\",\"birthday\":980879400000,\"contacts\":[],\"title\":\"Mr\"}");
			input.setContentType("application/json");
			postRequest.setEntity(input);
 
		HttpResponse response = httpClient.execute(postRequest);
 
		if (response.getStatusLine().getStatusCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
				+ response.getStatusLine().getStatusCode());
		}
 
		
		BufferedReader br = new BufferedReader(
                        new InputStreamReader((response.getEntity().getContent())));

		StringBuilder output= new StringBuilder();
		for (String line= br.readLine(); line!=null; line= br.readLine()) {
			output.append(line);
			System.out.println(line);
			output.append("\n");
		}
		httpClient.getConnectionManager().shutdown();
		final String outputString = output.toString();
		System.out.println("outputString="+outputString);
		User outputObject=objectMapper.readValue(outputString, User.class);
		final HashMap outputAsMap = objectMapper.readValue(outputString, HashMap.class);
		IOData<User, User> io= new IOData<User, User>(inputObject, outputObject, inputString, outputString, inputAsMap, outputAsMap);
		return io;
	}


}
