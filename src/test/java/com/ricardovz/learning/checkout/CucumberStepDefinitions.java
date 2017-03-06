package com.ricardovz.learning.checkout;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertFalse;

/**
 * Specify the step definitions for the feature file 'src/test/resources/checkout.features'
 */
public class CucumberStepDefinitions {

    private Shop shop = null;
    private Basket basket = null;

    @Given("^a new shop$")
    public void a_new_shop() throws Throwable {
        shop = new Shop();
    }

    @When("^there is an inventory of goods$")
    public void there_is_an_inventory_of_goods(List<InventoryRow> rows) throws Throwable {
        rows.forEach(row -> shop.addToInventory(row.toItem()));
    }

    @Then("^the inventory should contains (.*) items available$")
    public void the_inventory_should_show_items(int totalItems) throws Throwable {
        assertThat(shop.getInventory().size(), is(equalTo(totalItems)));
    }

    @And("^the inventory contains the item (.*) with price (.*)$")
    public void the_inventory_contains_the_item_X_with_price(String sku, float price) throws Throwable {

        Item item = shop.getInventory().get(sku);

        assertFalse(item == null);
        assertThat(item.getPrice(), is(equalTo(price)));
    }

    @And("^I get a basked$")
    public void I_get_a_Basket() {
        basket = shop.getBasket();
    }

    @And("^I add the item (.*) to the basked$")
    public void I_add_the_item_X_to_the_basked(String sku) throws Throwable {
        basket.add(sku);
    }

    @And("^the basket contains (.*) items$")
    public void the_basket_contains_X_items(int numberOfItems) throws Throwable {
        assertThat(basket.getTotalItems(), is(equalTo(numberOfItems)));
    }

    @And("^the basket contains (.*) (.*)'s$")
    public void the_basket_shows_X_s_for(int numberOfItems, String sku) throws Throwable {
        Item item = basket.getItems().get(sku);

        assertFalse(item == null);
        assertThat(item.getQuantity(), is(equalTo(numberOfItems)));
    }

    @Then("^the checkout total price is (.*)$")
    public void the_checkout_total_price_is(float checkoutTotal) throws Throwable {
        shop.checkout(basket);
        assertThat(shop.checkout(basket), is(equalTo(checkoutTotal)));
    }
}
