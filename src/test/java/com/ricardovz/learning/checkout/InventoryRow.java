package com.ricardovz.learning.checkout;

import lombok.Data;

/**
 * Helper class for the cucumber feature file
 */
@Data
class InventoryRow {
    private String sku;
    private Float price;
    private Integer unitsForOffer;
    private Float offerPrice;

    /**
     * Transforms feature table to inventory entries
     */
    Item toItem() {

        OfferRule offerRule = null;
        if (unitsForOffer != null && offerPrice != null) {
            offerRule = OfferRule.builder()
                    .units(unitsForOffer)
                    .price(offerPrice)
                    .build();
        }

        return Item.builder()
                .sku(sku)
                .price(price)
                .offerRule(offerRule)
                .build();
    }
}
