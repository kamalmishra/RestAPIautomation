package com.api.assignment;

import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.api.property.serverProperties;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class usersDetails {
	
	serverProperties sp = new serverProperties();
	@Test(priority = 1)
	public void GetMethod() {
		RestAssured.baseURI = sp.endPoint;
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.request(Method.GET, "/users");
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is =>  " + responseBody);
		int statusCode = response.getStatusCode();
		// Assert that correct status code is returned.
		Assert.assertEquals(statusCode,200, "Correct status code returned");
		Headers allHeaders = response.headers();
		// Iterate over all the Headers
		for (Header header : allHeaders) {
			System.out.println("Key: " + header.getName() + " Value: " + header.getValue());
		}
		String contentType = response.header("Content-Type");
		System.out.println("Content-Type value: " + contentType);
		String serverType = response.header("Server");
		System.out.println("Server value: " + serverType);
		String acceptLanguage = response.header("Content-Encoding");
		System.out.println("Content-Encoding: " + acceptLanguage);
	}

	@Test(priority = 2)
	public void PostMethod() throws JSONException {
		RestAssured.baseURI = sp.endPoint;
		RequestSpecification request = RestAssured.given();
		JSONObject requestParams = new JSONObject();
		requestParams.put("name", "Leanne Graham");
		requestParams.put("username", "Bret");
		requestParams.put("email", "test@test.com");
		requestParams.put("phone", "1-770-736-8031 x56442");
		requestParams.put("website", "hildegard.org");
		requestParams.put("id", "1");
		requestParams.put("address", " ");
		requestParams.put("street", "Kulas Light");
		requestParams.put("suite", "Apt. 556");
		requestParams.put("city", "Gwenborough");
		request.body(requestParams.toString());
		Response response = request.post("/users");
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode,201);
		}
	@Test(priority = 3)
	public void PutMethod() throws JSONException {
		RestAssured.baseURI = sp.endPoint;
		RequestSpecification request = RestAssured.given();
		JSONObject requestParams = new JSONObject();
		requestParams.put("name", "Leanne Graham");
		requestParams.put("username", "Bret");
		requestParams.put("email", "apitest@test.com");
		requestParams.put("phone", "1-770-736-8031 x56442");
		requestParams.put("website", "hildegard.org");
		requestParams.put("id", "1");
		requestParams.put("address", " ");
		requestParams.put("street", "Kulas Light");
		requestParams.put("suite", "Apt. 556");
		requestParams.put("city", "Gwenborough");
		request.body(requestParams.toString());
		Response response = request.put("/users");
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode,200);
		}
}
