package dojo;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class CocktailSteps {

    private Order order;

    @Given("{string} who wants to buy a drink")
    public void someoneWhoWantsToBuyADrink(String owner) {
        order = new Order();
        order.declareOwner(owner);
    }

    @When("an order is declared for {string}")
    public void anOrderIsDeclaredForSomeone(String target) {
        order.declareTarget(target);
    }

    @Then("there is {int} cocktail in the order")
    public void thereIsXCocktailInTheOrder(int number) {
        List<String> cocktails =  order.getCocktails();
        assertEquals(number, cocktails.size());
    }

    @And("a message saying {string} is added")
    public void aMessageSayingIsAdded(String message) {
        order.addMessage(message);
    }

    @Then("the ticket must say {string}")
    public void theTicketMustSay(String expectedMessage) {
        assertEquals(expectedMessage, order.getMessage());
    }
}
