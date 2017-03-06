package com.ricardovz.learning.checkout;

import org.junit.Test;

import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class BasketTest {

    @Test(expected = IllegalArgumentException.class)
    public void givenNull_invalidCatalog() throws Exception {
        new Basket(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenEmpty_invalidCatalog() throws Exception {
        new Basket(new HashMap<>());
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenUnknownSku_invalidSku() throws Exception {
        Item item = new Item("A", 10, 0, null);
        HashMap<String, Item> catalog = new HashMap<>();
        catalog.put(item.getSku(), item);

        Basket basket = new Basket(catalog);

        basket.add("B");
    }

    @Test
    public void givenKnownSku_TotalIncrease() throws Exception {
        Item itemA = new Item("A", 10, 0, null);
        Item itemB = new Item("B", 10, 0, null);
        HashMap<String, Item> catalog = new HashMap<>();
        catalog.put(itemA.getSku(), itemA);
        catalog.put(itemB.getSku(), itemB);

        Basket basket = new Basket(catalog);
        basket.add("A");
        basket.add("B");
        basket.add("A");

        assertThat(basket.getTotalItems(), is(3));
    }
}