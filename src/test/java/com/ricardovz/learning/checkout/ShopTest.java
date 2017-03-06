package com.ricardovz.learning.checkout;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ShopTest {

    private Shop shop;

    @Before
    public void setUp() throws Exception {
        shop = new Shop();
    }

    @Test
    public void emptyInventory() throws Exception {
        assertThat(shop.getInventory().size(), is(0));
    }

    @Test
    public void addToInventory() throws Exception {
        String itemSku = "A";
        Item item = new Item(itemSku, 30, 0, null);

        shop.addToInventory(item);

        assertThat(shop.getInventory().size(), is(1));
        assertThat(shop.getInventory().containsKey(itemSku), is(true));
    }

    @Test(expected = IllegalStateException.class)
    public void given_EmptyCatalog_getBasket() throws Exception {
        shop.getBasket();
    }

    @Test
    public void givenEmptyBasket_checkout() throws Exception {

        String itemSku = "A";
        Item item = new Item(itemSku, 30, 0, null);

        shop.addToInventory(item);

        Basket basket = shop.getBasket();
        assertThat(shop.checkout(basket), is(0.0f));

    }

    @Test
    public void givenNonEmptyBasket_checkout() throws Exception {

        String itemSku = "A";
        Item item = new Item(itemSku, 30, 0, null);

        shop.addToInventory(item);

        Basket basket = shop.getBasket();
        basket.add(itemSku);
        assertThat(shop.checkout(basket), is(30.0f));

    }

    @Test
    public void givenUnknownItem_checkout() throws Exception {

        String itemSku = "A";
        Item item = new Item(itemSku, 30, 0, null);

        shop.addToInventory(item);

        Basket basket = shop.getBasket();
        basket.add("A");

        shop.clearInventory();
        assertThat(shop.checkout(basket), is(0.0f));

    }
}