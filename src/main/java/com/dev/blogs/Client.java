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
	private String cookieName;
	private String cookieValue;
	private int statusCode;
	private String content;
	
	public Client() {
		this.cookieName = "";
		this.cookieValue = "";
	}
	
	public Client(String cookieName, String cookieValue) {
		this.cookieName = cookieName;
		this.cookieValue = cookieValue;
	}
	
	public String getCookieName() {
		return cookieName;
	}

	public void setCookieName(String cookieName) {
		this.cookieName = cookieName;
	}

	public String getCookieValue() {
		return cookieValue;
	}

	public void setCookieValue(String cookieValue) {
		this.cookieValue = cookieValue;
	}
	
	public int getStatusCode() {
		return statusCode;
	}

	public String getContent() {
		return content;
	}

	public void sendRequestByGetMethod(URL url) {
		try {
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet get = new HttpGet(url.toString());
			
			get.addHeader("Accept", "application/xml");
			get.addHeader("Content-Type", "application/json");
			if (!cookieName.equals("") && !cookieValue.equals("")) {
				get.addHeader("Cookie", cookieName + "=" + cookieValue);
			}
	
			HttpResponse response = client.execute(get);
			
			setResults(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void sendRequestByPostMethodWithSingleParameter(URL url, String data) {
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
			setResults(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void sendRequestByPostMethodWithMultipleParameters(URL url, String data) {
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
			setResults(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void setResults(HttpResponse response) {
		try {
			statusCode = response.getStatusLine().getStatusCode();
			
			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			StringBuilder result = new StringBuilder();
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
			content = result.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
}