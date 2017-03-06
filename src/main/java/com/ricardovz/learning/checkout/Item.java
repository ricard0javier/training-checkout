package com.ricardovz.learning.checkout;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

/**
 * Represents on Supermarket item (Value Object)
 */
@Slf4j
@Value
@Builder(toBuilder = true)
@EqualsAndHashCode(of = "sku")
public class Item {

    /**
     * 'stock keeping units' or 'SKU'
     */
    private String sku;

    /**
     * item price.
     */
    private float price;

    /**
     * item quantity.
     */
    private int quantity;

    /**
     * discount rule
     */
    private OfferRule offerRule;

    /**
     * Constructor
     *
     * @param sku       'stock keeping units' or 'SKU' (not empty)
     * @param price     item price. (positive number)
     * @param quantity  item quantity. (not negative)
     * @param offerRule discount rule
     */
    public Item(String sku, float price, int quantity, OfferRule offerRule) {

        if (sku == null || sku.isEmpty()) {
            log.info("sku value '{}' is invalid", sku);
            throw new IllegalArgumentException("Item 'sku' should not be empty");
        }

        if (price <= 0) {
            log.info("price value '{}' is invalid", price);
            throw new IllegalArgumentException("Item 'price' should be a positive number");
        }

        if (quantity < 0) {
            log.info("price value '{}' is invalid", price);
            throw new IllegalArgumentException("Item 'quantity' should not be a negative number");
        }

        this.sku = sku;
        this.price = price;
        this.quantity = quantity;
        this.offerRule = offerRule;
    }


    /**
     * Calculates teh subtotal for the current product including the offer if it applies
     */
    public float getSubtotal() {
        // if there is not promotion for this article it is straight forward
        if (offerRule == null || quantity < offerRule.getUnits()) {
            log.info("There is not discount rule to apply");
            return quantity * price;
        }

        // otherwise a discount applies
        float onPromotion = offerRule.getPrice() * (quantity / offerRule.getUnits());
        float normal = price * (quantity % offerRule.getUnits());
        return onPromotion + normal;
    }
}
