package stepdefination;
import com.github.javafaker.Faker;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;
import utilities.ApiRequests;


import java.io.IOException;
import java.util.UUID;

public class StepDefination {

    String firstPerson ;
    String secondPerson ;
    String firstPersonUUID ;
    String secondPersonUUID ;

    String name ;
    String uuid ;
    String uuidForMessage ;

    Faker faker ;
    ApiRequests requestUtils ;

    public StepDefination() throws IOException {
        faker = new Faker();
        requestUtils = new ApiRequests();
    }


    @Given("I send a POST request for creating user 1")
    public void creatingUserusingPostRequest_1() {
        firstPerson = faker.name().firstName();
        firstPersonUUID = UUID.randomUUID().toString().replace("-", "");
        requestUtils.postRequestforUsers(firstPerson,firstPersonUUID);
        name = firstPerson ;
        uuid = firstPersonUUID ;
    }

    @Given("I send a POST request for creating user 2")
    public void creatingUserusingPostRequest_2() {
        secondPerson = faker.name().firstName();
        secondPersonUUID = UUID.randomUUID().toString().replace("-", "");
        requestUtils.postRequestforUsers(secondPerson,secondPersonUUID);
        name = secondPersonUUID ;
        uuid = secondPersonUUID ;
    }

    @When("I get the details of the created user using Id")
    public void theResponseShouldContainTheUserIDForUser(int arg0) {
        String expectedName = requestUtils.getRequestforUsers(uuid);
        Assert.assertEquals(name,expectedName);
    }

    @And("I send a Message from user 1 to user 2 {string}")
    public void iSendMessageFromUserToUser(String messageContent) {
        uuidForMessage = UUID.randomUUID().toString().replace("-", "");
        requestUtils.postRequestforSendingMessage(firstPerson,secondPerson,messageContent,uuidForMessage);
    }


    @Then("I get the details of the message sent {string}")
    public void verifyTheMessageDetails(String messageContent){
        String actualMessage =  requestUtils.getMessageDetails(firstPersonUUID,secondPersonUUID);
        Assert.assertEquals(messageContent,actualMessage);
    }

    @When("I update the message details for the sent message {string}")
    public void updateTheMessageDetails(String messageContent){
        requestUtils.updateMessageDetails(uuidForMessage,messageContent);
    }

    @And("I get the details of the updated message {string}")
    public void getUpdateMessageDetails(String messageContent){
        String actualMessage = requestUtils.getUpdatedMessageDetails(uuidForMessage);
        Assert.assertEquals(messageContent,actualMessage);
    }

    @When("I delete the message that was created")
    public void deleteMessageDetails(){
        requestUtils.deleteMessageDetails(uuidForMessage);
    }

    @Then("I should not receive the message details for the UUID")
    public void iShouldNotReceiveTheMessageDetailsForTheUUID() {
        String actualMessage = requestUtils.getUpdatedMessageDetails(uuidForMessage);
        Assert.assertEquals(null,actualMessage);
    }
}
