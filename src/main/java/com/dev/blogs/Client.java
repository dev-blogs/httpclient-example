package com.dev.blogs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

public class Client {
	public static final String cookieName = "";
	public static final String cookieValue = "";
	
	public HttpResponse sendRequestByGetMethod(URL url) {
		try {
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet get = new HttpGet(url.toString());
			
			get.addHeader("Accept", "application/xml");
			get.addHeader("Content-Type", "application/json");
			if (!cookieName.equals("") && !cookieValue.equals("")) {
				get.addHeader("Cookie", cookieName + "=" + cookieValue);
			}
	
			HttpResponse response = client.execute(get);
			
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public HttpResponse sendRequestByPostMethodWithSingleParameter(URL url, String data) {
		try {
			HttpClient client = HttpClientBuilder.create().build();
			HttpPost post = new HttpPost(url.toString());
	
			post.addHeader("Accept", "application/json");
			post.addHeader("Content-Type", "application/json");
			if (!cookieName.equals("") && !cookieValue.equals("")) {
				post.addHeader("Cookie", cookieName + "=" + cookieValue);
			}
	
			// for single parameter
			post.setEntity(new StringEntity(data));
	
			HttpResponse response = client.execute(post);
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public HttpResponse sendRequestByPostMethodWithMultipleParameters(URL url, String data) {
		try {
			HttpClient client = HttpClientBuilder.create().build();
			HttpPost post = new HttpPost(url.toString());
	
			post.addHeader("Accept", "application/json");
			post.addHeader("Content-Type", "application/json");
			if (!cookieName.equals("") && !cookieValue.equals("")) {
				post.addHeader("Cookie", cookieName + "=" + cookieValue);
			}
	
			// For multiple parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("name1", "value1"));
			HttpEntity entity = new UrlEncodedFormEntity(params);
			post.setEntity(entity);
	
			HttpResponse response = client.execute(post);
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void printContent(HttpResponse response) {
		try {
			int statusCode = response.getStatusLine().getStatusCode();
			
			System.out.println("Status code: " + statusCode);
	
			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			StringBuilder result = new StringBuilder();
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
			System.out.println("Content: " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
	}
}