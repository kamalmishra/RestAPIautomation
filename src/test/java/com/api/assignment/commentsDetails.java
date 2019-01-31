package com.api.assignment;

import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.api.property.serverProperties;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class commentsDetails {
	
	serverProperties sp = new serverProperties();

	@Test(priority = 1)
	public void getMethod() {
		RestAssured.baseURI = sp.endPoint;
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.request(Method.GET, "/comments");
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is =>  " + responseBody);
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200, "Correct status code returned");
		Headers allHeaders = response.headers();
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
		requestParams.put("id", "1");
		requestParams.put("postId", "1");
		requestParams.put("name", "My PUT update");
		requestParams.put("email", "Eliseo@gardner.biz");
		requestParams.put("body", "new  modified title , current time 15:05:46.973");
		request.body(requestParams.toString());
		Response response = request.post("/comments");
		int statusCode = response.getStatusCode();
		int expectedCode = 201;
		Assert.assertEquals(statusCode, expectedCode);
	}
	@Test(priority = 3)
	public void PutMethod() throws JSONException {
		RestAssured.baseURI = sp.endPoint;
		RequestSpecification request = RestAssured.given();
		JSONObject requestParams = new JSONObject();
		requestParams.put("id", "1");
		requestParams.put("postId", "1");
		requestParams.put("name", "put my kamal");
		requestParams.put("email", "Eliseo@gardner.biz");
		requestParams.put("body", "new  modified title , current time 15:05:46.973");
		request.body(requestParams.toString());
		Response response = request.put("/comments");
		int statusCode = response.getStatusCode();
		int expectedCode = 200;
		Assert.assertEquals(statusCode, expectedCode);
	}
	}


