
import static com.jayway.restassured.RestAssured.expect;
import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Before;
import org.junit.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;


public class TestGET {

	@Before
	public final void setup(){
		RestAssured.baseURI  = "http://api.openweathermap.org/data/2.5";
	}

	@Test
	public void getExample()
	{
		get("/weather?q=London,uk").then().assertThat().body("name", equalTo("London"));
		get("/weather?q=Bangalore,in").then().assertThat().body("name", equalTo("Bangalore"));
	}

	@Test
	public void getStatusCheck(){
		expect().statusCode( 200 ).when().get("/weather?q=London,uk");
		expect().statusCode( 200 ).when().get("/weather?q=Bangalore,in");
	}

	@Test
	public void testICSLogin(){
		RestAssured.baseURI  = "http://ics-qa3.informatica.com";
		Response r = given()
				.contentType("application/json").
				body("{\"@type\":\"login\",\"username\":\"sharath2@infa.com\",\"password\":\"sharath\"}").
				when().
				post("/ma/api/v2/user/login");

		System.out.println(r.statusCode());
		String body = r.getBody().asString();
		System.out.println(body);
	}


}
