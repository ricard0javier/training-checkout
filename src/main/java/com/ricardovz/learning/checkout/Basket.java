package com.ricardovz.learning.checkout;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a basket of items
 */
@Slf4j
public class Basket {

    /**
     * Represents the items contained in the basket
     */
    private final Map<String, Item> items = new HashMap<>();

    /**
     * Supermarket inventory at the moment of creation of the current instance
     */
    private Map<String, Item> catalog;

    /**
     * Constructor
     *
     * @param catalog Supermarket inventory at the moment of creation of the current instance (Not empty)
     */
    public Basket(Map<String, Item> catalog) {

        if (catalog == null || catalog.isEmpty()) {
            log.info("catalog value '{}' is invalid", catalog);
            throw new IllegalArgumentException("Basket 'catalog' should not be empty");
        }

        this.catalog = catalog;
    }

    /**
     * Returns a copy of the items of the basket
     */
    public Map<String, Item> getItems() {
        return new HashMap<>(items);
    }

    /**
     * Increases the number of the item into the basket by 1
     */
    public void add(String sku) {

        // find item in basket by sku
        Item itemInBasket = items.get(sku);
        if (itemInBasket != null) {
            // update
            itemInBasket = itemInBasket.toBuilder().quantity(itemInBasket.getQuantity() + 1).build();
            items.put(sku, itemInBasket);
            return;
        }

        // find item in catalog by sku
        Item itemInCatalog = catalog.get(sku);
        if (itemInCatalog != null) {
            // update
            itemInBasket = itemInCatalog.toBuilder().quantity(1).build();
            items.put(sku, itemInBasket);
            return;
        }

        // validate
        throw new IllegalArgumentException("The item " + sku + " is not available");
    }


    /**
     * Returns the number of elements in the basket
     */
    public int getTotalItems() {
        return items.values().stream()
                .map(Item::getQuantity)
                .reduce(0, (quantity, acc) -> quantity + acc);
    }
}
