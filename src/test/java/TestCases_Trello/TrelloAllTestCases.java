package TestCases_Trello;

import Assertions.General_Assertions;
import EndPoints.AllEndPoints;
import Utilities.Config_Setting;
import io.qameta.allure.Description;
import io.restassured.response.Response;


import org.testng.annotations.Test;

import java.util.*;

public class TrelloAllTestCases extends Config_Setting implements AllEndPoints {

    private String boardID;
    private String listID1;
    private String listID2;
    private String listID3;
    private String cardID1;
    private String cardID2;

    General_Assertions assertions = new General_Assertions();

    @Description("Trello Uygulamasında Board Oluşturma Test Senaryosu")
    @Test(priority = 1, description = "Key, Token ve Name parametreleri ile atılan request sonucunda response mesajında board oluşturulduğu, oluşturulan board parametrelerinin görüntülenmesi kontrolü")
    public void Trello_CreateBoard() {

        HashMap<String, Object> boardRequestBody = new HashMap<>();
        boardRequestBody.put("name", "Sinan AY Board Example Testinium");
        boardRequestBody.put("key", config.getProperty("Key"));
        boardRequestBody.put("token", config.getProperty("Token"));

        Response createBoard = trelloCreateBoard("/1/boards/", boardRequestBody);
        assertions.verifyCreateBoardResponse(createBoard, "Sinan AY Board Example Testinium");

        boardID = createBoard.jsonPath().getString("id");

        waitInSeconds(2);
    }

    @Description("Trello Uygulamasında Oluşturulan Boarda Bulunan Listeleri Görüntüleme Test Senaryosu")
    @Test(priority = 2, description = "Key, Token parametreleri ile atılan request sonucunda response mesajında boarda bulunan listelerin görüntülenmesi kontrolü")
    public void Trello_GetBoardList()  {

        HashMap<String, Object> listRequestBody = new HashMap<>();
        listRequestBody.put("key", config.getProperty("Key"));
        listRequestBody.put("token", config.getProperty("Token"));

        Response getList = trelloGetList("/1/boards/{id}/lists", listRequestBody, boardID);

        assertions.verifyListResponse(getList);
        assertions.verifyListNameCheck(getList);

        listID1 = getList.jsonPath().getString("id[0]");
        listID2 = getList.jsonPath().getString("id[1]");
        listID3 = getList.jsonPath().getString("id[2]");

        waitInSeconds(2);
    }

    @Description("Trello Uygulamasında Card Oluşturma Test Senaryosu")
    @Test(priority = 3, description = "Key, Token, idList ve name parametreleri ile atılan request sonucunda 'Yapılıcaklar' bölümüne card ekleme işleminin gerçekleştirilmesi kontrolü")
    public void Trello_CreateCard_Yapilacaklar() {

        String cardName1 = "Trello Card 1";

        HashMap<String, Object> cardRequestBody1 = new HashMap<>();
        cardRequestBody1.put("key", config.getProperty("Key"));
        cardRequestBody1.put("token", config.getProperty("Token"));
        cardRequestBody1.put("name", cardName1);
        cardRequestBody1.put("idList", listID1);

        Response createCard = trelloCreateCard("/1/cards", cardRequestBody1);

        assertions.verifyCreateCardResponse(createCard, boardID, listID1, cardName1);

        cardID1 = createCard.jsonPath().getString("id");

        waitInSeconds(2);
    }

    @Description("Trello Uygulamasında Card Oluşturma Test Senaryosu")
    @Test(priority = 4, description = "Key, Token, idList ve name parametreleri ile atılan request sonucunda 'Yapılıyor' bölümüne card ekleme işleminin gerçekleştirilmesi kontrolü")
    public void Trello_CreateCard_Yapiliyor() {

        String cardName2 = "Trello Card 2";

        HashMap<String, Object> cardRequestBody2 = new HashMap();
        cardRequestBody2.put("key", config.getProperty("Key"));
        cardRequestBody2.put("token", config.getProperty("Token"));
        cardRequestBody2.put("name", cardName2);
        cardRequestBody2.put("idList", listID2);

        Response createCard = trelloCreateCard("/1/cards", cardRequestBody2);

        assertions.verifyCreateCardResponse(createCard, boardID, listID2, cardName2);

        cardID2 = createCard.jsonPath().getString("id");

        waitInSeconds(2);
    }

    @Description("Trello Uygulamasında Card Güncelleme Test Senaryosu")
    @Test(priority = 5, description = "Key, Token, Random idList parametreleri ile atılan request sonucunda card güncelleme işleminin gerçekleştirilmesi kontrolü")
    public void Trello_UpdateRandomCard() {

        String[] options = {cardID1, cardID2};
        Random random = new Random();

        String randomCardOption = options[random.nextInt(options.length)];

        String cardName3 = "Trello Card Update";

        HashMap<String, Object> cardUpdateRequestBody = new HashMap();
        cardUpdateRequestBody.put("key", config.getProperty("Key"));
        cardUpdateRequestBody.put("token", config.getProperty("Token"));
        cardUpdateRequestBody.put("name", cardName3);

       Response updateCard = trelloUpdateCard("/1/cards/{id}", cardUpdateRequestBody,randomCardOption);

       assertions.verifyUpdateCardResponse(updateCard, boardID, cardName3);

        waitInSeconds(2);
    }

    @Description("Trello Uygulamasında Card Silme Test Senaryosu")
    @Test(priority = 6, description = "Key, Token, cardID parametreleri ile atılan request sonucunda card silme işleminin gerçekleştirilmesi kontrolü")
    public void Trello_DeleteCard_Yapilacaklar() {

        HashMap<String, Object> cardDeleteRequestBody1 = new HashMap();
        cardDeleteRequestBody1.put("key", config.getProperty("Key"));
        cardDeleteRequestBody1.put("token", config.getProperty("Token"));

        Response deleteCard = trelloDeleteCard("/1/cards/{id}", cardDeleteRequestBody1,cardID1);

        assertions.statusCodeCheck(deleteCard);

        waitInSeconds(2);
    }

    @Description("Trello Uygulamasında Card Silme Test Senaryosu")
    @Test(priority = 7, description = "Key, Token, cardID parametreleri ile atılan request sonucunda card silme işleminin gerçekleştirilmesi kontrolü")
    public void Trello_DeleteCard_Yapiliyor() {

        HashMap<String, Object> cardDeleteRequestBody2 = new HashMap();
        cardDeleteRequestBody2.put("key", config.getProperty("Key"));
        cardDeleteRequestBody2.put("token", config.getProperty("Token"));


        Response deleteCard = trelloDeleteCard("/1/cards/{id}", cardDeleteRequestBody2,cardID2);

        assertions.statusCodeCheck(deleteCard);

        waitInSeconds(2);

    }

    @Description("Trello Uygulamasında Board Silme Test Senaryosu")
    @Test(priority = 8, description = "Key, Token, boardID parametreleri ile atılan request sonucunda board silme işleminin gerçekleştirilmesi kontrolü")
    public void Trello_DeleteBoard() {

        HashMap<String, Object> boardDeleteRequestBody = new HashMap();
        boardDeleteRequestBody.put("key", config.getProperty("Key"));
        boardDeleteRequestBody.put("token", config.getProperty("Token"));


        Response deleteBoard = trelloDeleteBoard("/1/boards/{id}", boardDeleteRequestBody,boardID);

        assertions.deleteBoardCheck(deleteBoard);

        waitInSeconds(2);

    }

}
