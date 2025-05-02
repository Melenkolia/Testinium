package Specifications;

import Utilities.Config_Setting;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;


public class specifications extends Config_Setting {


    public RequestSpecification TrelloRequestSpecification() {

        return new RequestSpecBuilder().
                setBaseUri(baseURL.getProperty("BaseURL")).
                log(LogDetail.ALL).build();
    }

    public ResponseSpecification TrelloResponseSpecification(){
        return new ResponseSpecBuilder().
                log(LogDetail.ALL).
                build();


    }

}
