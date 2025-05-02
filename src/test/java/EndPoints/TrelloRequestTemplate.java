package EndPoints;

import Specifications.specifications;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public interface TrelloRequestTemplate {

    default Response trelloCreateBoard (String path, HashMap<String, Object> Param){

        RequestSpecification requestSpecification = new specifications().TrelloRequestSpecification();
        ResponseSpecification responseSpecification = new specifications().TrelloResponseSpecification();

        Response response;
        response = given(requestSpecification).
                header("Content-Type", "pplication/json").
                header("Accept","application/json").
                queryParams(Param).
                post(path).
                then().spec(responseSpecification).
                extract().
                response();

        return response;

    }

    default Response trelloCreateCard (String path, HashMap<String, Object> Param){

        RequestSpecification requestSpecification = new specifications().TrelloRequestSpecification();
        ResponseSpecification responseSpecification = new specifications().TrelloResponseSpecification();

        Response response;
        response = given(requestSpecification).
                header("Content-Type", "pplication/json").
                header("Accept","application/json").
                queryParams(Param).
                post(path).
                then().spec(responseSpecification).
                extract().
                response();

        return response;

    }

    default Response trelloUpdateCard (String path, HashMap<String, Object> Param, String cardID){

        RequestSpecification requestSpecification = new specifications().TrelloRequestSpecification();
        ResponseSpecification responseSpecification = new specifications().TrelloResponseSpecification();

        Response response;
        response = given(requestSpecification).
                header("Content-Type", "pplication/json").
                header("Accept","application/json").
                param("id", cardID).
                queryParams(Param).
                put(path, cardID).
                then().spec(responseSpecification).
                extract().
                response();

        return response;

    }

    default Response trelloDeleteCard (String path, HashMap<String, Object> Param, String cardID){

        RequestSpecification requestSpecification = new specifications().TrelloRequestSpecification();
        ResponseSpecification responseSpecification = new specifications().TrelloResponseSpecification();

        Response response;
        response = given(requestSpecification).
                header("Content-Type", "pplication/json").
                header("Accept","application/json").
                param("id", cardID).
                queryParams(Param).
                delete(path, cardID).
                then().spec(responseSpecification).
                extract().
                response();

        return response;

    }

    default Response trelloDeleteBoard (String path, HashMap<String, Object> Param, String boardID){

        RequestSpecification requestSpecification = new specifications().TrelloRequestSpecification();
        ResponseSpecification responseSpecification = new specifications().TrelloResponseSpecification();

        Response response;
        response = given(requestSpecification).
                header("Content-Type", "pplication/json").
                header("Accept","application/json").
                param("id", boardID).
                queryParams(Param).
                log().all().
                delete(path, boardID).
                then().spec(responseSpecification).
                extract().
                response();

        return response;

    }

    default Response trelloGetList (String path, HashMap<String, Object> Param, String boardID){

        RequestSpecification requestSpecification = new specifications().TrelloRequestSpecification();
        ResponseSpecification responseSpecification = new specifications().TrelloResponseSpecification();

        Response response;
        response = given(requestSpecification).
                header("Content-Type", "pplication/json").
                header("Accept","application/json").
                param("id", boardID).
                queryParams(Param).
                get(path, boardID).
                then().spec(responseSpecification).
                extract().
                response();

        return response;

    }


}
