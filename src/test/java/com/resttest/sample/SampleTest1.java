package com.resttest.sample;

import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.github.fge.jsonschema.main.JsonValidator;
import com.jayway.restassured.response.Response;
import static com.jayway.restassured.RestAssured.given;

import java.io.File;
import java.io.IOException;

/**
 * Sample test case for Json Schema Validaiton.
 */

public class SampleTest1 
{
   @Test
   public void sampleTestCase() throws IOException, ProcessingException {
	   Response response = given().relaxedHTTPSValidation().contentType("json").request().when().get("http://dummy.restapiexample.com/api/v1/employee/1");
	   String json = response.asString();
	   String schemaFilePath = System.getProperty("user.dir")+File.separator + "src" + File.separator + "test" + File.separator + "java" + File.separator 
			  						+ "resources" + File.separator + "Input" + File.separator + "schema.json";
	   File jsonFile = new File(schemaFilePath);
       final JsonNode data = JsonLoader.fromString(json);
       final JsonNode schema = JsonLoader.fromFile(jsonFile);

       final JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
       JsonValidator validator = factory.getValidator();

       ProcessingReport report = validator.validate(schema, data);
       System.out.println(report.isSuccess());
	   
	   
   }
}
