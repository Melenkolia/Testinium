package Assertions;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.Map;

public class General_Assertions {

    SoftAssert softAssert = new SoftAssert();

    public void verifyCreateBoardResponse(Response response, String boardName){

        softAssert.assertEquals(response.jsonPath().getString("name"), boardName,"Board name must be equal requests name parameter");
        softAssert.assertNotNull(response.jsonPath().getString("id"),"id parameter must be not null value");
        softAssert.assertNotNull(response.jsonPath().getString("idOrganization"),"idOrganization parameter must be not null value");
        softAssert.assertEquals(response.statusCode(),200,"Response status code must be equels to 200");
        softAssert.assertAll();

    }
    public void statusCodeCheck(Response response){
        softAssert.assertEquals(response.statusCode(),200,"Response status code must be equels to 200");
        softAssert.assertAll();

    }
    public void verifyListResponse(Response response){

        JsonPath jsonPath = response.jsonPath();
        List<Map<String, Object>> objectsList = jsonPath.getList("$");
        for (Map<String, Object> obj : objectsList) {

            softAssert.assertNotNull(obj.get("id"), "The 'id' field should not be null");
            softAssert.assertNotNull(obj.get("name"), "The 'name' field should not be null");
            softAssert.assertFalse((Boolean) obj.get("closed"), "The 'closed' field should be false");
            softAssert.assertNull(obj.get("color"), "The 'color' field should be null");
            softAssert.assertNotNull(obj.get("idBoard"), "The 'idBoard' field should not be null");
            softAssert.assertNotNull(obj.get("pos"), "The 'pos' field should not be null");
            softAssert.assertFalse((Boolean) obj.get("subscribed"), "The 'subscribed' field should be false");
            softAssert.assertNull(obj.get("softLimit"), "The 'softLimit' field should be null");
            softAssert.assertNull(obj.get("type"), "The 'type' field should be null");

        }
        softAssert.assertEquals(response.statusCode(),200,"Response status code must be equels to 200");
        softAssert.assertAll();
    }
    public void verifyListNameCheck(Response response){
        softAssert.assertEquals(response.jsonPath().getString("name[0]"),"Yapılacaklar","name parameter must be equal 'Yapılacaklar'");
        softAssert.assertEquals(response.jsonPath().getString("name[1]"),"Yapılıyor", "name parameter must be equal 'Yapılıyor'");
        softAssert.assertEquals(response.jsonPath().getString("name[2]"),"Tamamlandı","name parameter must be equal 'Tamamlandı");
        softAssert.assertAll();
    }
    public void verifyCreateCardResponse(Response response, String boardID, String listID, String cardname){
        softAssert.assertNotNull(response.jsonPath().getString("id"), "The 'id' field should not be null");
        softAssert.assertEquals(response.jsonPath().getString("idBoard"), boardID, "The 'idBoard' field should match the expected board ID");
        softAssert.assertEquals(response.jsonPath().getString("idList"), listID, "The 'idList' field should match the expected list ID");
        softAssert.assertEquals(response.jsonPath().getString("name"), cardname, "The 'name' field should match the expected card name");
        softAssert.assertEquals(response.statusCode(), 200, "The response status code should be 200");
        softAssert.assertAll();

    }
    public void verifyUpdateCardResponse(Response response, String boardID, String cardname){
        softAssert.assertNotNull(response.jsonPath().getString("id"), "The 'id' field should not be null");
        softAssert.assertEquals(response.jsonPath().getString("idBoard"), boardID, "The 'idBoard' field should match the expected board ID");
        softAssert.assertEquals(response.jsonPath().getString("name"), cardname, "The 'name' field should match the expected card name");
        softAssert.assertEquals(response.statusCode(), 200, "The response status code should be 200");
        softAssert.assertAll();

    }

    public void deleteBoardCheck(Response response){
        softAssert.assertNull(response.jsonPath().getString("_value"),"_value parameter must be null value");
        softAssert.assertEquals(response.statusCode(),200,"Response status code must be equels to 200");
        softAssert.assertAll();

    }
}
