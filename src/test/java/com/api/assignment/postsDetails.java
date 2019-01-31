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

public class postsDetails {
	
	serverProperties sp = new serverProperties();
	@Test(priority = 1)
	public void getMethod() {
		RestAssured.baseURI = sp.endPoint;
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.request(Method.GET, "/posts");
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is =>  " + responseBody);
		int statusCode = response.getStatusCode();
		// Assert that correct status code is returned.
		Assert.assertEquals(statusCode, 200, "Correct status code returned");
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
	public void postMethod() throws JSONException {
		RestAssured.baseURI = sp.endPoint;
		RequestSpecification request = RestAssured.given();
		JSONObject requestParams = new JSONObject();
		requestParams.put("id", "1");
		requestParams.put("userId", "4");
		requestParams.put("title", "eum et est occaecati");
		requestParams.put("body",
				"ullam et saepe reiciendis voluptatem adipisci\\nsit amet autem assumenda provident rerum culpa\\nquis hic commodi nesciunt rem tenetur doloremque ipsam iure\\nquis sunt voluptatem rerum illo velit");
		request.body(requestParams.toString());
		Response response = request.post("/Posts");
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode,201);
	}
	@Test(priority = 3)
	public void putMethod() throws JSONException {
		RestAssured.baseURI = sp.endPoint;
		RequestSpecification request = RestAssured.given();
		JSONObject requestParams = new JSONObject();
		requestParams.put("id", "1");
		requestParams.put("userId", "4");
		requestParams.put("title", "apiTest");
		requestParams.put("body",
				"ullam et saepe reiciendis voluptatem adipisci\\nsit amet autem assumenda provident rerum culpa\\nquis hic commodi nesciunt rem tenetur doloremque ipsam iure\\nquis sunt voluptatem rerum illo velit");
		request.body(requestParams.toString());
		Response response = request.put("/Posts");
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode,200);
	}

}
