package com.ricardovz.learning.checkout;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ItemTest {

    @Test(expected = IllegalArgumentException.class)
    public void givenNull_invalidSku() throws Exception {
        new Item(null, 10, 3, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenEmpty_invalidSku() throws Exception {
        new Item("", 10, 3, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenZero_invalidPrice() throws Exception {
        new Item("A", 0, 3, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenNegative_invalidPrice() throws Exception {
        new Item("A", -10, 3, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenNegative_invalidQuantity() throws Exception {
        new Item("A", 10, -3, null);
    }

    @Test
    public void validData() throws Exception {
        String sku = "A";
        float price = 10;
        int quantity = 0;
        OfferRule offerRule = null;

        Item item = new Item(sku, price, quantity, offerRule);

        assertThat(item.getSku(), is(sku));
        assertThat(item.getPrice(), is(price));
        assertThat(item.getQuantity(), is(quantity));
        assertThat(item.getOfferRule(), is(offerRule));

    }

    @Test
    public void simpleSubtotal() throws Exception {

        Item item = new Item("A", 50, 4, null);

        float subtotal = item.getSubtotal();

        assertThat(subtotal, is(200.0f));

    }

    @Test
    public void rule_simpleSubtotal() throws Exception {

        OfferRule offerRule = new OfferRule(3, 130);
        Item item = new Item("A", 50, 2, offerRule);

        float subtotal = item.getSubtotal();

        assertThat(subtotal, is(100.0f));

    }

    @Test
    public void rule_complexSubtotal_oneDiscount() throws Exception {

        OfferRule offerRule = new OfferRule(3, 130);
        Item item = new Item("A", 50, 3, offerRule);

        float subtotal = item.getSubtotal();

        assertThat(subtotal, is(130.0f));

    }

    @Test
    public void rule_complexSubtotal_twoDiscount() throws Exception {

        OfferRule offerRule = new OfferRule(3, 130);
        Item item = new Item("A", 50, 6, offerRule);

        float subtotal = item.getSubtotal();

        assertThat(subtotal, is(260.0f));

    }

    @Test
    public void complexSubtotal() throws Exception {

        OfferRule offerRule = new OfferRule(3, 130);
        Item item = new Item("A", 50, 8, offerRule);

        float subtotal = item.getSubtotal();

        assertThat(subtotal, is(360.0f));

    }
}