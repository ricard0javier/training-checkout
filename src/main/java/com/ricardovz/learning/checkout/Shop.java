package com.ricardovz.learning.checkout;


import java.util.HashMap;
import java.util.Map;

/**
 * Represents the super market shop
 */
public class Shop {

    /**
     * Represents the supermarket inventory
     */
    private Map<String, Item> inventory = new HashMap<>();

    /**
     * Retrieves a copy of the inventory
     */
    public Map<String, Item> getInventory() {
        return new HashMap<>(inventory);
    }

    /**
     * Retrieves a copy of the inventory
     */
    public void clearInventory() {
        inventory.clear();
    }

    /**
     * Adds an item to the inventory
     */
    public void addToInventory(Item item) {
        inventory.put(item.getSku(), item);
    }

    /**
     * Returns a new Basket with a copy of the current inventory
     */
    public Basket getBasket() {
        if (inventory.isEmpty()) {
            throw new IllegalStateException("There are not items to be sold");
        }
        return new Basket(getInventory());
    }

    /**
     * Returns the total value of a basket based on the current inventory
     */
    public float checkout(Basket basket) {
        return basket.getItems().values().stream()
                .filter(item -> inventory.containsKey(item.getSku()))
                .map(item -> inventory.get(item.getSku())
                        .toBuilder()
                        .quantity(item.getQuantity())
                        .build())
                .map(Item::getSubtotal)
                .reduce(0.0f, (subtotal, acc) -> subtotal + acc);
    }
}
