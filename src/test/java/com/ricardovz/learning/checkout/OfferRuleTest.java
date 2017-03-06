package com.ricardovz.learning.checkout;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class OfferRuleTest {

    @Test(expected = IllegalArgumentException.class)
    public void givenZero_invalidUnits() throws Exception {
        new OfferRule(0, 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenNegative_invalidUnits() throws Exception {
        new OfferRule(-3, 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenZero_invalidPrice() throws Exception {
        new OfferRule(3, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenNegative_invalidPrice() throws Exception {
        new OfferRule(3, -10);
    }

    @Test
    public void validData() throws Exception {
        int units = 3;
        float price = 10;

        OfferRule offerRule = new OfferRule(units, price);

        assertThat(offerRule.getUnits(), is(units));
        assertThat(offerRule.getPrice(), is(price));

    }
}