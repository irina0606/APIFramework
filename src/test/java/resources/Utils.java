package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Properties;

public class Utils {
    public static RequestSpecification reqSpec;
    public RequestSpecification requestSpecification() throws IOException {

        if (reqSpec == null) {
            PrintStream log = new PrintStream(new FileOutputStream("logDetails.txt"));
            reqSpec = new RequestSpecBuilder()
                    .setContentType(ContentType.JSON)
                    .setBaseUri(getGlobalValue("baseUrl"))
                    .addQueryParam("key", "qaclick123")
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .build();
            return reqSpec;
        }
        return reqSpec;
    }

    public static String getGlobalValue(String key) throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("C:\\Users\\iryna.kalynychenko\\Udemy projects\\APIFramework\\src\\test\\java\\resources\\global.properties");
        prop.load(fis);
        return prop.getProperty(key);
    }


    public String getJsonPath(Response response, String key){
        String resp = response.asString();
        JsonPath json = new JsonPath(resp);
        return json.get(key).toString();
    }
}
