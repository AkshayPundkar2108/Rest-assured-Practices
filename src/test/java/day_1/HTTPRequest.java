package day_1;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import java.util.HashMap;
public class HTTPRequest {
	int id;

	@Test	(priority=1)
	void getusers() 
	{
		given()
		.when()
			.get("https://reqres.in/api/users?page=2")
		.then()
			.statusCode(200)
			.body("page",equalTo(2))
			.log().all();
	}
	
	@Test(priority=2)
	void CreateUser()
	{
		{
			String url="https://reqres.in/";
			String payload="{\r\n"
					+ "    \"name\": \"akshay\",\r\n"
					+ "    \"job\": \"testing\"\r\n"
					+ "}";
			String resources="/api/users?page=2";
			RestAssured.baseURI=url;
			given().body(payload).
			when().post(resources)
			.then()
			.log().all();
	}
		}
	
		
	@Test(priority=3,dependsOnMethods={"CreateUser"})
	void UpdateUser()
	{
			String url="https://reqres.in/";
			String payload="{\r\n"
					+ "    \"name\": \"Pundkar\",\r\n"
					+ "    \"job\": \"qa\"\r\n"
					+ "}";
			String resources="api/users/2";
			given()
					.body(payload)
			.when()
					.put(resources)
								
			.then()
					.statusCode(200)
					.log().all();
					
		
	}
		@Test(priority=4)
		void DeleteUser()
		{
			given()
			.when()
				.delete("https://reqres.in/api/users/"+id)
			.then()
				.statusCode(204);
			
		}
			
			
			
	}
	
	
	
	
	

