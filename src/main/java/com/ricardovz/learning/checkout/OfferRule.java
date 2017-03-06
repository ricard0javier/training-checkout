package com.ricardovz.learning.checkout;

import lombok.Builder;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

/**
 * Represents an item Discount
 */
@Value
@Builder
@Slf4j
public class OfferRule {

    /**
     * Units necessary to apply the discount
     */
    private int units;

    /**
     * Price given to the number of units of the item
     */
    private float price;

    /**
     * Constructor
     *
     * @param units number of units necessary to apply the discount (should be positive)
     * @param price Price given to the number of units of the item (should be positive)
     */
    public OfferRule(int units, float price) {
        if (units <= 0) {
            log.info("units value '{}' is invalid", units);
            throw new IllegalArgumentException("Rule 'units' should be a positive number");
        }

        if (price <= 0) {
            log.info("price value '{}' is invalid", price);
            throw new IllegalArgumentException("Rule 'price' should be a positive number");
        }

        this.units = units;
        this.price = price;
    }
}
