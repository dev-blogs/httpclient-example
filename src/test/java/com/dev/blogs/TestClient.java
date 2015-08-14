package com.dev.blogs;

import java.net.URL;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestClient {
	private Client client;
	
	@Before
	public void init() {
		client = new Client();
	}
	
	@Test
	public void testGetMethod() {
		try {
			URL url = new URL("http://www.google.ru");
			client.sendRequestByGetMethod(url);
			assertEquals(client.getStatusCode(), 200);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testPostMethod() {
		
	}
}